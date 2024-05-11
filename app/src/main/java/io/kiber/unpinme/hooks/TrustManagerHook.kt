package io.kiber.unpinme.hooks

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam
import io.kiber.unpinme.logInfo
import java.net.Socket
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.KeyManager
import javax.net.ssl.SSLEngine
import javax.net.ssl.TrustManager
import javax.net.ssl.X509ExtendedTrustManager
import javax.net.ssl.X509TrustManager

class TrustManagerHook(lpparam: LoadPackageParam) : Hook(lpparam) {
    override fun hook() {
        findAndHookMethod(
            "javax.net.ssl.SSLContext",
            "init",
            Array<KeyManager>::class.java,
            Array<TrustManager>::class.java,
            SecureRandom::class.java,
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    logInfo("[+] Bypassing TrustManager (Android < 7) pinner")
                    param.args[1] = arrayOf(getTrustManager())
                }
            })
    }

    private fun getTrustManager(): TrustManager {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FuckX509ExtendedTrustManager()
        } else {
            FuckX509TrustManager()
        }
    }

    @SuppressLint("CustomX509TrustManager")
    class FuckX509TrustManager : X509TrustManager {
        @SuppressLint("TrustAllX509TrustManager")
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}

        @SuppressLint("TrustAllX509TrustManager")
        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return arrayOf()
        }
    }

    @SuppressLint("CustomX509TrustManager")
    @TargetApi(Build.VERSION_CODES.N)
    class FuckX509ExtendedTrustManager : X509ExtendedTrustManager() {
        @SuppressLint("TrustAllX509TrustManager")
        override fun checkClientTrusted(
            chain: Array<out X509Certificate>?,
            authType: String?,
            socket: Socket?
        ) {}

        @SuppressLint("TrustAllX509TrustManager")
        override fun checkClientTrusted(
            chain: Array<out X509Certificate>?,
            authType: String?,
            engine: SSLEngine?
        ) {}

        @SuppressLint("TrustAllX509TrustManager")
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}

        @SuppressLint("TrustAllX509TrustManager")
        override fun checkServerTrusted(
            chain: Array<out X509Certificate>?,
            authType: String?,
            socket: Socket?
        ) {}

        @SuppressLint("TrustAllX509TrustManager")
        override fun checkServerTrusted(
            chain: Array<out X509Certificate>?,
            authType: String?,
            engine: SSLEngine?
        ) {}

        @SuppressLint("TrustAllX509TrustManager")
        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return arrayOf()
        }
    }
}