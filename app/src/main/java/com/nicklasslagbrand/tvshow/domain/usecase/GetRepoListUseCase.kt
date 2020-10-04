package com.nicklasslagbrand.tvshow.domain.usecase

import com.nicklasslagbrand.tvshow.domain.error.Error
import com.nicklasslagbrand.tvshow.domain.model.GithubRepo
import com.nicklasslagbrand.tvshow.domain.repository.GithubRepository
import com.nicklasslagbrand.tvshow.domain.result.Result

class GetRepoListUseCase(private val repository: GithubRepository) :
    UseCase<String, UseCase.None>() {
    override suspend fun call(params: None): Result<String, Error> =
        repository.getTvShows()
}

data class PagingParams(
    val page: Long
)
