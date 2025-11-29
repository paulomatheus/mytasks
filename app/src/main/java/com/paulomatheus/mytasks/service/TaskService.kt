package com.paulomatheus.mytasks.service

import com.paulomatheus.mytasks.entity.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskService {
    private val taskRepository = RetrofitService().getTaskRepository()

    fun create(task: Task) {
        taskRepository.create(task).enqueue(ServiceCallBack<Task>())
    }

    fun list() {
        taskRepository.list().enqueue(ServiceCallBack<List<Task>>())
    }
}