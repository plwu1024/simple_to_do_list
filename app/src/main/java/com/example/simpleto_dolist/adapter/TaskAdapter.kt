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

class TaskAdapter(
    private val context: Context,
    private val dataset: MutableList<Task>
): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    class TaskViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val titleTextView: TextView = view.findViewById(R.id.task_title)
        val descriptionTextView: TextView = view.findViewById((R.id.task_description))
        val createdDate: EditText = view.findViewById(R.id.task_created_date)
        val dueDateEditText: EditText = view.findViewById(R.id.task_due_date)
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
//        holder.createdDate.text = task.createDate.

    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}