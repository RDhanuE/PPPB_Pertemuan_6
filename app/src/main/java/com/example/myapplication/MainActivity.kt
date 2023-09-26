package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val description = arrayOf(
        "Hadir tepat waktu",
        "Sakit",
        "Terlambat",
        "Izin"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            val adapterDescription = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, description)
            adapterDescription.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerDescription.adapter = adapterDescription

            spinnerDescription.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        if (description[position] == "Sakit" || description[position] == "Izin"){
                            editTextDescription.visibility = View.VISIBLE
                        }
                        else {
                            editTextDescription.visibility = View.INVISIBLE
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }

            submit.setOnClickListener{
                val year = datePicker.year
                val month = datePicker.month + 1
                val monthFormat = SimpleDateFormat("MMMM", Locale("id", "ID"))
                val monthName = monthFormat.format(month)
                val dayOfMonth = datePicker.dayOfMonth

                val hour = timePicker.hour
                var amPm = "AM"
                if(hour > 12) {
                    amPm = "PM"
                } else {
                    amPm = "AM"
                }
                val minute = timePicker.minute

                val message = String.format("Presensi Berhasil $dayOfMonth $monthName $year jam %02d:%02d $amPm", hour, minute)

                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}