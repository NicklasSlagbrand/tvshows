package com.nicklasslagbrand.tvshow.feature.tvshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nicklasslagbrand.tvshow.core.viewmodel.ConsumableEvent
import com.nicklasslagbrand.tvshow.domain.model.TvShowModel
import com.nicklasslagbrand.tvshow.domain.usecase.GetTvShowUseCase
import com.nicklasslagbrand.tvshow.domain.usecase.UseCase
import com.nicklasslagbrand.tvshow.feature.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TvShowViewModel(
    private val getTvShowUseCase: GetTvShowUseCase,
    private val backgroundDispatcher: CoroutineDispatcher
) : BaseViewModel() {
    val eventsLiveData = MutableLiveData<ConsumableEvent<Event>>()

    fun getTvShow() {
        viewModelScope.launch {
            val result = withContext(backgroundDispatcher) {
                getTvShowUseCase.call(UseCase.None)
            }
            result.fold(
                {
                    eventsLiveData.value = ConsumableEvent(Event.ShowList(it))
                }, ::handleFailure
            )
        }
    }
    sealed class Event {
        data class ShowList(val tvShow: TvShowModel) : Event()
    }
}
