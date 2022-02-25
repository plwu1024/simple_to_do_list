package com.example.simpleto_dolist

import android.Manifest
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.simpleto_dolist.data.TaskDatabase
import com.example.simpleto_dolist.model.Task
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

class AddTaskActivity : AppCompatActivity() {
    private val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd")

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        var locationPermissionStatus: Boolean = false

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                    locationPermissionStatus = true
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // Only approximate location access granted.
                    locationPermissionStatus = true
                }
                else -> {
                    // No location access granted.

                }
            }
        }
        if (!locationPermissionStatus) {
            locationPermissionRequest.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }

//        var fusedLocCli : FusedLocationProviderClient = LocationServices

        val currentLocation = Location(LocationManager.GPS_PROVIDER)
        findViewById<TextInputLayout>(R.id.add_task_location).editText?.setText("${currentLocation.altitude}, ${currentLocation.latitude}")


//      due date
        val dueDateText = findViewById<TextView>(R.id.add_task_due_date_text)
        dueDateText.text = simpleDateFormat.format(Date())
        val dueDateButton = findViewById<ImageButton>(R.id.add_task_due_button)
        val dueDatePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Due on").build()
        dueDatePicker.addOnPositiveButtonClickListener {
            dueDateText.text = simpleDateFormat.format(dueDatePicker.selection)
        }
        dueDateButton.setOnClickListener {
            dueDatePicker.show(supportFragmentManager, "tag")
        }

//      created date
        val createdDateText = findViewById<TextView>(R.id.add_task_created_date_text)
        createdDateText.text = simpleDateFormat.format(Date())
        val createdDateButton = findViewById<ImageButton>(R.id.add_task_created_button)
        val createdDatePicker =
            MaterialDatePicker.Builder.datePicker().setTitleText("Created on").build()
        createdDatePicker.addOnPositiveButtonClickListener {
            createdDateText.text = simpleDateFormat.format(createdDatePicker.selection)
        }
        createdDateButton.setOnClickListener {
            createdDatePicker.show(supportFragmentManager, "tag")
        }


        val addTaskButton = findViewById<Button>(R.id.add_task_submit_button)
        addTaskButton.setOnClickListener {
            val title = findViewById<TextInputLayout>(R.id.add_task_title).editText?.text.toString()
            val description =
                findViewById<TextInputLayout>(R.id.add_task_description).editText?.text.toString()
            val dueDate = dueDateText.text.toString()
            val createdDate = createdDateText.text.toString()
            val location =
                findViewById<TextInputLayout>(R.id.add_task_location).editText?.text.toString()


            val db = Room.databaseBuilder(
                applicationContext,
                TaskDatabase::class.java, "task"
            ).allowMainThreadQueries().build()
            val taskDao = db.taskDao()
            taskDao.insert(Task(title, description, dueDate, createdDate, location))
            val intent = Intent(it.context, MainActivity::class.java)
            it.context.startActivity(intent)
        }
    }
}