package com.example.eisonhower_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

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

        val listData = arrayOfNulls<String>(10)
        for (i in 0 until 10)
        {
            listData[i] = "toto jkj sdf dsf dsdfhsdjfh ds fdjf jdsh fdshf dfkjhdskfj sdkjf skdjfjhsdh flksdfkhds fjksdhf"
        }

        val adapter = TaskListAdapter(this, listData as Array<String>, style)

        taskList.adapter = adapter


    }
}
