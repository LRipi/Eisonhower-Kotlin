package com.example.eisonhower_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.eisonhower_kotlin.network.EisonhowerService
import com.example.eisonhower_kotlin.network.LoginData
import com.example.eisonhower_kotlin.network.RegisterData
import com.example.eisonhower_kotlin.network.responseObject.Login
import com.example.eisonhower_kotlin.network.responseObject.Register
import com.example.eisonhower_kotlin.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_edit_task.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register_page.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
        val passwordTextBox = findViewById<EditText>(R.id.editText2)

        registerButton.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://vps.lemartret.com:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val eisonhowerService = retrofit.create(EisonhowerService::class.java)
            val callAsync = eisonhowerService.register(RegisterData(emailTextBox.text.toString(), passwordTextBox.text.toString(), emailTextBox.text.toString()))

            callAsync.enqueue(object: Callback<Register>
            {

                override fun onResponse(call: retrofit2.Call<Register>, response: Response<Register>)
                {
                    if (response.isSuccessful())
                    {
                            val nextScreenIntent = Intent(this@RegisterPage, LoginActivity::class.java)
                            startActivity(nextScreenIntent)
                        runOnUiThread {
                            Toast.makeText(applicationContext, "Your account has been created", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else
                    {
                        System.out.println("Request Error :: " + response.code() + "\nReponse message :: " + response.message())
                        runOnUiThread {
                            Toast.makeText(applicationContext, "Error from server", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: retrofit2.Call<Register>, t: Throwable)
                {
                    Log.e("Api_test_call", "Error: " + t.getLocalizedMessage());
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Check your internet connection", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }
}
