package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.kiber.unpinme.logInfo
import org.json.JSONArray

class PhoneGapHook(lpparam: XC_LoadPackage.LoadPackageParam) : Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "nl.xservices.plugins.sslCertificateChecker",
            "execute",
            String::class.java,
            JSONArray::class.java,
            "org.apache.cordova.CallbackContext",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Boolean {
                    logInfo("[+] Bypassing PhoneGap sslCertificateChecker: ${param.args[0]}")
                    return true
                }
            }
        )
    }
}