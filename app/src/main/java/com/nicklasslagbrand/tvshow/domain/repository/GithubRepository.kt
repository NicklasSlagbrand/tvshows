package com.nicklasslagbrand.tvshow.domain.repository

import com.nicklasslagbrand.tvshow.data.datasource.local.LocalGithubRepoRepository
import com.nicklasslagbrand.tvshow.data.datasource.remote.RemoteGithubReposRepository
import com.nicklasslagbrand.tvshow.domain.error.Error
import com.nicklasslagbrand.tvshow.domain.model.GithubRepo
import com.nicklasslagbrand.tvshow.domain.result.Result
import com.nicklasslagbrand.tvshow.domain.result.wrapResult

class GithubRepository(
    private val localRepository: LocalGithubRepoRepository,
    private val remoteRepository: RemoteGithubReposRepository
) {
    suspend fun getAndroidRepos(page: Long): Result<List<GithubRepo>, Error> {
        return wrapResult {
            remoteRepository.getAndroidRepos(page).also {
                localRepository.storeRepoList(it)
            }
        }
    }
}
