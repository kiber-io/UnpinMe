package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.kiber.unpinme.logInfo
import java.security.cert.Certificate

class SquareUpCertificatePinnerHook(lpparam: XC_LoadPackage.LoadPackageParam) : Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "com.squareup.okhttp.CertificatePinner",
            "check",
            String::class.java,
            Certificate::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing SquareUp CertificatePinner {1}: ${param.args[0]}")
                }
            }
        )

        findAndHookMethod(
            "com.squareup.okhttp.CertificatePinner",
            "check",
            String::class.java,
            List::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing SquareUp CertificatePinner {2}: ${param.args[0]}")
                }
            }
        )
    }
}