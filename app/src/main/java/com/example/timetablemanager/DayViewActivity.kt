package com.example.timetablemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class DayViewActivity : AppCompatActivity() {
    var todoItemsList = ArrayList<Task>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_view)

        val dbo = DatabaseOperations(this)
        val cursor = dbo.getTodaysItems(dbo)
        with(cursor) {
            while(moveToNext()) {
                val itemName = getString(getColumnIndex(DatabaseInfo.TableInfo.COLUMN_ITEM_NAME))
                val itemStart = getString(getColumnIndex(DatabaseInfo.TableInfo.COLUMN_ITEM_START))
                val itemEnd = getString(getColumnIndex(DatabaseInfo.TableInfo.COLUMN_ITEM_END))
                val itemDate = getString(getColumnIndex(DatabaseInfo.TableInfo.COLUMN_DATE))

                val task = Task(itemName, itemStart,itemEnd,itemDate)
                todoItemsList.add(task)
            }
        }
        for(item in todoItemsList) {
            Log.d("iteminfo name", item.name)
            Log.d("iteminfo starttime", item.startTime)
            Log.d("iteminfoendtime", item.endTime)
            Log.d("iteminfodate",item.dateToDo)

        }
    }
}