package com.bigthinkapps.walletwesend

import android.app.Application
import android.arch.persistence.room.Room
import com.bigthinkapps.walletwesend.redux.middlewares.databaseMiddleWare
import com.bigthinkapps.walletwesend.redux.middlewares.ethereumMiddleWare
import com.bigthinkapps.walletwesend.redux.reducer.appReducer
import com.bigthinkapps.walletwesend.storage.EthereumDatabase
import com.bigthinkapps.walletwesend.util.Constants.BASE_DB
import org.rekotlin.Store

val store = Store(
        reducer = ::appReducer,
        state = null,
        middleware = listOf(ethereumMiddleWare, databaseMiddleWare)
)

class WalletApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        ethereumDataBase = Room
                .databaseBuilder(this, EthereumDatabase::class.java, BASE_DB)
                .build()
    }

    companion object {
        @get:Synchronized
        lateinit var instance: WalletApplication
            private set
        var ethereumDataBase: EthereumDatabase? = null
    }
}