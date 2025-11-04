package com.paulomatheus.mytasks

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.paulomatheus.mytasks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater) //inflar é transformar um xml em um objeto do android (codigo kotlin)
        setContentView(binding.root)

        //findViewById<TextView>(R.id.tvMain).text = "Outro texto"


    }
}