package com.paulomatheus.mytasks.service

import com.paulomatheus.mytasks.entity.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskService {
    private val taskRepository = RetrofitService().getTaskRepository()

    fun create(task: Task) {
        taskRepository.create(task).enqueue(object : Callback<Task> {
            override fun onResponse(call: Call<Task>, response: Response<Task>) {
                if (response.isSuccessful) {

                }
            }

            override fun onFailure(
                call: retrofit2.Call<com.paulomatheus.mytasks.entity.Task?>,
                t: kotlin.Throwable
            ) {
                TODO("Not yet implemented")
            }
        })
    }

    fun list() {
        taskRepository.list().enqueue(object : Callback<List<Task>> {
            override fun onResponse(
                call: Call<List<Task>?>,
                response: Response<List<Task>?>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(
                call: Call<List<Task>?>,
                t: Throwable
            ) {
                TODO("Not yet implemented")
            }
        })
    }
}