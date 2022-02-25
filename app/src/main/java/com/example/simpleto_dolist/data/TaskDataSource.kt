package com.example.simpleto_dolist.data

import android.location.Location
import android.location.LocationManager
import com.example.simpleto_dolist.model.Task
import java.util.*

val defaultTasks = mutableListOf<Task>(
    Task("task1", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
    Task("task2", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
    Task("task3", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
    Task("task1", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
    Task("task2", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
    Task("task3", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
    Task("task1", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
    Task("task2", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
    Task("task3", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
    Task("task1", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
    Task("task2", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
    Task("task3", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER))
)

class TaskDataSource() {
    private val tasks: MutableList<Task> = mutableListOf()

    init {
        tasks.addAll(defaultTasks)
    }

    fun loadTasks(): MutableList<Task> {
        return tasks
    }

    fun addTask(task: Task) {
        tasks.add(task)
    }
}