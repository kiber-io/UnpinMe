package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.kiber.unpinme.logInfo

class FlutterJavaHook(lpparam: XC_LoadPackage.LoadPackageParam) : Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "diefferson.http_certificate_pinning.HttpCertificatePinning",
            "checkConnexion",
            String::class.java,
            List::class.java,
            Map::class.java,
            Int::class.java,
            String::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Boolean {
                    logInfo("[+] Bypassing Flutter HttpCertificatePinning for: ${param.args[0]}")
                    return true
                }
            }
        )

        findAndHookMethod(
            "com.macif.plugin.sslpinningplugin.SslPinningPlugin",
            "checkConnexion",
            String::class.java,
            List::class.java,
            Map::class.java,
            Int::class.java,
            String::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Boolean {
                    logInfo("[+] Bypassing Flutter SslPinningPlugin for: ${param.args[0]}")
                    return true
                }
            }
        )
    }
}