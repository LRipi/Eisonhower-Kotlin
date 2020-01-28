package com.example.eisonhower_kotlin

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_matrix.view.*

class MatrixActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matrix)

        val urgentNotImportantCard = findViewById<ConstraintLayout>(R.id.urgent_not_important_layout)

        urgentNotImportantCard.setOnClickListener {
            val intent = Intent(this@MatrixActivity, TaskListActivity::class.java)
            startActivity(intent)
        }


        //urgentImportantButton.setOnClickListener {
            //Toast.makeText(this@MatrixActivity, relativeview.z.toString() + " " + Toto.z.toString() , Toast.LENGTH_SHORT).show()
       //}

    }
}
