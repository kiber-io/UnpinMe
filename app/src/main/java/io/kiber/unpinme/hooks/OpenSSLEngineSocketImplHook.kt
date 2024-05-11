package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.kiber.unpinme.logInfo

class OpenSSLEngineSocketImplHook(lpparam: XC_LoadPackage.LoadPackageParam) : Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "com.android.org.conscrypt.OpenSSLEngineSocketImpl",
            "verifyCertificateChain",
            Array<Long>::class.java,
            String::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing OpenSSLEngineSocketImpl Conscrypt: ${param.args[1]}")
                }
            }
        )
    }
}