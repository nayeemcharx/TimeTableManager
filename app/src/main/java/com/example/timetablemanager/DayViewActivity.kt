package com.example.timetablemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class DayViewActivity : AppCompatActivity() {
    var todoItemsList = ArrayList<Task>()

    private lateinit var taskRecyclerView: RecyclerView
    private lateinit var recyclerAdapter: TaskAdapter
    private lateinit var recyclerLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_view)

        val dbo = DatabaseOperations(this)
        val cursor = dbo.getItems(dbo,getDateAsString())
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
        taskRecyclerView=findViewById(R.id.taskRecylerView)
        recyclerLayoutManager = LinearLayoutManager(this)
        recyclerAdapter = TaskAdapter(todoItemsList, this)

        taskRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = recyclerLayoutManager
            adapter = recyclerAdapter
        }

        for(item in todoItemsList) {
            Log.d("iteminfo name", item.name)
            Log.d("iteminfo starttime", item.startTime)
            Log.d("iteminfoendtime", item.endTime)
            Log.d("iteminfodate",item.dateToDo)

        }
    }
    private fun getDateAsString(): String {
        val date = Calendar.getInstance()
        val year = date.get(Calendar.YEAR).toString()
        val month = (date.get(Calendar.MONTH)+1).toString()
        val day = date.get(Calendar.DAY_OF_MONTH).toString()
        Log.d("today's date","$year/$month/$day")
        return "$year/$month/$day"
    }
}