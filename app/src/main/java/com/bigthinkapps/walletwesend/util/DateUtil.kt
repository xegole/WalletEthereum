package com.bigthinkapps.walletwesend.util

import android.text.format.DateFormat
import java.util.*

object DateUtil {
    fun getDateStringFromDate(date: Date): String = DateFormat.format("dd MM yyyy hh:mm", date).toString()
}