package com.example.simpleto_dolist.model

import androidx.room.*
import com.example.simpleto_dolist.model.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Task): Long

//    @Insert
//    fun insertAll(item: Task)

    @Query("SELECT * FROM Tasks ORDER BY dueDate DESC")
    fun getAll(): List<Task>

    @Delete
    fun delete(item: Task)

    @Update
    fun update(item: Task)
}