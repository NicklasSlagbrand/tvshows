package com.nicklasslagbrand.tvshow.core.di

import com.nicklasslagbrand.tvshow.data.datasource.local.LocalGithubRepoRepository
import com.nicklasslagbrand.tvshow.data.datasource.remote.RemoteGithubReposRepository
import com.nicklasslagbrand.tvshow.data.network.NetworkConnectionChecker
import com.nicklasslagbrand.tvshow.data.network.NetworkConnectionChecker.AndroidNetworkConnectionChecker
import com.nicklasslagbrand.tvshow.data.network.createGithubApi
import com.nicklasslagbrand.tvshow.data.time.AndroidTimeHandler
import com.nicklasslagbrand.tvshow.domain.TimeHandler
import com.nicklasslagbrand.tvshow.domain.repository.GithubRepository
import com.nicklasslagbrand.tvshow.domain.usecase.GetRepoListUseCase
import com.nicklasslagbrand.tvshow.feature.repo.ReposViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun androidPlatformModule() = module {
    single { AndroidTimeHandler() as TimeHandler }
    single { AndroidNetworkConnectionChecker(get()) as NetworkConnectionChecker }
    single { Dispatchers.IO }
}

fun generalAppModule(baseUrl: String, networkLogging: Boolean) = module {
    single {
        createGithubApi(
            debug = networkLogging,
            baseUrl = baseUrl,
            connectionChecker = get()
        )
    }
    single { LocalGithubRepoRepository() }
    single { RemoteGithubReposRepository(get()) }
    single { GithubRepository(get(), get()) }
}

fun useCaseAndViewModelModule() = module {
    factory {
        GetRepoListUseCase(get())
    }

    viewModel { ReposViewModel(get(), get()) }
}
