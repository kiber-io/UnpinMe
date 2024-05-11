package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.kiber.unpinme.logInfo

class OpenSSLSocketImplConscryptHook(lpparam: XC_LoadPackage.LoadPackageParam) : Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "com.android.org.conscrypt.OpenSSLSocketImpl",
            "verifyCertificateChain",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam?) {
                    logInfo("[+] Bypassing OpenSSLSocketImpl Conscrypt {1}")
                }
            }
        )
    }
}