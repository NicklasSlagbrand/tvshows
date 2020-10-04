package com.nicklasslagbrand.tvshow.domain.usecase

import com.nicklasslagbrand.tvshow.domain.error.Error
import com.nicklasslagbrand.tvshow.domain.result.Result

abstract class UseCase<Output : Any, in Params : Any> {
    abstract suspend fun call(params: Params): Result<Output, Error>

    object None
}
