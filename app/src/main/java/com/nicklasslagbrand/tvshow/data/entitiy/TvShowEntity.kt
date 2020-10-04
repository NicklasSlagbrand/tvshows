package com.nicklasslagbrand.tvshow.data.entitiy

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.nicklasslagbrand.tvshow.domain.model.TvShowEpisodesModel
import com.nicklasslagbrand.tvshow.domain.model.TvShowModel

data class TvShowResponse(
    @SerializedName("seasons")
    val seasons: List<TvShowEpisodes>?,
    @SerializedName("title")
    val title: String?
)

fun TvShowResponse.toModel(): TvShowModel {
    val allEpisodes = getAllEpisodes(this)
    return TvShowModel(
        title = title ?: "",
        allEpisodes = allEpisodes.map {
            it.toModel()
        }
    )
}

data class TvShowEpisodes(
    @SerializedName("episodes")
    val episodes: List<TvShowEpisodeEntity>?
)

data class TvShowEpisodeEntity(
    @SerializedName("Title")
    val title: String?,
    @SerializedName("imdbRating")
    val imdbRating: String?,
    @SerializedName("Season")
    val season: String?,
    @SerializedName("Episode")
    val episode: String?,
    @SerializedName("Poster")
    val posterUrl: String?
)

fun TvShowEpisodeEntity.toModel(): TvShowEpisodesModel {
    return TvShowEpisodesModel(
        title = title ?: "",
        imdbRating = imdbRating ?: "",
        imageUrl = posterUrl ?: "",
        season = season ?: "",
        episode = episode ?: ""
    )
}

fun String.toTwShowModel(): TvShowModel {
    val tvShowResponse: TvShowResponse = Gson().fromJson(this, TvShowResponse::class.java)
    return getTvShowModel(tvShowResponse)
}

private fun getAllEpisodes(response: TvShowResponse): List<TvShowEpisodeEntity> {
    val episodes: MutableList<TvShowEpisodeEntity> = mutableListOf()

    response.seasons!!.forEach { it ->
        it.episodes!!.forEach {
            episodes.add(it)
        }
    }
    return episodes
}

private fun getTvShowModel(response: TvShowResponse): TvShowModel {
    val episodes: MutableList<TvShowEpisodesModel> = mutableListOf()

    response.seasons!!.forEach { it ->
        it.episodes!!.forEach {
            episodes.add(it.toModel())
        }
    }
    return TvShowModel(
        title = response.title ?: "",
        allEpisodes = episodes
    )
}
