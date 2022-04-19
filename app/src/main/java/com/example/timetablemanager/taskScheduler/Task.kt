package com.example.timetablemanager.taskScheduler

import java.util.*

class Task(var name:String,var start: String, end:String, date:String)
{
    var taskName=name
    var startTime=start
    var endTime=end
    var dateToDo=date
    var startTimeInMinutes=timeInMinutes(startTime)
    private fun timeInMinutes(start:String):Int
    {
        val x=start.split(":")
        val min=(x[0].toInt()*60)+x[1].toInt()
        return min
    }

}