package com.example.timetablemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class TaskEditorActivity : AppCompatActivity() {
    private lateinit var taskName:EditText
    private lateinit var startTime:EditText
    private lateinit var endTime:EditText
    private lateinit var date:EditText
    private lateinit var saveButton:Button
    private lateinit var cancelButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_editor)
        taskName=findViewById(R.id.editTextTask)
        startTime=findViewById(R.id.editStartTime)
        endTime=findViewById(R.id.editEndTime)
        date=findViewById(R.id.editDate)
        saveButton=findViewById(R.id.saveButton)
        cancelButton=findViewById(R.id.cancelButton)


        val itemName = intent.getStringExtra("ITEM_NAME")
        val itemStart = intent.getStringExtra("ITEM_START")
        val itemEnd = intent.getStringExtra("ITEM_END")
        val itemDate = intent.getStringExtra("ITEM_DATE")

        if(itemName!=null)
            taskName.setText(itemName)
        if(itemStart!=null)
            startTime.setText(itemStart)
        if(itemName!=null)
            endTime.setText(itemEnd)
        if(itemName!=null)
            date.setText(itemDate)

        saveButton.setOnClickListener {
            val itemName=taskName.text.toString()
            val start=startTime.text.toString()
            val end=endTime.text.toString()
            val dateToDo=date.text.toString()
            var task=Task(itemName,start,end,dateToDo)
            Log.d("task created", task.name)
            val dbo = DatabaseOperations(this)
            dbo.addItem(dbo,task)
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        cancelButton.setOnClickListener{
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}