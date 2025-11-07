package com.paulomatheus.mytasks.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.paulomatheus.mytasks.databinding.ListItemBinding
import com.paulomatheus.mytasks.entity.Task

class ItemViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setData(task: Task) {
        binding.tvTitle.text = task.title
        binding.tvDate.text = task.date
    }
}