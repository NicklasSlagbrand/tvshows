package com.nicklasslagbrand.tvshow.feature.repo

import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import com.nicklasslagbrand.tvshow.R
import com.nicklasslagbrand.tvshow.core.extension.observeEvents
import com.nicklasslagbrand.tvshow.feature.base.BaseActivity
import kotlinx.android.synthetic.main.fragment_repo_list.*
import timber.log.Timber
import org.koin.android.viewmodel.ext.android.viewModel


class ReposActivity : BaseActivity() {
    private val viewModel: ReposViewModel by viewModel()

    override fun provideLayoutId() = R.layout.fragment_repo_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeToLiveData()
        initializeList()
    }

    private fun initializeList() {
        viewModel.getReposList()
        rvRepos.layoutManager = LinearLayoutManager(this)
    }

    private fun subscribeToLiveData() {
        observeEvents(viewModel.eventsLiveData) {
            when (it) {
                is ReposViewModel.Event.ShowList -> {
                    Timber.d("RESULT: ${it.json}")
                }
            }
        }
        observeEvents(viewModel.failure, ::handleFailure)
    }
}
