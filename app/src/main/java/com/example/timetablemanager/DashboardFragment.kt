package com.example.timetablemanager

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class DashboardFragment : Fragment() {

    private lateinit var AddTaskButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AddTaskButton=view.findViewById(R.id.add_task_button)
        val activity= activity

        AddTaskButton.setOnClickListener{
            val intent = Intent (activity, TaskEditorActivity::class.java)
            activity?.startActivity(intent)
            activity?.finish()

        }

    }


}