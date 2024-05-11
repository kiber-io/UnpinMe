package io.kiber.unpinme.hooks

import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebView
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.kiber.unpinme.logInfo

class AndroidWebViewClientHook(lpparam: XC_LoadPackage.LoadPackageParam) : Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "android.webkit.WebViewClient",
            "onReceivedSslError",
            WebView::class.java,
            SslErrorHandler::class.java,
            SslError::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam?) {
                    logInfo("[+] Bypassing Android WebViewClient check")
                }
            }
        )
    }
}