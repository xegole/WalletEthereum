package com.bigthinkapps.walletwesend.storage

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.bigthinkapps.walletwesend.model.EthereumData

@Dao
interface EthereumDao {

    @Query("SELECT * from balances")
    fun getAll(): List<EthereumData>

    @Insert
    fun insertAll(vararg ethereumObject: EthereumData)

    @Insert
    fun insert(ethereumObject: EthereumData)

    @Delete
    fun delete(ethereumObject: EthereumData)
}