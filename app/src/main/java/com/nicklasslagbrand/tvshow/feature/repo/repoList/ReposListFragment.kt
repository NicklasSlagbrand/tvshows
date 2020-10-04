package com.nicklasslagbrand.tvshow.feature.repo.repoList

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicklasslagbrand.tvshow.R
import com.nicklasslagbrand.tvshow.core.extension.observeEvents
import com.nicklasslagbrand.tvshow.feature.base.BaseFragment
import com.nicklasslagbrand.tvshow.feature.repo.ReposViewModel
import kotlinx.android.synthetic.main.fragment_repo_list.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class ReposListFragment : BaseFragment() {
    private val viewModel: ReposViewModel by sharedViewModel()
    override fun provideLayoutId(): Int? = R.layout.fragment_repo_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToLiveData()
        initializeList()
    }
    private fun initializeList() {
        viewModel.getReposList()
        rvRepos.layoutManager = LinearLayoutManager(requireContext())
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
