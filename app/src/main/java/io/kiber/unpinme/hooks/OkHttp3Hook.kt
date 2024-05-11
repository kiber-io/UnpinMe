package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam
import io.kiber.unpinme.logInfo
import java.security.cert.Certificate

class OkHttp3Hook(lpparam: LoadPackageParam) : Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "okhttp3.CertificatePinner",
            "check",
            String::class.java,
            List::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing OkHTTPv3 {1}: ${param.args[0]}")
                }
            })

        findAndHookMethod(
            "okhttp3.CertificatePinner",
            "check",
            String::class.java,
            Certificate::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing OkHTTPv3 {2}: ${param.args[0]}")
                }
            })

        findAndHookMethod(
            "okhttp3.CertificatePinner",
            "check",
            String::class.java,
            Array<Certificate>::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing OkHTTPv3 {3}: ${param.args[0]}")
                }
            })

        findAndHookMethod(
            "okhttp3.CertificatePinner",
            "check\$okhttp",
            String::class.java,
            "kotlin.jvm.functions.Function0",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing OkHTTPv3 {4}: ${param.args[0]}")
                }
            })
    }
}