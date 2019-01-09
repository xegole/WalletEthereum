package com.bigthinkapps.walletwesend.redux.reducer

import com.bigthinkapps.walletwesend.redux.states.AppState
import org.rekotlin.Action

fun appReducer(action: Action, appState: AppState?): AppState =
        AppState(
                ethereumBalanceListState = ethereumListReducer(action, appState?.ethereumBalanceListState)
        )