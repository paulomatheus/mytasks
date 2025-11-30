package com.paulomatheus.mytasks.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import com.paulomatheus.mytasks.R
import com.paulomatheus.mytasks.adapter.ListAdapter
import com.paulomatheus.mytasks.adapter.TouchCallback
import com.paulomatheus.mytasks.databinding.ActivityMainBinding
import com.paulomatheus.mytasks.entity.Task
import com.paulomatheus.mytasks.listener.SwipeListener
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
        binding.tvMessage.visibility = View.GONE
        adapter = ListAdapter(this,binding.tvMessage)
        binding.rvMain.adapter = adapter

        binding.fabNew.setOnClickListener {
            startActivity(Intent(this, FormActivity::class.java))
        }

        ItemTouchHelper(TouchCallback(object : SwipeListener {
            override fun onSwipe(position: Int) {
                adapter.getItem(position).id?.let {
                    taskService.delete(it).observe(this@MainActivity) { response ->
                        if (response.error){
                            adapter.notifyItemChanged(position)
                        } else{
                            adapter.removeItem(position)
                        }
                    }
                }
            }

        })).attachToRecyclerView(binding.rvMain)

        binding.srlMain.setOnRefreshListener {
            getTasks()
        }

    }

    private fun getTasks() {
        taskService.list()
            .observe(this) { response ->
                binding.srlMain.isRefreshing = false
                if (response.error) {
                    binding.tvMessage.visibility = View.VISIBLE
                    binding.tvMessage.text = ContextCompat.getString(this, R.string.server_error)
                } else {
                    response.value?.let {
                            adapter.setData(it) //it referencia o value do response

                    } ?: run {
                        binding.tvMessage.visibility = View.VISIBLE
                        binding.tvMessage.text = ContextCompat.getString(this, R.string.empty_list)
                    }
                }
            }
    }
}