package com.example.timetablemanager

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val taskList: ArrayList<Task>, val activity: DayViewActivity):
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    class ViewHolder(val constraintLayout: ConstraintLayout): RecyclerView.ViewHolder(constraintLayout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val constraintLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.task_layout, parent, false) as ConstraintLayout

        constraintLayout.setOnClickListener(View.OnClickListener {
            val nameTextView = constraintLayout.getChildAt(0) as TextView
            val startTimeView = constraintLayout.getChildAt(1) as TextView
            val endTimeView = constraintLayout.getChildAt(2) as TextView
            val nameText = nameTextView.text
            val start=startTimeView.text
            val end=endTimeView.text

            val intent: Intent = Intent(parent.context, TaskEditorActivity::class.java)
            intent.putExtra("ITEM_NAME", nameText)
            intent.putExtra("ITEM_START", start)
            intent.putExtra("ITEM_END", end)
            intent.putExtra("ITEM_DATE", taskList[0].dateToDo)
            activity.startActivity(intent)
        })

//        constraintLayout.setOnLongClickListener(View.OnLongClickListener {
//            val position: Int = parent.indexOfChild(it)
//
//            val todoItemToRemove = todoItemsList[position]
//            val dbo = DatabaseOperations(parent.context)
//            dbo.deleteItem(dbo, todoItemToRemove)
//
//            todoItemsList.removeAt(position)
//            notifyItemRemoved(position)
//            true
//        })

        return ViewHolder(constraintLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val constraintLayout = holder.constraintLayout
        val nameTextView = constraintLayout.getChildAt(0) as TextView
        val startTimeView = constraintLayout.getChildAt(1) as TextView
        val endTimeView = constraintLayout.getChildAt(2) as TextView
        nameTextView.text = taskList[position].name
        startTimeView.text=taskList[position].startTime
        endTimeView.text=taskList[position].endTime

    }

    override fun getItemCount(): Int {
        return taskList.size
    }

}