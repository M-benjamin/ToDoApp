package com.example.todo.model

data class Task(
    val id: String = "",
    val content: String,
    val completed: Boolean = false
)