package com.paulomatheus.mytasks.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.paulomatheus.mytasks.R
import com.paulomatheus.mytasks.databinding.ActivityFormBinding
import com.paulomatheus.mytasks.entity.Task
import com.paulomatheus.mytasks.service.TaskService

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding

    private lateinit var taskService:  TaskService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        taskService = TaskService()
        initComponents()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initComponents() {
        binding.layoutTitle.error = null
        binding.btSave.setOnClickListener {
            if (binding.etTitle.text.isNullOrEmpty()) {
                binding.layoutTitle.error = ContextCompat.getString(this, R.string.title_required)
            } else {
                val task = Task(title = binding.etTitle.text.toString())
                taskService.create(task)
                finish()
            }
        }
    }



}
