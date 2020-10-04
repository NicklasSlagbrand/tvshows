package com.nicklasslagbrand.tvshow.feature.tvshow

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicklasslagbrand.tvshow.R
import com.nicklasslagbrand.tvshow.core.extension.observeEvents
import com.nicklasslagbrand.tvshow.domain.model.TvShowModel
import com.nicklasslagbrand.tvshow.feature.base.BaseActivity
import com.nicklasslagbrand.tvshow.feature.tvshow.allEpisodeList.EpisodesAdapter
import kotlinx.android.synthetic.main.activity_tvshow.*
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowActivity : BaseActivity() {
    private val viewModel: TvShowViewModel by viewModel()

    override fun provideLayoutId() = R.layout.activity_tvshow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeToLiveData()
        initializeList()
    }

    private fun initializeList() {
        viewModel.getTvShow()
        rvRepos.layoutManager = LinearLayoutManager(this)
    }

    private fun subscribeToLiveData() {
        observeEvents(viewModel.eventsLiveData) {
            when (it) {
                is TvShowViewModel.Event.ShowList -> {
                    showTvShow(it.tvShow)
                }
            }
        }
        observeEvents(viewModel.failure, ::handleFailure)
    }

    private fun showTvShow(tvshow: TvShowModel) {
        rvRepos.adapter = EpisodesAdapter(this).apply {
            results = tvshow.allEpisodes
        }
    }
}
