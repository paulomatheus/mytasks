package com.paulomatheus.mytasks.activity

import android.os.Bundle
import android.content.Intent
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.paulomatheus.mytasks.R
import com.paulomatheus.mytasks.databinding.ActivityFormBinding
import com.paulomatheus.mytasks.entity.Task
import com.paulomatheus.mytasks.extension.hasValue
import com.paulomatheus.mytasks.extension.value
import com.paulomatheus.mytasks.service.TaskService
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import java.util.Calendar
import java.util.Locale

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding

    private val taskService: TaskService by viewModels()

    private var taskId: Long? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent.extras?.getSerializable("task")?.let { extra ->
            val task = extra as Task

            binding.etTitle.setText(task.title)
            binding.etDescription.setText(task.description)
            binding.etDate.setText(task.formatDate())
            binding.etTime.setText(task.formatTime())
        }

        intent.extras?.getString(Intent.EXTRA_TEXT)?.let { text ->
            binding.etTitle.setText(text)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }

        initComponents()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initComponents() {
        binding.etDate.setOnClickListener {
            showDatePicker()
        }

        binding.etTime.setOnClickListener {
            showTimePicker()
        }

        binding.layoutTitle.error = null
        binding.btSave.setOnClickListener {
            if (binding.etTitle.text.isNullOrEmpty()) {
                binding.layoutTitle.error = ContextCompat.getString(this, R.string.title_required)
            } else {
                val date = if (binding.etDate.hasValue()) {
                    LocalDate.parse(
                        binding.etDate.value(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    )
                } else null

                val time = if (binding.etTime.hasValue()) {
                    LocalTime.parse(binding.etTime.value(), DateTimeFormatter.ofPattern("HH:mm"))
                } else null

                val task = Task(
                    id = taskId,
                    title = binding.etTitle.value(),
                    description = binding.etDescription.value(),
                    date = date,
                    time = time
                )


                if (taskId == null) {
                    taskService.create(task).observe(this) { response ->
                        if (response.error) {
                            showAlert(R.string.create_error)
                        } else {
                            finish()
                        }
                    }
                } else {
                    taskService.update(task).observe(this) { response ->
                        if (response.error) {
                            showAlert(R.string.update_error)
                        } else {
                            finish()
                        }
                    }
                }
            }
        }

    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // O mês no Calendar começa em 0, por isso não precisamos somar +1 se usarmos formatação direta,
                // mas para exibir 01/01/2025, o month precisa ser tratado.
                // O formato deve bater com o DateTimeFormatter "dd/MM/yyyy" que você usa no save
                val formattedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
                binding.etDate.setText(formattedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                // Formata para manter dois dígitos (ex: 09:05)
                val formattedTime = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute)
                binding.etTime.setText(formattedTime)
            },
            hour,
            minute,
            true // true para formato 24 horas
        )
        timePickerDialog.show()
    }

    private fun showAlert(message: Int) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setNeutralButton(android.R.string.ok, null)
            .create()
            .show()
    }
}


