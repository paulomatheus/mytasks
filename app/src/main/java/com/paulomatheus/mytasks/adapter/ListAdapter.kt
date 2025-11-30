package com.paulomatheus.mytasks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paulomatheus.mytasks.databinding.ListItemBinding
import com.paulomatheus.mytasks.entity.Task

class ListAdapter : RecyclerView.Adapter<ItemViewHolder>() {

    private val items = mutableListOf<Task>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        holder.setData(items[position])
    }

    override fun getItemCount() = items.size

    fun setData(tasks: List<Task>) {
        items.clear()
        items.addAll(tasks)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        items.removeAt(position)
        notifyItemRemoved(position)

    }

}