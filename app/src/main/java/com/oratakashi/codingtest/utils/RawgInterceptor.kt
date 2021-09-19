package com.oratakashi.codingtest.utils

import com.oratakashi.codingtest.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RawgInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder().url(
                chain.request().url
                    .newBuilder()
                    .addQueryParameter("key", BuildConfig.TOKEN)
                    .addQueryParameter("platforms", "4")
                    .build()
            ).build()
        )
    }
}