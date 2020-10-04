package com.nicklasslagbrand.tvshow.core.di

import com.nicklasslagbrand.tvshow.data.datasource.LocalFileStorage
import com.nicklasslagbrand.tvshow.domain.repository.TvShowRepository
import com.nicklasslagbrand.tvshow.domain.usecase.GetTvShowUseCase
import com.nicklasslagbrand.tvshow.feature.tvshow.TvShowViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidPlatformModule = module {
    single { Dispatchers.IO }
}

val generalAppModule = module {
    single { LocalFileStorage(get()) }
    single { TvShowRepository(get()) }
}

val useCaseModule = module {
    factory {
        GetTvShowUseCase(get())
    }
}

val viewModelModule = module {
    viewModel { TvShowViewModel(get(), get()) }
}
