package com.example.simpleto_dolist.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simpleto_dolist.model.Task
import com.example.simpleto_dolist.model.TaskDao

//val defaultTasks = mutableListOf<Task>(
//    Task("task1", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
//    Task("task2", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
//    Task("task3", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
//    Task("task4", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
//    Task("task5", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
//    Task("task6", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
//    Task("task7", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
//    Task("task8", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
//    Task("task9", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
//    Task("task10", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
//    Task("task11", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER)),
//    Task("task12", "description", Date(), Date(), Location(LocationManager.FUSED_PROVIDER))
//)

//class TaskDataSource() {
//    private val tasks: MutableList<Task> = mutableListOf()
//
//    init {
//        tasks.addAll(defaultTasks)
//    }
//
//    fun loadTasks(): MutableList<Task> {
//        return tasks
//    }
//
//    fun addTask(task: Task) {
//        tasks.add(task)
//    }
//}

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}