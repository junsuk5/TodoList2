package com.exmaple.todolist.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var title: String = "",
    var date: Long = 0
)