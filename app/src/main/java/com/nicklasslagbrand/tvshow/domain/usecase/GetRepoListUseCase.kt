package com.nicklasslagbrand.tvshow.domain.usecase

import com.nicklasslagbrand.tvshow.domain.error.Error
import com.nicklasslagbrand.tvshow.domain.model.GithubRepo
import com.nicklasslagbrand.tvshow.domain.repository.GithubRepository
import com.nicklasslagbrand.tvshow.domain.result.Result

class GetRepoListUseCase(private val repository: GithubRepository) :
    UseCase<List<GithubRepo>, PagingParams>() {
    override suspend fun call(params: PagingParams): Result<List<GithubRepo>, Error> =
        repository.getAndroidRepos(params.page)
}

data class PagingParams(
    val page: Long
)
