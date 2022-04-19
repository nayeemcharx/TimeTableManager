package com.example.timetablemanager.taskViewer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import com.example.timetablemanager.Global
import com.example.timetablemanager.R

class SettingFragment : Fragment() {

    private lateinit var notifSwitch:SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notifSwitch=view.findViewById(R.id.notificationSwitch)
        notifSwitch.isChecked=Global.notif

        notifSwitch.setOnClickListener{
            Global.notif=notifSwitch.isChecked
        }




    }


}