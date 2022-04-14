package com.example.timetablemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.timetablemanager.R.id.navHostFragment
import com.google.android.material.navigation.NavigationView
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout:DrawerLayout
    private lateinit var imageMenu: ImageView
    private lateinit var navigationView:NavigationView
    private lateinit var navController:NavController
    private lateinit var dayViewText: TextView
    private lateinit var weekViewText: TextView
    private lateinit var AddTaskButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        //starting
        drawerLayout = findViewById(R.id.drawerLayout)
        imageMenu = findViewById(R.id.imageMenu)
        navigationView=findViewById(R.id.navigationView)
        navController=findNavController(R.id.navHostFragment)
        NavigationUI.setupWithNavController(navigationView,navController)

        /*dayViewText=findViewById(R.id.dayview)
        weekViewText=findViewById(R.id.weekview)*/
        AddTaskButton=findViewById(R.id.add_task_button)


        imageMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }



        /*dayViewText.setOnClickListener{

            val intent = Intent(this,DayViewActivity::class.java)
            startActivity(intent)
            finish()
        }
        weekViewText.setOnClickListener{

            val intent = Intent(this,WeekViewActivity::class.java)
            startActivity(intent)
            finish()
        }*/
        AddTaskButton.setOnClickListener{
            val intent = Intent(this,TaskEditorActivity::class.java)
            startActivity(intent)
            finish()
        }



    }
}