package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.kiber.unpinme.logInfo

class CWACNetsecurityHook(lpparam: XC_LoadPackage.LoadPackageParam) : Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "com.commonsware.cwac.netsecurity.conscrypt.CertPinManager",
            "isChainValid",
            String::class.java,
            List::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Boolean {
                    logInfo("[+] Bypassing CWAC-Netsecurity CertPinManager: ${param.args[0]}")
                    return true
                }
            }
        )
    }
}