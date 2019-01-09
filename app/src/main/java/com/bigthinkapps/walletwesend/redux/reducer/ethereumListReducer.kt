package com.bigthinkapps.walletwesend.redux.reducer

import com.bigthinkapps.walletwesend.redux.actions.DisplayBalances
import com.bigthinkapps.walletwesend.redux.states.EthereumBalanceListState
import org.rekotlin.Action

fun ethereumListReducer(action: Action, ethereumBalanceListState: EthereumBalanceListState?): EthereumBalanceListState {
    var state = ethereumBalanceListState ?: EthereumBalanceListState()
    when (action) {
        is DisplayBalances -> {
            state = state.copy(ethereumObjects = action.ethereumObjects)
        }
    }
    return state
}