package com.nicklasslagbrand.tvshow.domain.repository

import com.nicklasslagbrand.tvshow.data.datasource.LocalFileStorage
import com.nicklasslagbrand.tvshow.data.entitiy.toTwShowModel
import com.nicklasslagbrand.tvshow.domain.error.Error
import com.nicklasslagbrand.tvshow.domain.model.TvShowModel
import com.nicklasslagbrand.tvshow.domain.result.Result
import com.nicklasslagbrand.tvshow.domain.result.wrapResult

class TvShowRepository(
    private val localRepository: LocalFileStorage
) {
    fun getTvShows(): Result<TvShowModel, Error> {
        return wrapResult { localRepository.getTvShow().toTwShowModel() }
    }
}
