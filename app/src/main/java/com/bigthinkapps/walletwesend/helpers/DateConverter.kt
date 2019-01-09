package com.bigthinkapps.walletwesend.helpers

import android.arch.persistence.room.TypeConverter
import java.util.*


open class DateConverter {

    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return if (dateLong == null) null else Date(dateLong)
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return (date?.time)!!.toLong()
    }
}