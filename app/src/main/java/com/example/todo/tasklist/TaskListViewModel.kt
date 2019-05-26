package com.example.todo.tasklist

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.todo.model.Task
import com.example.todoapp.network.TaskApi
import kotlinx.coroutines.launch

class TaskListViewModel : ViewModel() {
    val adapter = TaskListAdapter(mutableListOf(), this::deleteTask, this::openTask, this::closeTask)

    fun refreshTasks() {
        viewModelScope.launch {
            val newTasks = TaskApi.retrofitService.getAllTasks().await()
            adapter.resetAll(newTasks)
        }
    }


    fun addTask(text: String) {
        print("TESXXXXXXXXX $text")
        viewModelScope.launch {
            val newTask = TaskApi.retrofitService.createTask(Task(content = text)).await()
            adapter.addTask(newTask)
        }
    }

    fun deleteTask(taskRemove: Task) {
        viewModelScope.launch {
            val response = TaskApi.retrofitService.deleteTask(taskRemove.id).await()
            if (response.isSuccessful) {
                adapter.removeTask(taskRemove)
            }
        }
    }

    fun deleteAll() {
        for (task in adapter.taskList) {
            TaskApi.retrofitService.deleteTask(task.id)
        }
        adapter.removeAllTask()
    }

    fun closeTask(task: Task) {
        viewModelScope.launch {
            val response = TaskApi.retrofitService.closeTask(task.id).await()
            if (response.isSuccessful) {
                //adapter.removeTask(task)
            }
        }
    }

    fun openTask(task: Task) {
        viewModelScope.launch {
            val response = TaskApi.retrofitService.openTask(task.id).await()
            if (response.isSuccessful) {
                //adapter.removeTask(task)
            }
        }
    }


}
