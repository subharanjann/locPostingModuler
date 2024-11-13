package com.mobillor.goodsinwardmodule.data.apiclient

import com.mobillor.locpostingmodule.presentation.activity.LocPostingStarterActivity
import com.mobillor.locpostingmodule.data.apiService.LocationPostingApiInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

object NewApiClient {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .sslSocketFactory(createInsecureSslContext().socketFactory, createTrustAllManager())
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private fun createTrustAllManager(): X509TrustManager {
        return object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {

            }

            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {

            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
    }

    private fun createInsecureSslContext(): SSLContext {
        return SSLContext.getInstance("TLS").apply {
            init(null, arrayOf(createTrustAllManager()), null)
        }
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(LocPostingStarterActivity.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()



    fun getLocPostingService(): LocationPostingApiInterface {
        return retrofit.create(LocationPostingApiInterface::class.java)
    }

}