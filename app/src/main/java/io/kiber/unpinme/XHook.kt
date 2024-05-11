package io.kiber.unpinme

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.kiber.unpinme.hooks.AndroidWebViewClientHook
import io.kiber.unpinme.hooks.ApacheAbstractVerifierHook
import io.kiber.unpinme.hooks.ApacheCordovaWebViewClientHook
import io.kiber.unpinme.hooks.AppceleratorHook
import io.kiber.unpinme.hooks.AppmattusHostnameVerifierHook
import io.kiber.unpinme.hooks.AppmattusInterceptorHook
import io.kiber.unpinme.hooks.BoyeAbstractVerifierHook
import io.kiber.unpinme.hooks.CWACNetsecurityHook
import io.kiber.unpinme.hooks.ChromiumCronetHook
import io.kiber.unpinme.hooks.ConscryptCertPinManagerHook
import io.kiber.unpinme.hooks.ConscryptCertPinManagerLegacyHook
import io.kiber.unpinme.hooks.FlutterJavaHook
import io.kiber.unpinme.hooks.IBMMobileFirstHook
import io.kiber.unpinme.hooks.IBMWorkLightHook
import io.kiber.unpinme.hooks.NettyFingerprintTrustManagerFactoryHook
import io.kiber.unpinme.hooks.OkHttp3Hook
import io.kiber.unpinme.hooks.OpenSSLEngineSocketImplHook
import io.kiber.unpinme.hooks.OpenSSLSocketImplApacheHarmonyHook
import io.kiber.unpinme.hooks.OpenSSLSocketImplConscryptHook
import io.kiber.unpinme.hooks.PhoneGapHook
import io.kiber.unpinme.hooks.SquareUpCertificatePinnerHook
import io.kiber.unpinme.hooks.SquareUpOkHostnameVerifierHook
import io.kiber.unpinme.hooks.TrustKitHook
import io.kiber.unpinme.hooks.TrustManagerHook
import io.kiber.unpinme.hooks.TrustManagerImplHook
import io.kiber.unpinme.hooks.WorklightAndroidGapWLCertificatePinningPluginHook

class XHook : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
        if (lpparam == null || lpparam.packageName.equals(BuildConfig.APPLICATION_ID)) return
        hook(lpparam)
    }

    private fun hook(lpparam: XC_LoadPackage.LoadPackageParam) {
        TrustManagerHook(lpparam).hook()
        TrustManagerImplHook(lpparam).hook()
        OkHttp3Hook(lpparam).hook()
        TrustKitHook(lpparam).hook()
        AppceleratorHook(lpparam).hook()
        OpenSSLSocketImplConscryptHook(lpparam).hook()
        OpenSSLEngineSocketImplHook(lpparam).hook()
        OpenSSLSocketImplApacheHarmonyHook(lpparam).hook()
        PhoneGapHook(lpparam).hook()
        IBMMobileFirstHook(lpparam).hook()
        IBMWorkLightHook(lpparam).hook()
        ConscryptCertPinManagerHook(lpparam).hook()
        ConscryptCertPinManagerLegacyHook(lpparam).hook()
        CWACNetsecurityHook(lpparam).hook()
        WorklightAndroidGapWLCertificatePinningPluginHook(lpparam).hook()
        NettyFingerprintTrustManagerFactoryHook(lpparam).hook()
        SquareUpCertificatePinnerHook(lpparam).hook()
        SquareUpOkHostnameVerifierHook(lpparam).hook()
        AppmattusHostnameVerifierHook(lpparam).hook()
        AppmattusInterceptorHook(lpparam).hook()
        AndroidWebViewClientHook(lpparam).hook()
        ApacheCordovaWebViewClientHook(lpparam).hook()
        BoyeAbstractVerifierHook(lpparam).hook()
        ApacheAbstractVerifierHook(lpparam).hook()
        ChromiumCronetHook(lpparam).hook()
        FlutterJavaHook(lpparam).hook()
    }
}