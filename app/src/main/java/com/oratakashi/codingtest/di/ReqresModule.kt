package com.oratakashi.codingtest.di

import com.oratakashi.codingtest.BuildConfig
import com.oratakashi.codingtest.data.RawgDataStore
import com.oratakashi.codingtest.data.RawgRepository
import com.oratakashi.codingtest.data.web.RawgApi
import com.oratakashi.codingtest.data.web.RawgApiClient
import com.oratakashi.codingtest.domain.HomeInteractor
import com.oratakashi.codingtest.domain.HomeUsecase
import com.oratakashi.codingtest.domain.SearchInteractor
import com.oratakashi.codingtest.domain.SearchUsecase
import com.oratakashi.codingtest.presentation.main.MainViewModel
import com.oratakashi.codingtest.presentation.search.SearchViewModel
import com.oratakashi.viewbinding.core.tools.retrofit.createReactiveService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val reqresModule = module {
    single {
        createReactiveService(
            RawgApiClient::class.java,
            get(),
            BuildConfig.BASE_URL
        )
    }

    single { RawgApi(get()) }

    single<RawgRepository> { RawgDataStore(get()) }

    single<HomeUsecase> { HomeInteractor(get()) }
    single<SearchUsecase> { SearchInteractor(get()) }

    viewModel { MainViewModel(get(), get()) }
    viewModel { SearchViewModel(get(), get()) }
}