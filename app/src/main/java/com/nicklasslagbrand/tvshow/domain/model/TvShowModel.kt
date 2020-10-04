package com.nicklasslagbrand.tvshow.domain.model

data class TvShowModel(
    val title: String,
    val allEpisodes: List<TvShowEpisodesModel>
)

data class TvShowEpisodesModel(
    val title: String,
    val imdbRating: String,
    val season: String,
    val episode: String,
    val imageUrl: String
)
