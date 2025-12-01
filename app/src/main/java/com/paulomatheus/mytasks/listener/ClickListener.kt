package com.paulomatheus.mytasks.listener

import com.paulomatheus.mytasks.entity.Task

interface ClickListener {
    fun onClick(task: Task)
    fun OnComplete(id: Long)
}