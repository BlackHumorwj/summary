package com.kotlin.for2021.android.jetpack.coroutine

import okhttp3.internal.tls.OkHostnameVerifier.verify
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/11/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class SSLSocketClient {


    companion object {
        //获取这个SSLSocketFactory
        fun getSSLSocketFactory(): SSLSocketFactory {
            try {
                val sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, getTrustManager(), SecureRandom());
                return sslContext.socketFactory
            } catch (e: Exception) {
                throw   RuntimeException(e);
            }
        }

        //获取TrustManager
         fun getTrustManager(): Array<TrustManager> {
            return arrayOf(X509Trust())

        }


        //获取HostnameVerifier
        fun getHostnameVerifier(): HostnameVerifier {
            return HostnameVeri()

        }


    }


    class X509Trust : X509TrustManager {
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {

        }

        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return arrayOf<X509Certificate>()
        }

    }

    class HostnameVeri : HostnameVerifier{
        override fun verify(hostname: String?, session: SSLSession?): Boolean {
            return true
        }

    }



}