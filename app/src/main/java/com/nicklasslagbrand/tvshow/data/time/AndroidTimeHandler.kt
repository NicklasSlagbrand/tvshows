package com.nicklasslagbrand.tvshow.data.time

import com.nicklasslagbrand.tvshow.domain.TimeHandler
import org.joda.time.DateTime

class AndroidTimeHandler : TimeHandler {
    override fun isBeforeNow(date: String) = DateTime.parse(date).isBeforeNow
}
