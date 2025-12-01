package com.paulomatheus.mytasks.activity

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.paulomatheus.mytasks.R
import com.paulomatheus.mytasks.adapter.ListAdapter
import com.paulomatheus.mytasks.adapter.TouchCallback
import com.paulomatheus.mytasks.databinding.ActivityMainBinding
import com.paulomatheus.mytasks.entity.Task
import com.paulomatheus.mytasks.listener.ClickListener
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
        askNotificationPermission()
    }

    override fun onResume() {
        super.onResume()
        getTasks()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.preferences) {
            startActivity(Intent(this, PreferenceActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initComponents() {
        binding.tvMessage.visibility = View.INVISIBLE
        adapter = ListAdapter(this,binding.tvMessage, object : ClickListener{
            override fun OnComplete(id: Long) {
                taskService.complete(id).observe(this@MainActivity) { response ->
                    if(!response.error){
                        getTasks()
                    }
                }
            }

        })
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

    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
                PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),){
        isGranted: Boolean ->
        if(!isGranted){
            if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                AlertDialog.Builder(this)
                    .setTitle(R.string.permission)
                    .setMessage(R.string.notification_permission_rationale)
                    .setPositiveButton(android.R.string.ok
                    ) { dialog, which -> null }
                    .setNegativeButton(android.R.string.cancel, null)
                    .create()
                    .show()
            }
        } else{

        }
    }
}