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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding =
            ActivityMainBinding.inflate(layoutInflater) //inflar é transformar um xml em um objeto do android (codigo kotlin)
        setContentView(binding.root)

        //findViewById<TextView>(R.id.tvMain).text = "Outro texto"
        initComponents()

        Log.e("Lifecycle", "onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.e("Lifecycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("Lifecycle", "onPause")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Lifecycle", "onDestroy")
    }

    private fun initComponents() {
        val adapter = ListAdapter()
        binding.rvMain.adapter = adapter

        for (i in 1..20) {
            adapter.addItem(Task(title = "Minha tarefa $i", date = "06/11/2025"))
        }

        binding.fabNew.setOnClickListener {
            startActivity(Intent(this, FormActivity::class.java))
        }

    }
}