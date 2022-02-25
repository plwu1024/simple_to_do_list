package com.example.simpleto_dolist.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.simpleto_dolist.MainActivity
import com.example.simpleto_dolist.R
import com.example.simpleto_dolist.UpdateTaskActivity
import com.example.simpleto_dolist.data.TaskDatabase
import com.example.simpleto_dolist.model.Task
import com.google.android.material.card.MaterialCardView
import java.text.SimpleDateFormat

class TaskAdapter(
    private val context: Context,
    private val dataset: List<Task>
): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private val db = Room.databaseBuilder(
        context,
        TaskDatabase::class.java,
        "task"
    ).allowMainThreadQueries().build()

    private val taskDao = db.taskDao()

    class TaskViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val titleTextView: TextView = view.findViewById(R.id.task_title)
        val descriptionTextView: TextView = view.findViewById((R.id.task_description))
        val createdDateTextView: TextView = view.findViewById(R.id.task_created_date)
        val dueDateTextView: TextView = view.findViewById(R.id.task_due_date)
        val locationTextView: TextView = view.findViewById(R.id.task_location)
        val deleteButton: ImageButton = view.findViewById(R.id.button_delete)
        val card: MaterialCardView = view.findViewById(R.id.task_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_task, parent, false)
        return TaskViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = dataset[position]
        holder.titleTextView.text = task.title
        holder.descriptionTextView.text = task.description
        holder.createdDateTextView.text = task.createdDate
        holder.dueDateTextView.text = task.dueDate
        holder.locationTextView.text = task.location
        holder.deleteButton.setOnClickListener {
            taskDao.delete(task)
            context.startActivity(Intent(it.context, MainActivity::class.java))
        }
        holder.card.setOnClickListener {
            val intent = Intent(it.context, UpdateTaskActivity::class.java)
            intent.putExtra("id", task.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}