package com.nicklasslagbrand.tvshow.data.entity.converter

import com.nicklasslagbrand.tvshow.data.entity.RealmOwner
import com.nicklasslagbrand.tvshow.data.entity.RealmRepo
import com.nicklasslagbrand.tvshow.domain.model.GithubRepo
import com.nicklasslagbrand.tvshow.domain.model.Owner

object RealmGithubRepoConverter {
    fun toRepo(realmRepo: RealmRepo): GithubRepo {
        with(realmRepo) {
            return GithubRepo(id, title, description, Owner(owner?.avatarUrl))
        }
    }

    fun fromRepo(githubRepo: GithubRepo): RealmRepo {
        with(githubRepo) {
            return RealmRepo(id, title, description ?: "",
                RealmOwner(owner.avatarUrl ?: ""))
        }
    }
}
