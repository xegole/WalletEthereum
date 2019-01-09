package com.bigthinkapps.walletwesend.redux.middlewares

import com.bigthinkapps.walletwesend.model.EthereumData
import com.bigthinkapps.walletwesend.redux.actions.AddEthereumData
import com.bigthinkapps.walletwesend.redux.actions.DisplayBalances
import com.bigthinkapps.walletwesend.redux.states.AppState
import com.bigthinkapps.walletwesend.storage.MovieDBHelper
import org.rekotlin.DispatchFunction
import org.rekotlin.Middleware

internal val databaseMiddleWare: Middleware<AppState> = { dispatch, _ ->
    { next ->
        { action ->
            when (action) {
                is AddEthereumData -> {
                    insertEthereumAsync(action.ethereumObject, dispatch)
                }
            }
            next(action)
        }
    }
}

private fun insertEthereumAsync(ethereumObject: EthereumData, dispatch: DispatchFunction) {
    MovieDBHelper.insertEthereumAsync(ethereumObject) {
        displayAllBalances(dispatch)
    }
}

private fun displayAllBalances(dispatch: DispatchFunction) {
    MovieDBHelper.getStoredBalances { list ->
        list?.apply {
            dispatch(DisplayBalances(list))
        }
    }
}