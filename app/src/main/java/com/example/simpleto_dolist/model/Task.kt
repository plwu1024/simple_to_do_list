package com.example.simpleto_dolist.model

import android.location.Location
import androidx.room.*
import java.util.*

@Entity(tableName = "Tasks")
data class Task(
    var title: String,
    var description: String,
    var dueDate: String,
    val createDate: String,
    val location: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)

