package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.kiber.unpinme.logInfo

class BoyeAbstractVerifierHook(lpparam: XC_LoadPackage.LoadPackageParam) : Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "ch.boye.httpclientandroidlib.conn.ssl.AbstractVerifier",
            "verify",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing Boye AbstractVerifier check for: ${param.args[0]}")
                }
            }
        )
    }
}