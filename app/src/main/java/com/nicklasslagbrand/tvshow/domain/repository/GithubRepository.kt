package com.nicklasslagbrand.tvshow.domain.repository

import com.nicklasslagbrand.tvshow.data.datasource.LocalFileStorage
import com.nicklasslagbrand.tvshow.domain.error.Error
import com.nicklasslagbrand.tvshow.domain.result.Result
import com.nicklasslagbrand.tvshow.domain.result.wrapResult

class GithubRepository(
    private val localRepository: LocalFileStorage
) {
    fun getTvShows(): Result<String, Error> {
        return wrapResult {
            localRepository.getTvShow()
        }
    }
}
