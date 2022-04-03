package com.example.timetablemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var dayViewText: TextView
    private lateinit var weekViewText: TextView
    private lateinit var AddTaskButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //starting
        dayViewText=findViewById(R.id.dayview)
        weekViewText=findViewById(R.id.weekview)
        AddTaskButton=findViewById(R.id.add_task_button)

        dayViewText.setOnClickListener{

            val intent = Intent(this,DayViewActivity::class.java)
            startActivity(intent)
            finish()
        }
        weekViewText.setOnClickListener{

            val intent = Intent(this,WeekViewActivity::class.java)
            startActivity(intent)
            finish()
        }
        AddTaskButton.setOnClickListener{
            val intent = Intent(this,TaskEditorActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}