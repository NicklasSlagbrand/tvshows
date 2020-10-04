package com.nicklasslagbrand.tvshow.core.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.nicklasslagbrand.tvshow.core.viewmodel.ConsumableEvent

fun <T : Any, L : LiveData<ConsumableEvent<T>>> LifecycleOwner.observeEvents(liveData: L, body: (T) -> Unit) =
    liveData.observe(this, EventObserver {
        body(it)
    })
