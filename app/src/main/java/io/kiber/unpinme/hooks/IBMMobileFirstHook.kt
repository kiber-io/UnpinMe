package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.kiber.unpinme.logInfo

class IBMMobileFirstHook(lpparam: XC_LoadPackage.LoadPackageParam) : Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "com.worklight.wlclient.api.WLClient",
            "pinTrustedCertificatePublicKey",
            String::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing IBM MobileFirst pinTrustedCertificatePublicKey {1}: ${param.args[0]}")
                }
            }
        )

        findAndHookMethod(
            "com.worklight.wlclient.api.WLClient",
            "pinTrustedCertificatePublicKey",
            Array<String>::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing IBM MobileFirst pinTrustedCertificatePublicKey {2}: ${param.args[0]}")
                }
            }
        )
    }
}