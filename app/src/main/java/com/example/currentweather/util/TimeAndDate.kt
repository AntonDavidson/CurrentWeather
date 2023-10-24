package com.example.currentweather.util

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun hourFormat(inputDate: String): String {
    val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm")
    val outputFormat = "HH"
    val outputFormatter = DateTimeFormatter.ofPattern(outputFormat)
    val date = LocalDateTime.parse(inputDate.trim(), inputFormat)
    val formattedDate = date.format(outputFormatter)
    return formattedDate.toString()

}

fun hourAndMinuteFormat(inputDate: String): String {
    val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm")
    val outputFormat = "HH:MM"
    val outputFormatter = DateTimeFormatter.ofPattern(outputFormat)
    val date = LocalDateTime.parse(inputDate.trim(), inputFormat)
    val formattedDate = date.format(outputFormatter)
    return formattedDate.toString()

}

fun timeByDayMonth(inputDate: String): String {
    val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val outputFormat = "dd/MM"
    val outputFormatter = DateTimeFormatter.ofPattern(outputFormat)
    val date = LocalDate.parse(inputDate.trim(), inputFormat)
    val formattedDate = date.format(outputFormatter)
    return formattedDate.toString()
}

fun extractHourFromTimeString(timeString: String): Int {
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
    val time = LocalTime.parse(timeString, formatter)
    return time.hour
}

fun extractMinuteFromTimeString(timeString: String): Int {
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
    val time = LocalTime.parse(timeString, formatter)
    return time.minute
}

fun extractHourFromTimeEpochString(dateTimeString: String): Int {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val dateTime = LocalDateTime.parse(dateTimeString, formatter)
    return dateTime.hour
}
fun getHoursAndMinutesDiff(startHour: String, endHour: String): Pair<Int, Int> {
    val startTotalMinutes =
        extractHourFromTimeString(startHour) * 60 + extractMinuteFromTimeString(startHour)
    val endTotalMinutes =
        extractHourFromTimeString(endHour) * 60 + extractMinuteFromTimeString(endHour)
    val diffMinutes = endTotalMinutes - startTotalMinutes
    val hours = diffMinutes / 60
    val minutes = diffMinutes % 60

    return Pair(hours, minutes)
}

fun getCurrentTimeString(): String {
    val currentTimeMillis = System.currentTimeMillis()
    val currentTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(currentTimeMillis), ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
    return currentTime.format(formatter)
}
fun currentMillisToHours(millis: Long): Int {
    return ((millis / (1000 * 60 * 60)) % 24).toInt()
}
