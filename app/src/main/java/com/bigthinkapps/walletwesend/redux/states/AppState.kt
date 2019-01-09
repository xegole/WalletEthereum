package com.bigthinkapps.walletwesend.redux.states

import org.rekotlin.StateType

data class AppState(
        var ethereumBalanceListState: EthereumBalanceListState? = null
) : StateType