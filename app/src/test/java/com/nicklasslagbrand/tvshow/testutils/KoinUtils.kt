package com.nicklasslagbrand.tvshow.testutils

import com.nicklasslagbrand.tvshow.core.di.generalAppModule
import com.nicklasslagbrand.tvshow.core.di.useCaseAndViewModelModule
import com.nicklasslagbrand.tvshow.data.network.NetworkConnectionChecker
import com.nicklasslagbrand.tvshow.domain.TimeHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.koin.core.KoinApplication
import org.koin.core.logger.EmptyLogger
import org.koin.core.module.Module
import org.koin.dsl.module

@Suppress("EXPERIMENTAL_API_USAGE")
fun startKoin(
    baseUrl: String = "",
    networkLogging: Boolean = false,
    overridesModule: Module
): KoinApplication {
    // Gather all required dependencies
    val allModules = listOf(
        generalAppModule(baseUrl, networkLogging),
        module {
            single<TimeHandler> { NoopTimeHandler() }
            single<NetworkConnectionChecker> { TestNetworkConnectionChecker(true) }
            single<CoroutineDispatcher> { TestCoroutineDispatcher() }
        },
        useCaseAndViewModelModule(),
        overridesModule
    )

    return org.koin.core.context.startKoin {
        modules(allModules)
        logger(EmptyLogger())
    }
}
