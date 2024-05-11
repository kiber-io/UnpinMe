package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.kiber.unpinme.logInfo

class ConscryptCertPinManagerLegacyHook(lpparam: XC_LoadPackage.LoadPackageParam) : Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "com.android.org.conscrypt.CertPinManager",
            "isChainValid",
            String::class.java,
            List::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing Conscrypt CertPinManager (Legacy): ${param.args[0]}")
                }
            }
        )
    }
}