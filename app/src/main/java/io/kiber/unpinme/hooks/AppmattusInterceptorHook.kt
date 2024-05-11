package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.kiber.unpinme.logInfo

class AppmattusInterceptorHook(lpparam: XC_LoadPackage.LoadPackageParam) : Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "com.appmattus.certificatetransparency.internal.verifier.CertificateTransparencyInterceptor",
            "intercept",
            "okhttp3.Interceptor\$Chain",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Any {
                    logInfo("[+] Bypassing Appmattus Interceptor")
                    val chain = param.args[0]
                    val request = XposedHelpers.callMethod(chain, "request")
                    return XposedHelpers.callMethod(chain, "proceed", request)
                }
            }
        )
    }
}

