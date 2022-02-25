package com.example.simpleto_dolist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.simpleto_dolist.data.TaskDatabase
import com.example.simpleto_dolist.model.Task
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat

class UpdateTaskActivity: AppCompatActivity() {
    private val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView((R.layout.activity_add_task))

        val titleTextField = findViewById<TextInputLayout>(R.id.add_task_title)
        val descriptionTextField = findViewById<TextInputLayout>(R.id.add_task_description)
        val dueDateText = findViewById<TextView>(R.id.add_task_due_date_text)
        val createdDateText = findViewById<TextView>(R.id.add_task_created_date_text)
        val locationTextField = findViewById<TextInputLayout>(R.id.add_task_location)
        val submitButton = findViewById<Button>(R.id.add_task_submit_button)
        submitButton.text = "OK"
        val db = Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java,
            "task"
        ).allowMainThreadQueries().build()
        val taskDao = db.taskDao()
        val task = intent?.extras?.getInt("id")?.let { taskDao.getById(it) }

        //      due date
        val dueDateButton = findViewById<ImageButton>(R.id.add_task_due_button)
        val dueDatePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Due on").build()
        dueDatePicker.addOnPositiveButtonClickListener {
            dueDateText.text = simpleDateFormat.format(dueDatePicker.selection)
        }
        dueDateButton.setOnClickListener {
            dueDatePicker.show(supportFragmentManager, "tag")
        }

//      created date
        val createdDateButton = findViewById<ImageButton>(R.id.add_task_created_button)
        val createdDatePicker =
            MaterialDatePicker.Builder.datePicker().setTitleText("Created on").build()
        createdDatePicker.addOnPositiveButtonClickListener {
            createdDateText.text = simpleDateFormat.format(createdDatePicker.selection)
        }
        createdDateButton.setOnClickListener {
            createdDatePicker.show(supportFragmentManager, "tag")
        }

        if (task != null) {
            titleTextField.editText?.setText(task.title)
            descriptionTextField.editText?.setText(task.description)
            dueDateText.text = task.dueDate
            createdDateText.text = task.createdDate
            locationTextField.editText?.setText(task.location)
        }
        submitButton.setOnClickListener {
            val title = titleTextField.editText?.text.toString()
            val description = descriptionTextField.editText?.text.toString()
            val dueDate = dueDateText.text.toString()
            val createdDate = createdDateText.text.toString()
            val location = locationTextField.editText?.text.toString()
            if (task != null) {
                taskDao.update(Task(title, description, dueDate, createdDate, location, task.id))
            }
            it.context.startActivity(Intent(it.context, MainActivity::class.java))

        }
    }
}