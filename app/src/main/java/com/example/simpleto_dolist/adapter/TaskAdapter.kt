package com.example.simpleto_dolist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleto_dolist.R
import com.example.simpleto_dolist.model.Task
import java.text.SimpleDateFormat

class TaskAdapter(
    private val context: Context,
    private val dataset: List<Task>
): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd")

    class TaskViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val titleTextView: TextView = view.findViewById(R.id.task_title)
        val descriptionTextView: TextView = view.findViewById((R.id.task_description))
        val createdDateTextView: TextView = view.findViewById(R.id.task_created_date)
        val dueDateTextView: TextView = view.findViewById(R.id.task_due_date)
        val locationTextView: TextView = view.findViewById(R.id.task_location)
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
        holder.createdDateTextView.text = task.createDate
        holder.dueDateTextView.text = task.dueDate
        holder.locationTextView.text = task.location
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}