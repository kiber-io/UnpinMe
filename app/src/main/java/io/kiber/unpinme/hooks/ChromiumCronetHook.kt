package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.kiber.unpinme.logInfo
import java.util.Date

class ChromiumCronetHook(lpparam: XC_LoadPackage.LoadPackageParam) : Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "org.chromium.net.impl.CronetEngineBuilderImpl",
            "enablePublicKeyPinningBypassForLocalTrustAnchors",
            Boolean::class.java,
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Disabling Public Key pinning for local trust anchors in Chromium Cronet")
                    param.args[0] = true
                }
            }
        )

        findAndHookMethod(
            "org.chromium.net.impl.CronetEngineBuilderImpl",
            "addPublicKeyPins",
            String::class.java,
            Set::class.java,
            Boolean::class.java,
            Date::class.java,
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing Chromium Cronet pinner for: ${param.args[0]}")
                    param.result = this
                }
            }
        )
    }
}