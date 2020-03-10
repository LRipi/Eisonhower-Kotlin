package com.example.eisonhower_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.xw.repo.BubbleSeekBar

class EditTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)

        val save_button = findViewById<Button>(R.id.save_button)

        val title = findViewById<TextInputEditText>(R.id.title_input)
        val description = findViewById<TextInputEditText>(R.id.description_input)

        val urgency_gauge = findViewById<BubbleSeekBar>(R.id.urgency_bar)
        val importance_gauge = findViewById<BubbleSeekBar>(R.id.importance_bar)

        val done_checkbox = findViewById<CheckBox>(R.id.checkBox)

        save_button.setOnClickListener{
            Toast.makeText(this,
                "task: " + title.text
                + " has a description of: " + description.text
                + " is important: " + importance_gauge.progress
                + " and urgent: " + urgency_gauge.progress
                + " and is done: " + done_checkbox.isChecked
                , Toast.LENGTH_SHORT).show()
        }

    }
}
