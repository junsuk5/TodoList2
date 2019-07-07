package com.exmaple.todolist.db

import androidx.room.*
import com.exmaple.todolist.models.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo")
    fun getAll(): List<Todo>

    @Query("SELECT * FROM Todo WHERE id=:id")
    fun getTodo(id: Long): Todo?

    @Insert
    fun insert(todo: Todo)

    @Update
    fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)
}