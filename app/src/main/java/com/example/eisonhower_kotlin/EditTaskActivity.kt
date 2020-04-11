package com.example.eisonhower_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.example.eisonhower_kotlin.network.EisonhowerService
import com.example.eisonhower_kotlin.network.responseObject.BaseTask
import com.example.eisonhower_kotlin.network.toCreateTask
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

        val retrofit = Retrofit.Builder()
            .baseUrl("http://vps.lemartret.com:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val eisonhowerService = retrofit.create(EisonhowerService::class.java)

        if (this@EditTaskActivity.intent.getStringExtra("TASK_ID") == null) {
            save_button.setOnClickListener {
                val callAsync = eisonhowerService.createTask(
                    this@EditTaskActivity.intent.getStringExtra("JWT_TOKEN"),
                    toCreateTask(
                        importance_gauge.progress.toString(),
                        urgency_gauge.progress.toString(),
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
                            val nextScreenIntent =
                                Intent(this@EditTaskActivity, MatrixActivity::class.java)
                            nextScreenIntent.putExtra(
                                "JWT_TOKEN",
                                this@EditTaskActivity.intent.getStringExtra("JWT_TOKEN")
                            )
                            startActivity(nextScreenIntent)
                            runOnUiThread{
                                Toast.makeText(applicationContext, "Task Created", Toast.LENGTH_SHORT).show()
                            }
                            finish()
                        } else {
                            System.out.println("Request Error :: " + response.code() + "\nReponse message :: " + response.message())
                            runOnUiThread {
                                Toast.makeText(applicationContext, "Error from server", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<Void>, t: Throwable) {
                        Log.e("Api_test_call", "Error: " + t.getLocalizedMessage());
                        runOnUiThread {
                            Toast.makeText(applicationContext, "Check your internet connection", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        } else {
            Log.d("EditActivity", this@EditTaskActivity.intent.getStringExtra("TASK_ID"))
            save_button.setOnClickListener {
                retrofit.create(EisonhowerService::class.java).updateTask(
                    this@EditTaskActivity.intent.getStringExtra("JWT_TOKEN"),
                    this@EditTaskActivity.intent.getStringExtra("TASK_ID"),
                    toCreateTask(
                        importance_gauge.progress.toString(),
                        urgency_gauge.progress.toString(),
                        title.text.toString(),
                        description.text.toString(),
                        SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(Date()),
                        if (done_checkbox.isActivated == false) "open" else "closed"
                    )
                ).enqueue(object : Callback<Void> {
                    override fun onResponse(
                        call: retrofit2.Call<Void>,
                        response: Response<Void>
                    ) {
                        if (response.isSuccessful()) {
                            val nextScreenIntent =
                                Intent(this@EditTaskActivity, MatrixActivity::class.java)
                            nextScreenIntent.putExtra(
                                "JWT_TOKEN",
                                this@EditTaskActivity.intent.getStringExtra("JWT_TOKEN")
                            )
                            startActivity(nextScreenIntent)
                            runOnUiThread{
                                Toast.makeText(applicationContext, "Task updated", Toast.LENGTH_SHORT).show()
                            }
                            finish()
                        } else {
                            System.out.println("Request Error :: " + response.code() + "\nReponse message :: " + response.message())
                            runOnUiThread {
                                Toast.makeText(applicationContext, "Error from server", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<Void>, t: Throwable) {
                        Log.e("Api_test_call", "Error: " + t.getLocalizedMessage());
                        runOnUiThread {
                            Toast.makeText(applicationContext, "Check your internet connection", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }

            findViewById<Button>(R.id.remove_button).setOnClickListener {
                Log.d("EditActivity", "erase ?")
                retrofit.create(EisonhowerService::class.java).deleteTask(
                    this@EditTaskActivity.intent.getStringExtra("JWT_TOKEN"),
                    this@EditTaskActivity.intent.getStringExtra("TASK_ID")
                ).enqueue(object: Callback<Void> {
                    override fun onResponse(
                        call: retrofit2.Call<Void>,
                        response: Response<Void>
                    ) {
                        if (response.isSuccessful()) {
                            val nextScreenIntent =
                                Intent(this@EditTaskActivity, MatrixActivity::class.java)
                            nextScreenIntent.putExtra(
                                "JWT_TOKEN",
                                this@EditTaskActivity.intent.getStringExtra("JWT_TOKEN")
                            )
                            startActivity(nextScreenIntent)
                            runOnUiThread{
                                Toast.makeText(applicationContext, "Task deleted", Toast.LENGTH_SHORT).show()
                            }
                            finish()
                        } else {
                            System.out.println("Request Error :: " + response.code() + "\nReponse message :: " + response.message())
                            runOnUiThread {
                                Toast.makeText(applicationContext, "Error from server", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<Void>, t: Throwable) {
                        Log.e("Api_test_call", "Error: " + t.getLocalizedMessage());
                        runOnUiThread {
                            Toast.makeText(applicationContext, "Check your internet connection", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }
}
