package com.bigthinkapps.walletwesend.redux.middlewares

import com.bigthinkapps.walletwesend.redux.actions.DisplayBalances
import com.bigthinkapps.walletwesend.redux.actions.InitializeEthereumList
import com.bigthinkapps.walletwesend.redux.states.AppState
import com.bigthinkapps.walletwesend.storage.MovieDBHelper
import org.rekotlin.DispatchFunction
import org.rekotlin.Middleware

internal val ethereumMiddleWare: Middleware<AppState> = { dispatch, _ ->
    { next ->
        { action ->
            when (action) {
                is InitializeEthereumList -> {
                    getAllEthereumDataList(dispatch)
                }
            }
            next(action)
        }
    }
}

private fun getAllEthereumDataList(dispatch: DispatchFunction) {
    MovieDBHelper.getStoredBalances { list ->
        list?.apply {
            dispatch(DisplayBalances(list))
        }
    }
}