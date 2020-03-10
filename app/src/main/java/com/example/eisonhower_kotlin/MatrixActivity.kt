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
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_matrix.view.*

class MatrixActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        // call API
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matrix)


        val urgentNotImportantCard = findViewById<ConstraintLayout>(R.id.urgent_not_important_layout)
        val urgentImportantCard = findViewById<ConstraintLayout>(R.id.urgent_important_layout)
        val notUrgentImportantCard = findViewById<ConstraintLayout>(R.id.not_urgent_important_layout)
        val notUrgentNotImportantCard = findViewById<ConstraintLayout>(R.id.not_urgent_not_important_layout)


        urgentNotImportantCard.setOnClickListener {
            val intent = Intent(this@MatrixActivity, TaskListActivity::class.java)
            intent.putExtra("Style", "UrgentNotImportant")
            startActivity(intent)
        }

        urgentImportantCard.setOnClickListener {
            val intent = Intent(this@MatrixActivity, TaskListActivity::class.java)
            intent.putExtra("Style", "UrgentImportant")
            startActivity(intent)
        }

        notUrgentImportantCard.setOnClickListener {
            val intent = Intent(this@MatrixActivity, TaskListActivity::class.java)
            intent.putExtra("Style", "NotUrgentImportant")
            startActivity(intent)
        }

        notUrgentNotImportantCard.setOnClickListener {
            val intent = Intent(this@MatrixActivity, TaskListActivity::class.java)
            intent.putExtra("Style", "NotUrgentNotImportant")
            startActivity(intent)
        }



        //urgentImportantButton.setOnClickListener {
            //Toast.makeText(this@MatrixActivity, relativeview.z.toString() + " " + Toto.z.toString() , Toast.LENGTH_SHORT).show()
       //}

    }
}
