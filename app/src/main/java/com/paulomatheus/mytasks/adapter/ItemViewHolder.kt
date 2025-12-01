package com.paulomatheus.mytasks.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.paulomatheus.mytasks.R
import com.paulomatheus.mytasks.databinding.ListItemBinding
import com.paulomatheus.mytasks.entity.Task
import com.paulomatheus.mytasks.listener.ClickListener

class ItemViewHolder(private val binding: ListItemBinding private val listener: ClickListener) : RecyclerView.ViewHolder(binding.root) {
    fun setData(task: Task) {
        binding.tvTitle.text = task.title

        binding.root.setOnCreateContextMenuListener { menu, _, _ ->
            menu.add(R.string.mark_completed).setOnMenuItemClickListener {
                task.id?.let { id -> listener.OnComplete(task.id) }
                true
            }
        }
    }
}