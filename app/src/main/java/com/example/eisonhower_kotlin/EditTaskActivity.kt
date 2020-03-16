package com.example.eisonhower_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.example.eisonhower_kotlin.network.EisonhowerService
import com.example.eisonhower_kotlin.network.RegisterData
import com.example.eisonhower_kotlin.network.responseObject.Register
import com.example.eisonhower_kotlin.network.toCreateTask
import com.example.eisonhower_kotlin.ui.login.LoginActivity
import com.google.android.material.textfield.TextInputEditText
import com.xw.repo.BubbleSeekBar
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

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

        save_button.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://vps.lemartret.com:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val eisonhowerService = retrofit.create(EisonhowerService::class.java)
            val callAsync = eisonhowerService.createTask(
                this@EditTaskActivity.intent.getStringExtra("JWT_TOKEN"),
                toCreateTask(
                    urgency_gauge.progress.toString(),
                    importance_gauge.progress.toString(),
                    title.text.toString(),
                    description.text.toString(),
                    SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(Date()),
                    if (done_checkbox.isActivated == false) "open" else "closed"
                )
            )

            callAsync.enqueue(object : Callback<Void> {

                override fun onResponse(
                    call: retrofit2.Call<Void>,
                    response: Response<Void>
                ) {
                    if (response.isSuccessful()) {
                        val nextScreenIntent = Intent(this@EditTaskActivity, MatrixActivity::class.java)
                        nextScreenIntent.putExtra("JWT_TOKEN", this@EditTaskActivity.intent.getStringExtra("JWT_TOKEN"))
                        startActivity(nextScreenIntent)
                    } else {
                        System.out.println("Request Error :: " + response.code() + "\nReponse message :: " + response.message())
                    }
                }

                override fun onFailure(call: retrofit2.Call<Void>, t: Throwable) {
                    Log.e("Api_test_call", "Error: " + t.getLocalizedMessage());
                }
            })
        }
    }
}
