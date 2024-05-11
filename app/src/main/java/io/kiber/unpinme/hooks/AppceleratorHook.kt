package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam
import io.kiber.unpinme.logInfo

class AppceleratorHook(lpparam: LoadPackageParam) : Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "appcelerator.https.PinningTrustManager",
            "checkServerTrusted",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam?) {
                    logInfo("[+] Bypassing Appcelerator PinningTrustManager")
                }
            })
    }
}