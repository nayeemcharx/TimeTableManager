package com.example.timetablemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class TaskEditorActivity : AppCompatActivity() {
    private lateinit var taskName:EditText
    private lateinit var startTime:EditText
    private lateinit var endTime:EditText
    private lateinit var date:EditText
    private lateinit var saveButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_editor)
        taskName=findViewById(R.id.editTextTask)
        startTime=findViewById(R.id.editStartTime)
        endTime=findViewById(R.id.editEndTime)
        date=findViewById(R.id.editDate)
        saveButton=findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
        }

    }
}