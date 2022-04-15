package com.example.timetablemanager.taskViewer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.timetablemanager.R
import com.example.timetablemanager.databaseHandler.DatabaseInfo
import com.example.timetablemanager.databaseHandler.DatabaseOperations
import com.example.timetablemanager.taskScheduler.Task
import java.util.*


class WeekViewFragment : Fragment() {
    var todoItemsList = arrayOf(ArrayList<Task>())
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
                    //todoItemsList[i].add(task)
                    Log.d("testing","$i $itemName $itemStart $itemEnd $itemDate")
                }
            }
            date.add(Calendar.DATE,1)
        }
        //todoItemsList[day_of_week] returns list of tasks on that day. sunday to saturday is 0 to 6


    }
    private fun getDateAsString(date:Calendar): String {
        val year = date.get(Calendar.YEAR).toString()
        val month = (date.get(Calendar.MONTH)).toString()
        val day = date.get(Calendar.DAY_OF_MONTH).toString()
        Log.d("today's date","$year/$month/$day")
        return "$year/$month/$day"
    }


}