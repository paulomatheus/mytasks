package com.paulomatheus.mytasks.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paulomatheus.mytasks.entity.Task

class TaskService: ViewModel() {
    private val taskRepository = RetrofitService().getTaskRepository()

    fun create(task: Task) : LiveData<Task> {
        val tasksLiveData = MutableLiveData<Task>()
        taskRepository.create(task).enqueue(ServiceCallBack<Task>(tasksLiveData))
        return tasksLiveData
    }

    fun list(): LiveData<List<Task>> {
        val tasksLiveData = MutableLiveData<List<Task>>()
        taskRepository.list().enqueue(ServiceCallBack<List<Task>>(tasksLiveData))
        return tasksLiveData
    }
}