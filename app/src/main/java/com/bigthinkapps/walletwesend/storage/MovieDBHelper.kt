package com.bigthinkapps.walletwesend.storage

import com.bigthinkapps.walletwesend.WalletApplication
import com.bigthinkapps.walletwesend.helpers.asyncRxExecutor
import com.bigthinkapps.walletwesend.model.EthereumData

class MovieDBHelper {

    companion object {

        fun insertEthereumAsync(ethereumObject: EthereumData, listener: () -> Unit) {
            asyncRxExecutor(
                    heavyFunction = { WalletApplication.ethereumDataBase?.ethereumDao()?.insert(ethereumObject) },
                    response = { listener.invoke() }
            )
        }

        fun getStoredBalances(listener: (List<EthereumData>?) -> Unit) {
            asyncRxExecutor(
                    heavyFunction = { WalletApplication.ethereumDataBase?.ethereumDao()?.getAll() },
                    response = { listener.invoke(it) }
            )
        }
    }
}