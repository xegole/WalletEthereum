package com.bigthinkapps.walletwesend.redux.actions

import com.bigthinkapps.walletwesend.model.EthereumData
import org.rekotlin.Action


class InitializeEthereumList : Action

class DisplayBalances(val ethereumObjects: List<EthereumData>) : Action

class AddEthereumData(val ethereumObject: EthereumData) : Action