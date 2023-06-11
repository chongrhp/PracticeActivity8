package com.example.practiceactivity8

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.practiceactivity8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toggleButton.setOnClickListener {
            if(binding.toggleButton.isChecked) {
                Toast.makeText(applicationContext,"Celsius",Toast.LENGTH_SHORT).show()
                binding.toggleButton.text = "Celsius"
            } else {
                Toast.makeText(applicationContext,"Fahrenheit",Toast.LENGTH_SHORT).show()
                binding.toggleButton.text = "Fahrenheit"
            }
        }

        binding.chkSunny.setOnClickListener {
            if(binding.chkSunny.isChecked) Toast.makeText(applicationContext,binding.chkSunny.text,Toast.LENGTH_SHORT).show()
        }

        binding.chkCloudy.setOnClickListener {
            if(binding.chkCloudy.isChecked) Toast.makeText(applicationContext,binding.chkCloudy.text,Toast.LENGTH_SHORT).show()
        }

        binding.chkRainy.setOnClickListener {
            if(binding.chkRainy.isChecked) Toast.makeText(applicationContext,binding.chkRainy.text,Toast.LENGTH_SHORT).show()
        }

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.option1 -> Toast.makeText(applicationContext,binding.option1.text,Toast.LENGTH_SHORT).show()
                R.id.option2 -> Toast.makeText(applicationContext,binding.option2.text,Toast.LENGTH_SHORT).show()
            }
        }

        val spinnerItems = arrayOf("New York","London","Tokyo")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id:Long
            ){
                val selectedItem = spinnerItems[position]
                Toast.makeText(applicationContext, selectedItem, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(applicationContext,"Noting is selected", Toast.LENGTH_SHORT).show()
            }
        }

        binding.frameLayout.setOnClickListener { showDialog() }

    }


    private fun showDialog(){
        val alertDialogBuilder = AlertDialog.Builder(this)
        var weatherUpdate:String = ""

        weatherUpdate = "\nLOCATION: ${binding.spinner.selectedItem.toString()}"
        weatherUpdate = "$weatherUpdate\n\nDegrees in ${binding.toggleButton.text}\n"
        if(binding.chkCloudy.isChecked) weatherUpdate = "$weatherUpdate\n${binding.chkCloudy.text}"
        if(binding.chkRainy.isChecked) weatherUpdate = "$weatherUpdate\n${binding.chkRainy.text}"
        if(binding.chkSunny.isChecked) weatherUpdate ="$weatherUpdate\n${binding.chkSunny.text}"
        if(binding.option1.isChecked) weatherUpdate ="$weatherUpdate\n\n${binding.option1.text} is selected"
        if(binding.option2.isChecked) weatherUpdate = "$weatherUpdate\n\n${binding.option2.text} is selected"

        alertDialogBuilder.setTitle("WEATHER CONDITION")
        alertDialogBuilder.setMessage("$weatherUpdate")

        alertDialogBuilder.setNegativeButton("OK"){ _: DialogInterface, _:Int ->
            Toast.makeText(applicationContext,"Ok is clicked", Toast.LENGTH_SHORT).show()
        }

        alertDialogBuilder.setPositiveButton("Cancel"){ _: DialogInterface, _:Int ->
            Toast.makeText(applicationContext,"Cancel is clicked", Toast.LENGTH_SHORT).show()
        }

        alertDialogBuilder.create().show()
    }
}