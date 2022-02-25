package com.example.simpleto_dolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.simpleto_dolist.adapter.TaskAdapter
import com.example.simpleto_dolist.data.TaskDatabase
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val dataset = TaskDataSource().loadTasks()
        val db = Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java,
            "task"
        ).allowMainThreadQueries().build()

        val taskDao = db.taskDao()
        var dataset = taskDao.getAll()

        val tasksRecyclerView = findViewById<RecyclerView>(R.id.tasks_recycler_view)
        tasksRecyclerView.adapter = TaskAdapter(this, dataset)

        val addTaskCard: MaterialCardView = findViewById(R.id.add_task_card)
        addTaskCard.setOnClickListener {
            val intent = Intent(it.context, AddTaskActivity::class.java)
            it.context.startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val db = Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java,
            "task"
        ).allowMainThreadQueries().build()

        val taskDao = db.taskDao()
        var dataset = taskDao.getAll()

        val tasksRecyclerView = findViewById<RecyclerView>(R.id.tasks_recycler_view)
        tasksRecyclerView.adapter = TaskAdapter(this, dataset)
    }
}