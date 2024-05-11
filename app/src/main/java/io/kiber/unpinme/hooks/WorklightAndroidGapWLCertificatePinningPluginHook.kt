package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.kiber.unpinme.logInfo
import org.json.JSONArray

class WorklightAndroidGapWLCertificatePinningPluginHook(lpparam: XC_LoadPackage.LoadPackageParam) :
    Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "com.worklight.androidgap.plugin.WLCertificatePinningPlugin",
            "execute",
            String::class.java,
            JSONArray::class.java,
            "org.apache.cordova.CallbackContext",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing Worklight Androidgap WLCertificatePinningPlugin: ${param.args[0]}")
                }
            }
        )
    }
}