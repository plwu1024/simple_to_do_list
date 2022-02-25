package com.example.simpleto_dolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleto_dolist.adapter.TaskAdapter
import com.example.simpleto_dolist.data.TaskDataSource
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataset = TaskDataSource().loadTasks()

        val tasksRecyclerView = findViewById<RecyclerView>(R.id.tasks_recycler_view)
        tasksRecyclerView.adapter = TaskAdapter(this, dataset)

        val addTaskCard: MaterialCardView = findViewById(R.id.add_task_card)
        addTaskCard.setOnClickListener {
            val intent: Intent = Intent(it.context, AddTaskActivity::class.java)
            it.context.startActivity(intent)
        }
    }
}