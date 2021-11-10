package com.example.timestables

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import com.example.timestables.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var mode:String = "normal"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val button = binding.buttonStart
        button.setOnClickListener {
            val theIntent = Intent(this, TableActivity::class.java)
            var table = binding.edittextTimestable.text.toString()
            if (table == "") {
                table = "0"
            }
            Log.i("james", "$table")
            theIntent.putExtra("mode", mode)
            theIntent.putExtra("table", table)
            startActivity(theIntent)
        }
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_normal ->
                    if (checked) {
                        this.mode = "normal"
                    }
                R.id.radio_random ->
                    if (checked) {
                        this.mode = "random"
                    }
            }
        }
    }
}
