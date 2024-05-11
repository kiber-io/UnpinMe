package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.kiber.unpinme.logInfo
import java.security.cert.X509Certificate
import javax.net.ssl.SSLSession

class SquareUpOkHostnameVerifierHook(lpparam: XC_LoadPackage.LoadPackageParam) : Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "com.squareup.okhttp.internal.tls.OkHostnameVerifier",
            "verify",
            String::class.java,
            X509Certificate::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing SquareUp OkHostnameVerifier {1}: ${param.args[0]}")
                }
            }
        )

        findAndHookMethod(
            "com.squareup.okhttp.internal.tls.OkHostnameVerifier",
            "verify",
            String::class.java,
            SSLSession::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing SquareUp OkHostnameVerifier {1}: ${param.args[0]}")
                }
            }
        )
    }
}