package com.nicklasslagbrand.tvshow.core.extension

import androidx.lifecycle.Observer
import com.nicklasslagbrand.tvshow.data.viewmodel.ConsumableEvent

class EventObserver<T>(private val onEventUnconsumedContent: (T) -> Unit) : Observer<ConsumableEvent<T>> {
    override fun onChanged(event: ConsumableEvent<T>) {
        event.consume(onEventUnconsumedContent)
    }
}
