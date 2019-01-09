package com.bigthinkapps.walletwesend.storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.bigthinkapps.walletwesend.model.EthereumData

@Database(entities = [(EthereumData::class)], version = 1)
abstract class EthereumDatabase : RoomDatabase() {
    abstract fun ethereumDao(): EthereumDao
}