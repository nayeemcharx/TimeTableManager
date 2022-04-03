package com.example.timetablemanager

import java.util.*

class Task(var name:String,var start: String, end:String, date:String)
{
    var taskName=name
    var startTime=start
    var endTime=end
    var dateToDo=date
    var dateToday = Calendar.getInstance()
    var dateString: String = getDateAsString()

    fun getDateAsString(): String {
        val year = dateToday.get(Calendar.YEAR).toString()
        val month = dateToday.get(Calendar.MONTH).toString()
        val day = dateToday.get(Calendar.DAY_OF_MONTH).toString()
        return "$year/$month/$day"
    }
}