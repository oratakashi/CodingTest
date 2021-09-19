package com.oratakashi.codingtest.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.oratakashi.codingtest.BuildConfig
import com.oratakashi.codingtest.utils.RawgInterceptor
import com.oratakashi.viewbinding.core.tools.retrofit.createOkHttpClient
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val libModule = module {
    single {
        RawgInterceptor()
    }

    single { CompositeDisposable() }

    single {
        createOkHttpClient(
            interceptors = arrayOf(
                get<RawgInterceptor>(),
                ChuckerInterceptor(androidContext())
            ),
            authenticator = null,
            showDebugLog = BuildConfig.DEBUG,
            pinner = null
        )
    }
}