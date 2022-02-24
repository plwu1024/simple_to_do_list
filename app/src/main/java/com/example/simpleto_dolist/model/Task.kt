package com.example.simpleto_dolist.model

import android.location.Location
import java.util.*

data class Task(
    var title: String,
    var description: String,
    val createDate: Date,
    var dueDate: Date,
    val location: Location
)