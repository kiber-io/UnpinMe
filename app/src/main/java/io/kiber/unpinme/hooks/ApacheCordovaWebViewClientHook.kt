package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.kiber.unpinme.logInfo

class ApacheCordovaWebViewClientHook(lpparam: XC_LoadPackage.LoadPackageParam) : Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "org.apache.cordova.CordovaWebViewClient",
            "onReceivedSslError",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing Apache Cordova WebViewClient check")
                    val sslError = param.args[2]
                    XposedHelpers.callMethod(sslError, "proceed")
                }
            }
        )
    }
}