package com.nicklasslagbrand.tvshow.data.datasource.remote

import com.nicklasslagbrand.tvshow.data.network.GithubApi
import com.nicklasslagbrand.tvshow.domain.model.GithubRepo

class RemoteGithubReposRepository(
    private val githubApi: GithubApi
) {
    suspend fun getAndroidRepos(page: Long): List<GithubRepo> {
        return githubApi.getAndroidRepos(page)
    }
}
