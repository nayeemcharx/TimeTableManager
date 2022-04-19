package com.example.timetablemanager.taskViewer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timetablemanager.R
import com.example.timetablemanager.databaseHandler.DatabaseInfo
import com.example.timetablemanager.databaseHandler.DatabaseOperations
import com.example.timetablemanager.taskScheduler.Task
import java.util.*


class WeekViewFragment : Fragment() {
    var todoItemsListSun = ArrayList<Task>()
    var todoItemsListMon= ArrayList<Task>()

    private lateinit var taskRecyclerView: RecyclerView
    private lateinit var recyclerAdapter: TaskAdapter
    private lateinit var recyclerLayoutManager: RecyclerView.LayoutManager
    private lateinit var sundayButton:Button
    private lateinit var mondayButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_week_view, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sundayButton=view.findViewById(R.id.sun_button)
        mondayButton=view.findViewById(R.id.mon_button)


        val date=Calendar.getInstance()
        while(date.get(Calendar.DAY_OF_WEEK)!=1)
        {
            date.add(Calendar.DATE,-1)
        }

        for(i in 0..6)
        {
            val stringDate=getDateAsString(date)


            val dbo = DatabaseOperations(requireActivity())
            val cursor = dbo.getItems(dbo,stringDate)
            with(cursor) {
                while(moveToNext()) {
                    val itemName = getString(getColumnIndex(DatabaseInfo.TableInfo.COLUMN_ITEM_NAME))
                    val itemStart = getString(getColumnIndex(DatabaseInfo.TableInfo.COLUMN_ITEM_START))
                    val itemEnd = getString(getColumnIndex(DatabaseInfo.TableInfo.COLUMN_ITEM_END))
                    val itemDate = getString(getColumnIndex(DatabaseInfo.TableInfo.COLUMN_DATE))
                    val task = Task(itemName, itemStart,itemEnd,itemDate)
                    if(i==0)todoItemsListSun.add(task)
                    if(i==1)todoItemsListMon.add((task))
                    Log.d("testing","$i $itemName $itemStart $itemEnd $itemDate")
                }
            }
            date.add(Calendar.DATE,1)
        }
        taskRecyclerView=view.findViewById(R.id.task_recycler_view_week)
        val activity= activity
        recyclerLayoutManager = LinearLayoutManager(activity)
        sundayButton.setOnClickListener{

            recyclerAdapter = TaskAdapter(todoItemsListSun,activity)
            taskRecyclerView.apply {
                setHasFixedSize(true)
                layoutManager = recyclerLayoutManager
                adapter = recyclerAdapter
            }
        }
        mondayButton.setOnClickListener {
            recyclerAdapter = TaskAdapter(todoItemsListMon,activity)
            taskRecyclerView.apply {
                setHasFixedSize(true)
                layoutManager = recyclerLayoutManager
                adapter = recyclerAdapter
            }
        }


    }
    private fun getDateAsString(date:Calendar): String {
        val year = date.get(Calendar.YEAR).toString()
        val month = (date.get(Calendar.MONTH)).toString()
        val day = date.get(Calendar.DAY_OF_MONTH).toString()
        Log.d("today's date","$year/$month/$day")
        return "$year/$month/$day"
    }


}