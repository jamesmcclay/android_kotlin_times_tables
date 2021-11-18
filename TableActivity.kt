package com.example.timestables

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.timestables.databinding.ActivityTableBinding

class TableActivity : AppCompatActivity() {

    var mode:String = ""
    var tableMode = "normal"
    var table:Int = 0
    var multiplier = 1
    var answerShowing = false

    lateinit var appBarConfiguration: AppBarConfiguration
    //lateinit var binding: ActivityTableBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityTableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //actionbar
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Practice!"

        val question = binding.textviewQuestion
        val answer = binding.textviewAnswer
        val button = binding.buttonShow

        mode = intent.getStringExtra("mode").toString()
        table = intent.getStringExtra("table").toString().toInt()
        if (table == 0) {
            tableMode = "all"
            table = 2
        }

	nextQuestion(question,answer,button)

        button.setOnClickListener {
            if (answerShowing == false) {
                showAnswer(question, answer, button)
            }else {
                nextQuestion(question, answer, button)
            }
        }
    }

    fun nextQuestion(question:TextView, answer:TextView, button: Button) {
        if (mode == "random") {
            multiplier = (2..12).random()
            if (tableMode == "all") {
                table = (2..9).random()
            }
        }else{
            multiplier += 1
            if (multiplier == 13) {
                multiplier = 2
                if (tableMode == "all") {
                    table += 1
                }
            }
        }
        question.text = "$table X $multiplier = "
        answerShowing = false
        answer.text = ""
        button.text = "Show Answer"
    }

    fun showAnswer(question:TextView, answer:TextView, button: Button) {
        val theAnswer = table * multiplier
        answer.text = "$theAnswer"
        answerShowing = true
        button.text = "Next"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
