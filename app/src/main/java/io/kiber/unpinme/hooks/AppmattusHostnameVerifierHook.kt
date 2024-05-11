package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.kiber.unpinme.logInfo
import javax.net.ssl.SSLSession

class AppmattusHostnameVerifierHook(lpparam: XC_LoadPackage.LoadPackageParam) : Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "com.appmattus.certificatetransparency.internal.verifier.CertificateTransparencyHostnameVerifier",
            "verify",
            String::class.java,
            SSLSession::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Boolean {
                    logInfo("[+] Bypassing Appmattus HostnameVerifier: ${param.args[0]}")
                    return true
                }
            }
        )
    }
}

