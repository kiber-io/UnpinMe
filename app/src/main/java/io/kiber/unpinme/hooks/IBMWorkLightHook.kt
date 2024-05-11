package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.kiber.unpinme.logInfo
import java.security.cert.X509Certificate
import javax.net.ssl.SSLSession
import javax.net.ssl.SSLSocket

class IBMWorkLightHook(lpparam: XC_LoadPackage.LoadPackageParam) : Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "com.worklight.wlclient.certificatepinning.HostNameVerifierWithCertificatePinning",
            "verify",
            String::class.java,
            SSLSocket::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing IBM WorkLight HostNameVerifierWithCertificatePinning {1}: ${param.args[0]}")
                }
            }
        )

        findAndHookMethod(
            "com.worklight.wlclient.certificatepinning.HostNameVerifierWithCertificatePinning",
            "verify",
            String::class.java,
            Array<String>::class.java,
            Array<String>::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing IBM WorkLight HostNameVerifierWithCertificatePinning {2}: ${param.args[0]}")
                }
            }
        )

        findAndHookMethod(
            "com.worklight.wlclient.certificatepinning.HostNameVerifierWithCertificatePinning",
            "verify",
            String::class.java,
            X509Certificate::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing IBM WorkLight HostNameVerifierWithCertificatePinning {3}: ${param.args[0]}")
                }
            }
        )

        findAndHookMethod(
            "com.worklight.wlclient.certificatepinning.HostNameVerifierWithCertificatePinning",
            "verify",
            String::class.java,
            SSLSession::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing IBM WorkLight HostNameVerifierWithCertificatePinning {4}: ${param.args[0]}")
                }
            }
        )
    }
}