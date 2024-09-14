package com.memozi.diary.utils

object CalendarUtils {

    fun showLastMonth(year: Int, month: Int): Pair<Int, Int> {
        var newYear = year
        var newMonth = month
        if (newMonth == 1) {
            newYear--
            newMonth = 12
        } else {
            newMonth--
        }
        return Pair(newYear, newMonth)
    }

    fun showNextMonth(year: Int, month: Int): Pair<Int, Int> {
        var newYear = year
        var newMonth = month
        if (newMonth == 12) {
            newYear++
            newMonth = 1
        } else {
            newMonth++
        }
        return Pair(newYear, newMonth)
    }
}
