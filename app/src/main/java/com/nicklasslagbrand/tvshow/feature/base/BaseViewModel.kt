package com.nicklasslagbrand.tvshow.feature.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nicklasslagbrand.tvshow.core.viewmodel.ConsumableEvent
import com.nicklasslagbrand.tvshow.domain.error.Error

open class BaseViewModel : ViewModel() {
    val failure = MutableLiveData<ConsumableEvent<Error>>()

    fun handleFailure(error: Error) {
        this.failure.value = ConsumableEvent(error)
    }
}
