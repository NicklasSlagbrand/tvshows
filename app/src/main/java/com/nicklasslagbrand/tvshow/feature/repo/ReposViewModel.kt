package com.nicklasslagbrand.tvshow.feature.repo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nicklasslagbrand.tvshow.core.viewmodel.ConsumableEvent
import com.nicklasslagbrand.tvshow.domain.usecase.GetRepoListUseCase
import com.nicklasslagbrand.tvshow.domain.usecase.UseCase
import com.nicklasslagbrand.tvshow.feature.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReposViewModel(
    private val getRepoListUseCase: GetRepoListUseCase,
    private val backgroundDispatcher: CoroutineDispatcher
) : BaseViewModel() {
    val eventsLiveData = MutableLiveData<ConsumableEvent<Event>>()

    fun getReposList() {
        viewModelScope.launch(backgroundDispatcher) {
            val result = withContext(backgroundDispatcher) {
                getRepoListUseCase.call(UseCase.None)
            }
            result.fold(
                {
                    eventsLiveData.value = ConsumableEvent(Event.ShowList(it))
                }, ::handleFailure
            )
        }
    }
    sealed class Event {
        data class ShowList(val json: String) : Event()
    }
}

