package com.nicklasslagbrand.tvshow.feature.tvshow.allEpisodeList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicklasslagbrand.tvshow.R
import com.nicklasslagbrand.tvshow.core.extension.loadImageWithCenterCropTransform
import com.nicklasslagbrand.tvshow.domain.model.TvShowEpisodesModel
import kotlin.properties.Delegates
import kotlinx.android.synthetic.main.item_episode.view.*

class EpisodesAdapter(
    private val context: Context
) :
    RecyclerView.Adapter<EpisodesAdapter.EpisodesListViewHolder>() {

    var results: List<TvShowEpisodesModel> by Delegates.observable(
        emptyList()
    ) { _, _, _ ->
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesListViewHolder =
        EpisodesListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_episode,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: EpisodesListViewHolder, position: Int) {
        holder.bind(results[position], context)
    }

    override fun getItemCount() = results.size

    class EpisodesListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(tvshow: TvShowEpisodesModel, context: Context) {

            view.tvTitle.text = "${tvshow.episode}. ${tvshow.title}"
            view.tvSeason.text = "${context.getString(R.string.season_placeholder)} " +
                    "${tvshow.season}"

            view.ivPoster.loadImageWithCenterCropTransform(
                tvshow.imageUrl ?: "")
        }
    }
}
