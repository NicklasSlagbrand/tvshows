package com.nicklasslagbrand.tvshow.core.di

import com.nicklasslagbrand.tvshow.data.datasource.LocalFileStorage
import com.nicklasslagbrand.tvshow.data.time.AndroidTimeHandler
import com.nicklasslagbrand.tvshow.domain.TimeHandler
import com.nicklasslagbrand.tvshow.domain.repository.GithubRepository
import com.nicklasslagbrand.tvshow.domain.usecase.GetRepoListUseCase
import com.nicklasslagbrand.tvshow.feature.repo.ReposViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidPlatformModule = module {
    single { AndroidTimeHandler() as TimeHandler }
    single { Dispatchers.IO }
}

val generalAppModule = module {
    single { LocalFileStorage(get()) }
    single { GithubRepository(get()) }
}

val useCaseModule = module {
    factory {
        GetRepoListUseCase(get())
    }
}

val viewModelModule = module {
    viewModel { ReposViewModel(get(), get()) }
}
