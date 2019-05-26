/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.network

import com.example.todo.model.Task
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


interface TaskApiService {
    @GET("tasks")
    fun getAllTasks(): Deferred<List<Task>>

    @POST("tasks")
    @Headers("Content-Type: application/json")
    fun createTask(@Body task: Task): Deferred<Task?>

    @POST("tasks/{id}/close")
    fun closeTask(@Path("id") id: String): Deferred<Response<ResponseBody>>

    @POST("tasks/{id}/reopen")
    fun openTask(@Path("id") id: String): Deferred<Response<ResponseBody>>

    @DELETE("tasks/{id}")
    fun deleteTask(@Path("id") id: String):Deferred<Response<ResponseBody>>
}



