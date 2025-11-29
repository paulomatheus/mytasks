package com.paulomatheus.mytasks.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.paulomatheus.mytasks.adapter.ListAdapter
import com.paulomatheus.mytasks.databinding.ActivityMainBinding
import com.paulomatheus.mytasks.entity.Task
import com.paulomatheus.mytasks.service.TaskService

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ListAdapter
    private val taskService: TaskService by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding =
            ActivityMainBinding.inflate(layoutInflater) //inflar é transformar um xml em um objeto do android (codigo kotlin)
        setContentView(binding.root)

        //findViewById<TextView>(R.id.tvMain).text = "Outro texto"
        initComponents()
    }

    override fun onResume() {
        super.onResume()
        getTasks()
    }
    private fun initComponents() {
        adapter = ListAdapter()
        binding.rvMain.adapter = adapter

        binding.fabNew.setOnClickListener {
            startActivity(Intent(this, FormActivity::class.java))
        }
    }

    private fun getTasks(){
        taskService.list()
            .observe(this) { response ->
                adapter.setData(response)
            }

    }
}