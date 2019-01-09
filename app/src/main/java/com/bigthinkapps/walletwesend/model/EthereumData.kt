package com.bigthinkapps.walletwesend.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.bigthinkapps.walletwesend.helpers.DateConverter
import java.util.*

@Entity(tableName = "balances")
@TypeConverters(DateConverter::class)
data class EthereumData(
        @ColumnInfo(name = "address") val address: String,
        @ColumnInfo(name = "balance") val balance: String,
        @ColumnInfo(name = "last_update") val lastUpdated: Date) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}