package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam
import io.kiber.unpinme.logInfo
import java.security.cert.X509Certificate
import javax.net.ssl.SSLSession

class TrustKitHook(lpparam: LoadPackageParam) : Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "com.datatheorem.android.trustkit.pinning.OkHostnameVerifier",
            "verify",
            String::class.java,
            SSLSession::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Boolean {
                    logInfo("[+] Bypassing TrustKit {1}: ${param.args[0]}")
                    return true
                }
            })

        findAndHookMethod(
            "com.datatheorem.android.trustkit.pinning.OkHostnameVerifier",
            "verify",
            String::class.java,
            X509Certificate::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Boolean {
                    logInfo("[+] Bypassing TrustKit {2}: ${param.args[0]}")
                    return true
                }
            })

        findAndHookMethod(
            "com.datatheorem.android.trustkit.pinning.PinningTrustManager",
            "checkServerTrusted",
            Array<X509Certificate>::class.java,
            String::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing TrustKit {3}")
                }
            })
    }
}