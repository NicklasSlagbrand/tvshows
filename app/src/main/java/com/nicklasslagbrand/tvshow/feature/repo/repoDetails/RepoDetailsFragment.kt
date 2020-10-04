package com.nicklasslagbrand.tvshow.feature.repo.repoDetails

import android.os.Bundle
import android.view.View
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.Chip
import com.nicklasslagbrand.tvshow.R
import com.nicklasslagbrand.tvshow.core.extension.loadImageWithFitCenterTransform
import com.nicklasslagbrand.tvshow.domain.model.GithubRepo
import com.nicklasslagbrand.tvshow.feature.base.BaseFragment
import com.nicklasslagbrand.tvshow.feature.repo.ReposViewModel
import kotlinx.android.synthetic.main.fragment_repo_details.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class RepoDetailsFragment : BaseFragment() {
    private val viewModel: ReposViewModel by sharedViewModel()
    private lateinit var githubRepo: GithubRepo

    override fun provideLayoutId() = R.layout.fragment_repo_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        githubRepo = viewModel.activeGithubRepo

        ivAvatar.loadImageWithFitCenterTransform(
            githubRepo.owner.avatarUrl ?: "",
            RequestOptions.circleCropTransform()
        )

        tvTitle.text = githubRepo.title
        tvDescription.text = githubRepo.description

        val exampleTags = listOf("Android", "Github", "Test")

        createSkillsChips(exampleTags)
    }

    private fun createSkillsChips(skills: List<String>) {
        skills.onEach {
            val chip = Chip(requireContext(), null, R.style.Chip_Skills)
            chip.text = it

            cgSkillsGroup.addView(chip)
        }
    }
}
