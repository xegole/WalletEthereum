package com.bigthinkapps.walletwesend.redux.states

import com.bigthinkapps.walletwesend.model.EthereumData
import org.rekotlin.StateType

data class EthereumBalanceListState(var ethereumObjects: List<EthereumData>? = null) : StateType