package com.annevonwolffen.behancer.utils

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

object DateUtils {

    @JvmStatic
    fun formatLongToString(millis: Long): String {
        val date = Date(millis * 1000L)
        return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
    }
}