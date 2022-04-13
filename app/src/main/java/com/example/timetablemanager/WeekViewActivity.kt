package com.example.timetablemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.*

class WeekViewActivity : AppCompatActivity() {
    var todoItemsList = arrayOf(ArrayList<Task>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week_view)
        val date=Calendar.getInstance()

        while(date.get(Calendar.DAY_OF_WEEK)!=1)
        {
            date.add(Calendar.DATE,-1)
        }

        for(i in 0..6)
        {
            val stringDate=getDateAsString(date)


            val dbo = DatabaseOperations(this)
            val cursor = dbo.getItems(dbo,stringDate)
            with(cursor) {
                while(moveToNext()) {
                    val itemName = getString(getColumnIndex(DatabaseInfo.TableInfo.COLUMN_ITEM_NAME))
                    val itemStart = getString(getColumnIndex(DatabaseInfo.TableInfo.COLUMN_ITEM_START))
                    val itemEnd = getString(getColumnIndex(DatabaseInfo.TableInfo.COLUMN_ITEM_END))
                    val itemDate = getString(getColumnIndex(DatabaseInfo.TableInfo.COLUMN_DATE))

                    val task = Task(itemName, itemStart,itemEnd,itemDate)
                    todoItemsList[i].add(task)
                }
            }
            date.add(Calendar.DATE,1)
        }
        //todoItemsList[day_of_week] returns list of tasks on that day. sunday to saturday is 0 to 6






    }
    private fun getDateAsString(date:Calendar): String {
        val year = date.get(Calendar.YEAR).toString()
        val month = (date.get(Calendar.MONTH)+1).toString()
        val day = date.get(Calendar.DAY_OF_MONTH).toString()
        Log.d("today's date","$year/$month/$day")
        return "$year/$month/$day"
    }
}