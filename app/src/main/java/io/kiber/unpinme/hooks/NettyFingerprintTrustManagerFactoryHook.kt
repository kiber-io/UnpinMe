package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.kiber.unpinme.logInfo

class NettyFingerprintTrustManagerFactoryHook(lpparam: XC_LoadPackage.LoadPackageParam) :
    Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "io.netty.handler.ssl.util.FingerprintTrustManagerFactory",
            "checkTrusted",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing Netty FingerprintTrustManagerFactory")
                }
            }
        )
    }
}