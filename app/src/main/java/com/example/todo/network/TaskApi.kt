package com.example.todoapp.network

import com.example.android.marsrealestate.network.TaskApiService

object TaskApi {
    val retrofitService : TaskApiService by lazy {
        TaskNetworkAdapter.retrofit.create(TaskApiService::class.java)
    }
}