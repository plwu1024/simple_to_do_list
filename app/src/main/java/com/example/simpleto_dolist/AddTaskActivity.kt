package com.example.simpleto_dolist

import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.simpleto_dolist.data.TaskDatabase
import com.example.simpleto_dolist.model.Task
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class AddTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val addTaskButton = findViewById<Button>(R.id.add_task_submit_button)
        addTaskButton.setOnClickListener {
            val title = findViewById<TextInputLayout>(R.id.add_task_title).editText?.text.toString()
            val description = findViewById<TextInputLayout>(R.id.add_task_description).editText?.text.toString()
            val dueDate = findViewById<TextInputLayout>(R.id.add_task_due_date).editText?.text.toString()
            val createdDate = findViewById<TextInputLayout>(R.id.add_task_created_date).editText?.text.toString()
            val location = findViewById<TextInputLayout>(R.id.add_task_location).editText?.text.toString()
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