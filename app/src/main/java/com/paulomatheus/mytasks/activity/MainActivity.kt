package com.paulomatheus.mytasks.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.paulomatheus.mytasks.adapter.ListAdapter
import com.paulomatheus.mytasks.databinding.ActivityMainBinding
import com.paulomatheus.mytasks.entity.Task

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ListAdapter


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
        adapter.addItem(Task(title = "Minha tarefa", date = "06/11/2025"))
    }
    private fun initComponents() {
        adapter = ListAdapter()
        binding.rvMain.adapter = adapter

        /*for (i in 1..20) {
            adapter.addItem(Task(title = "Minha tarefa $i", date = "06/11/2025"))
        }*/

        binding.fabNew.setOnClickListener {
            startActivity(Intent(this, FormActivity::class.java))
        }

    }
}