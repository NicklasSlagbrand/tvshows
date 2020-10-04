package com.nicklasslagbrand.tvshow.domain

interface TimeHandler {
    fun isBeforeNow(date: String): Boolean
}
