package com.nicklasslagbrand.tvshow.testutils

import com.nicklasslagbrand.tvshow.domain.TimeHandler

class NoopTimeHandler : TimeHandler {
    override fun isBeforeNow(date: String) = false
}
