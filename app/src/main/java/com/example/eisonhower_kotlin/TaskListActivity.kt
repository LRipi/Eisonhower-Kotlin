package com.example.eisonhower_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.eisonhower_kotlin.network.EisonhowerService
import com.example.eisonhower_kotlin.network.RegisterData
import com.example.eisonhower_kotlin.network.responseObject.Register
import com.example.eisonhower_kotlin.network.responseObject.Task
import com.example.eisonhower_kotlin.network.responseObject.Tasks
import com.example.eisonhower_kotlin.ui.login.LoginActivity
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Collections.list

class TaskListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        var styleKey = intent.getStringExtra("Style")

        var style = when (styleKey) {
            "UrgentImportant" -> R.drawable.urgent_important_style
            "NotUrgentImportant" -> R.drawable.not_urgent_important
            "UrgentNotImportant" -> R.drawable.urgent_not_important_style
            else -> R.drawable.not_important_not_urgent_style
        }

        val taskList = findViewById<ListView>(R.id.task_list)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val eisonhowerService = retrofit.create(EisonhowerService::class.java)
        val importance = when (styleKey) {
            "UrgentImportant" -> true
            "NotUrgentImportant" -> true
            "UrgentNotImportant" -> false
            else -> false
        }
        val urgence = when (styleKey) {
            "UrgentImportant" -> true
            "NotUrgentImportant" -> false
            "UrgentNotImportant" -> true
            else -> false
        }

        val callAsync = eisonhowerService.listOfTasks(this@TaskListActivity.intent.getStringExtra("JWT_TOKEN"), importance, urgence)

        callAsync.enqueue(object: Callback<Tasks>
        {

            override fun onResponse(call: retrofit2.Call<Tasks>, response: Response<Tasks>)
            {
                if (response.isSuccessful())
                {
                    val r = response.body()
                    val listData = arrayOfNulls<String>(r?.tasks!!.size)
                    val idList = arrayOfNulls<String>(r?.tasks!!.size)
                    val tasksList = arrayOfNulls<Task>(r?.tasks!!.size)
                    for (i in 0 until r?.tasks!!.size)
                    {
                        listData[i] = r.tasks!![i].title
                        tasksList[i] = r.tasks!![i]
                        idList[i] = r.tasks!![i].id.toString()
                    }
                    val adapter = TaskListAdapter(
                        this@TaskListActivity,
                        listData as Array<String>,
                        idList as Array<String>,
                        tasksList as Array<Task>,
                        this@TaskListActivity.intent.getStringExtra("JWT_TOKEN"),
                        style
                    )

                    taskList.adapter = adapter
                }
                else
                {
                    System.out.println("Request Error :: " + response.code() + "\nReponse message :: " + response.message());
                }
            }

            override fun onFailure(call: retrofit2.Call<Tasks>, t: Throwable)
            {
                Log.e("Api_test_call", "Error: " + t.getLocalizedMessage());
            }
        })
    }
}
