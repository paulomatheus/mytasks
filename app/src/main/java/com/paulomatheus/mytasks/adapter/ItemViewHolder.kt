package com.paulomatheus.mytasks.adapter

import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.paulomatheus.mytasks.R
import com.paulomatheus.mytasks.databinding.ListItemBinding
import com.paulomatheus.mytasks.entity.Task
import com.paulomatheus.mytasks.listener.ClickListener
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class ItemViewHolder(private val binding: ListItemBinding, private val listener: ClickListener) : RecyclerView.ViewHolder(binding.root) {

    fun setData(task: Task) {
        binding.tvTitle.text = task.title
        binding.tvDescription.text = task.description ?: ""
        binding.tvTime.text = task.formatTime()

        val context = binding.root.context
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val useExtendedFormat = preferences.getBoolean("date_format_extended", false)

        binding.tvDate.text = getFormattedDate(task, useExtendedFormat)

        val colorResource = getStatusColor(task)
        (binding.root as CardView).setCardBackgroundColor(
            ContextCompat.getColor(context, colorResource)
        )

        binding.root.setOnClickListener {
            listener.onClick(task)
        }

        binding.root.setOnCreateContextMenuListener { menu, _, _ ->
            menu.add(R.string.mark_completed).setOnMenuItemClickListener {
                task.id?.let { id -> listener.OnComplete(task.id) }
                true
            }
        }
    }

    private fun getStatusColor(task: Task): Int {
        if (task.completed) {
            return R.color.green
        }

        val date = task.date ?: return R.color.blue
        val today = LocalDate.now()

        return when {
            date.isBefore(today) -> R.color.red
            date.isEqual(today) -> R.color.yellow
            else -> R.color.blue
        }
    }

    private fun getFormattedDate(task: Task, useExtended: Boolean): String {
        val date = task.date ?: return ""

        return if (useExtended) {
            val formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", Locale("pt", "BR"))
            date.format(formatter)
        } else {
            task.formatDate()
        }
    }
}