package com.saber.sabernews.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

private const val DATE_ISO_8601_FORMAT = "yyyy-MM-dd'T'hh:mm:ss"
private const val DATE_FORMAT_SHORT = "yyyy-MM-dd"

fun String.getDate(): Date? {
    return if (this.isDateInISO8601()) {
        val format = SimpleDateFormat(DATE_ISO_8601_FORMAT, Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("GMT")
        format.parse(this)
    } else {
        null
    }
}

fun LocalDate.getShortDateString(): String? {
    return SimpleDateFormat(DATE_FORMAT_SHORT).format(this)
}

fun Date.getOffsetDate(): String {
    val format = SimpleDateFormat(DATE_ISO_8601_FORMAT, Locale.getDefault())
    format.timeZone = TimeZone.getTimeZone("GMT")
    return format.format(this)
}

private fun String.isDateInISO8601(): Boolean {
    return try {
        val format = SimpleDateFormat(DATE_ISO_8601_FORMAT, Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("GMT")
        format.parse(this)
        true
    } catch (e: ParseException) {
        false
    }
}