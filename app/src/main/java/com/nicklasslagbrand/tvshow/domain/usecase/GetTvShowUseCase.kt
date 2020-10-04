package com.nicklasslagbrand.tvshow.domain.usecase

import com.nicklasslagbrand.tvshow.domain.error.Error
import com.nicklasslagbrand.tvshow.domain.model.TvShowModel
import com.nicklasslagbrand.tvshow.domain.repository.TvShowRepository
import com.nicklasslagbrand.tvshow.domain.result.Result

class GetTvShowUseCase(private val repository: TvShowRepository) :
    UseCase<TvShowModel, UseCase.None>() {
    override suspend fun call(params: None): Result<TvShowModel, Error> =
        repository.getTvShows()
}
