package com.example.timetablemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class TaskEditorActivity : AppCompatActivity() {
    private lateinit var taskName:EditText
    private lateinit var startTime:TimePicker
    private lateinit var endTime:TimePicker
    private lateinit var date:DatePicker
    private lateinit var saveButton:Button
    private lateinit var cancelButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_editor)
        taskName=findViewById(R.id.task_name)
        startTime=findViewById(R.id.start_time_picker)
        endTime=findViewById(R.id.end_time_picker)
        date=findViewById(R.id.date_picker)
        saveButton=findViewById(R.id.save_button)
        cancelButton=findViewById(R.id.cancel_button)

        var editingItem=false;

        val itemName = intent.getStringExtra("ITEM_NAME")
        val itemStart = intent.getStringExtra("ITEM_START")
        val itemEnd = intent.getStringExtra("ITEM_END")
        val itemDate = intent.getStringExtra("ITEM_DATE")

        if(itemName!=null) {
            taskName.setText(itemName)
            editingItem=true;
        }
        if(itemStart!=null) {
            val list=itemStart.split(":")
            startTime.hour=list[0].toInt()
            startTime.minute=list[1].toInt()

        }
        if(itemEnd!=null) {
            val list=itemEnd.split(":")
            endTime.hour=list[0].toInt()
            endTime.minute=list[1].toInt()
        }

        lateinit var oldTask:Task
        if(editingItem)
            oldTask=Task(itemName!!,itemStart!!,itemEnd!!,itemDate!!)
        saveButton.setOnClickListener {
            val item=taskName.text.toString()
            val start="${startTime.hour}:${startTime.minute}"
            val end="${endTime.hour}:${endTime.minute}"
            val dateToDo="${date.year}/${date.month}/${date.dayOfMonth}"
            val task=Task(item,start,end,dateToDo)
            Log.d("task created", task.name)
            val dbo = DatabaseOperations(this)
            if(!editingItem)
            {
                dbo.addItem(dbo, task)
            }
            else
            {
                dbo.updateItem(dbo, oldTask, task)
            }
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
        cancelButton.setOnClickListener{
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}