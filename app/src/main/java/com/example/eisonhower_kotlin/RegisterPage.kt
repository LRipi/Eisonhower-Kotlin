package com.example.eisonhower_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_edit_task.*
import kotlinx.android.synthetic.main.activity_register_page.*

class RegisterPage : AppCompatActivity() {

    fun spawnPopup(message: String)
    {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        val registerButton = findViewById<Button>(R.id.registerButton)
        val emailTextBox = findViewById<EditText>(R.id.emailTextbox)

        registerButton.setOnClickListener {
            spawnPopup(emailTextBox.text.toString())
        }
    }
}
