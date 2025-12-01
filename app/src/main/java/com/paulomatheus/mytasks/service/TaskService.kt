package com.paulomatheus.mytasks.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paulomatheus.mytasks.entity.Task
import com.paulomatheus.mytasks.repository.ResponseDTO

class TaskService: ViewModel() {
    private val taskRepository = RetrofitService().getTaskRepository()

    fun create(task: Task) : LiveData<ResponseDTO<Task>> {
        val tasksLiveData = MutableLiveData<ResponseDTO<Task>>()
        taskRepository.create(task).enqueue(ServiceCallBack<Task>(tasksLiveData))
        return tasksLiveData
    }

    fun list(): LiveData<ResponseDTO<List<Task>>> {
        val tasksLiveData = MutableLiveData<ResponseDTO<List<Task>>>()
        taskRepository.list().enqueue(ServiceCallBack<List<Task>>(tasksLiveData))
        return tasksLiveData
    }

    fun delete(id: Long): LiveData<ResponseDTO<Void>> {
        val liveData = MutableLiveData<ResponseDTO<Void>>()
        taskRepository.delete(id).enqueue(ServiceCallBack(liveData))
        return liveData
    }

    fun complete(id: Long): LiveData<ResponseDTO<Task>> {
        val taskLiveData = MutableLiveData<ResponseDTO<Task>>()
        taskRepository.complete(id).enqueue(ServiceCallBack(taskLiveData))
        return taskLiveData

    }
}