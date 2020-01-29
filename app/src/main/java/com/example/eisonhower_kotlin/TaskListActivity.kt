package com.example.eisonhower_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class TaskListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        val taskList = findViewById<ListView>(R.id.task_list)

        val listData = arrayOfNulls<String>(10)
        for (i in 0 until 10)
        {
            listData[i] = "toto jkj sdf dsf dsdfhsdjfh ds fdjf jdsh fdshf dfkjhdskfj sdkjf skdjfjhsdh flksdfkhds fjksdhf"
        }

        val adapter = TaskListAdapter(this, listData as Array<String>)

        taskList.adapter = adapter


    }
}
