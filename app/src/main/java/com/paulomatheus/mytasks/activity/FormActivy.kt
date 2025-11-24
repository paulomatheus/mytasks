package com.paulomatheus.mytasks.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.paulomatheus.mytasks.databinding.ActivityFormBinding

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e("Lifecycle", "onStart")
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
}
