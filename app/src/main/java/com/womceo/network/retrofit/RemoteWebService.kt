package com.womceo.network.retrofit

import com.womceo.network.config.WebServiceConfig
import com.womceo.network.config.WebServiceConfig.Url.REMOTE_HOST
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.SocketFactory

class RemoteWebService<TRetrofitWebService> {

    fun create(
        tClass: Class<TRetrofitWebService>
    ): TRetrofitWebService {
        val okHttpClient = makeOkHttpClient(
            httpLoggingInterceptor = makeLoggingInterceptor()
        )
        return createRetrofit(
            okHttpClient = okHttpClient,
            tClass = tClass
        )
    }

    private fun makeOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .socketFactory(SocketFactory.getDefault())
            .connectTimeout(WebServiceConfig.Timeout.CONNECT, TimeUnit.SECONDS)
            .build()

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    private fun createRetrofit(
        okHttpClient: OkHttpClient,
        tClass: Class<TRetrofitWebService>
    ): TRetrofitWebService =
        Retrofit.Builder()
            .baseUrl(REMOTE_HOST)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(tClass)
}