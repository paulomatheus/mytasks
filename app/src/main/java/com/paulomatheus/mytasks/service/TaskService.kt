package com.paulomatheus.mytasks.service

import com.paulomatheus.mytasks.entity.Task

class TaskService {
    private val taskRepository = RetrofitService().getTaskRepository()

    fun create(task: Task){
        taskRepository.create(task)
    }
}