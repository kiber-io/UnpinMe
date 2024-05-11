package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam
import io.kiber.unpinme.logInfo
import java.security.cert.X509Certificate

class TrustManagerImplHook(lpparam: LoadPackageParam) : Hook(lpparam) {
    override fun hook() {
        findAndHookMethod("com.android.org.conscrypt.TrustManagerImpl",
            "checkTrustedRecursive",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): List<X509Certificate> {
                    logInfo("[+] Bypassing TrustManagerImpl (Android > 7) checkTrustedRecursive check for ${param.args[3]}")
                    return ArrayList()
                }
            })

        // TODO: probably no more necessary
        findAndHookMethod("com.android.org.conscrypt.TrustManagerImpl",
            "verifyChain",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Any? {
                    logInfo("[+] Bypassing TrustManagerImpl (Android > 7) verifyChain check for ${param.args[3]}")
                    return param.args[0]
                }
            })
    }
}