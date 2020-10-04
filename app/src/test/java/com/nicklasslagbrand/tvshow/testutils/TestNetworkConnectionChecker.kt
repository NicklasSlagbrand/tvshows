package com.nicklasslagbrand.tvshow.testutils

import com.nicklasslagbrand.tvshow.data.network.NetworkConnectionChecker

class TestNetworkConnectionChecker(var isConnected: Boolean = false) :
    NetworkConnectionChecker {
    override val isNotConnected: Boolean
        get() = !isConnected
}
