package com.example.eisonhower_kotlin

import android.content.DialogInterface
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.eisonhower_kotlin.network.EisonhowerService
import com.example.eisonhower_kotlin.network.RegisterData
import com.example.eisonhower_kotlin.network.responseObject.NumberOfTasks
import com.example.eisonhower_kotlin.network.responseObject.Register
import com.example.eisonhower_kotlin.ui.login.LoginActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_matrix.view.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MatrixActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()

        val urgentNotImportantCard = findViewById<ConstraintLayout>(R.id.urgent_not_important_layout)
        val urgentImportantCard = findViewById<ConstraintLayout>(R.id.urgent_important_layout)
        val notUrgentImportantCard = findViewById<ConstraintLayout>(R.id.not_urgent_important_layout)
        val notUrgentNotImportantCard = findViewById<ConstraintLayout>(R.id.not_urgent_not_important_layout)

        refreshTaskNumber(urgentNotImportantCard, urgentImportantCard, notUrgentImportantCard, notUrgentNotImportantCard)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matrix)


        val urgentNotImportantCard = findViewById<ConstraintLayout>(R.id.urgent_not_important_layout)
        val urgentImportantCard = findViewById<ConstraintLayout>(R.id.urgent_important_layout)
        val notUrgentImportantCard = findViewById<ConstraintLayout>(R.id.not_urgent_important_layout)
        val notUrgentNotImportantCard = findViewById<ConstraintLayout>(R.id.not_urgent_not_important_layout)

        val addTaskButton = findViewById<FloatingActionButton>(R.id.addTextButton)

        refreshTaskNumber(urgentNotImportantCard, urgentImportantCard, notUrgentImportantCard, notUrgentNotImportantCard)

        addTaskButton.setOnClickListener{
            val intent = Intent(this@MatrixActivity, EditTaskActivity::class.java)
            intent.putExtra("JWT_TOKEN", this@MatrixActivity.intent.getStringExtra("JWT_TOKEN"))
            startActivity(intent)
        }


        urgentNotImportantCard.setOnClickListener {
            val intent = Intent(this@MatrixActivity, TaskListActivity::class.java)
            intent.putExtra("Style", "UrgentNotImportant")
            intent.putExtra("JWT_TOKEN", this@MatrixActivity.intent.getStringExtra("JWT_TOKEN"))
            startActivity(intent)
        }

        urgentImportantCard.setOnClickListener {
            val intent = Intent(this@MatrixActivity, TaskListActivity::class.java)
            intent.putExtra("Style", "UrgentImportant")
            intent.putExtra("JWT_TOKEN", this@MatrixActivity.intent.getStringExtra("JWT_TOKEN"))
            startActivity(intent)
        }

        notUrgentImportantCard.setOnClickListener {
            val intent = Intent(this@MatrixActivity, TaskListActivity::class.java)
            intent.putExtra("Style", "NotUrgentImportant")
            intent.putExtra("JWT_TOKEN", this@MatrixActivity.intent.getStringExtra("JWT_TOKEN"))
            startActivity(intent)
        }

        notUrgentNotImportantCard.setOnClickListener {
            val intent = Intent(this@MatrixActivity, TaskListActivity::class.java)
            intent.putExtra("Style", "NotUrgentNotImportant")
            intent.putExtra("JWT_TOKEN", this@MatrixActivity.intent.getStringExtra("JWT_TOKEN"))
            startActivity(intent)
        }

        //urgentImportantButton.setOnClickListener {
        //   Toast.makeText(this@MatrixActivity, relativeview.z.toString() + " " + Toto.z.toString() , Toast.LENGTH_SHORT).show()
        //}

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val eisonhowerService = retrofit.create(EisonhowerService::class.java)
        if (R.id.disconnectMenuButton == item.itemId)
        {
            Toast.makeText(applicationContext, "disconnect", Toast.LENGTH_LONG).show()
            val intent = Intent(this@MatrixActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        if (R.id.eraseAllTasksMenuButton == item.itemId)
        {
            val alertDialog: AlertDialog? = this@MatrixActivity?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setMessage("Are you sur you want to erase all task ?")
                    setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
            eisonhowerService.deleteAllTasks(this@MatrixActivity.intent.getStringExtra("JWT_TOKEN")).enqueue(object: Callback<Void> {
                override fun onResponse(call: retrofit2.Call<Void>, response: Response<Void>)
                {
                    if (response.isSuccessful())
                    {
                        Toast.makeText(applicationContext, "All tasks erased", Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        System.out.println("Request Error :: " + response.code() + "\nReponse message :: " + response.message());
                    }
                }

                override fun onFailure(call: retrofit2.Call<Void>, t: Throwable)
                {
                    Log.e("Api_test_call", "Error: " + t.getLocalizedMessage());
                }
            })
                    })
                    setNegativeButton("Cancel",
                        DialogInterface.OnClickListener { dialog, id ->
                            // User cancelled the dialog
                        })
                }
                builder.create()
            }
            alertDialog?.show()
            Toast.makeText(applicationContext, "erase tasks", Toast.LENGTH_LONG).show()
            return true
        }
        if (R.id.deleteAccountMenuButton == item.itemId)
        {
            val alertDialog: AlertDialog? = this@MatrixActivity?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setMessage("Are you sur you want to delete your acount")
                    setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                            // User clicked OK button
                        eisonhowerService.deleteUser(this@MatrixActivity.intent.getStringExtra("JWT_TOKEN")).enqueue(object: Callback<Void> {
                            override fun onResponse(call: retrofit2.Call<Void>, response: Response<Void>)
                            {
                                if (response.isSuccessful())
                                {
                                    Toast.makeText(applicationContext, "User deleted", Toast.LENGTH_LONG).show()
                                    val intent = Intent(this@MatrixActivity, LoginActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                                else
                                {
                                    System.out.println("Request Error :: " + response.code() + "\nReponse message :: " + response.message());
                                }
                            }

                            override fun onFailure(call: retrofit2.Call<Void>, t: Throwable)
                            {
                                Log.e("Api_test_call", "Error: " + t.getLocalizedMessage());
                            }
                        })
                        })
                    setNegativeButton("Cancel",
                        DialogInterface.OnClickListener { dialog, id ->
                            // User cancelled the dialog
                        })
                }
                builder.create()
            }
            alertDialog?.show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun refreshTaskNumber(urgentNotImportantCard : ConstraintLayout,
                          urgentImportantCard : ConstraintLayout,
                          notUrgentImportantCard  : ConstraintLayout,
                          notUrgentNotImportantCard : ConstraintLayout
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val eisonhowerService = retrofit.create(EisonhowerService::class.java)
        val callAsync = eisonhowerService.numberOfTasks(this@MatrixActivity.intent.getStringExtra("JWT_TOKEN"), false, true)

        callAsync.enqueue(object: Callback<NumberOfTasks>
        {

            override fun onResponse(call: retrofit2.Call<NumberOfTasks>, response: Response<NumberOfTasks>)
            {
                if (response.isSuccessful())
                {
                    val r = response.body()
                    urgentNotImportantCard.urgent_not_important_amount.text = r?.number.toString();
                }
                else
                {
                    System.out.println("Request Error :: " + response.code() + "\nReponse message :: " + response.message());
                }
            }

            override fun onFailure(call: retrofit2.Call<NumberOfTasks>, t: Throwable)
            {
                Log.e("Api_test_call", "Error: " + t.getLocalizedMessage());
            }
        })

        eisonhowerService.numberOfTasks(this@MatrixActivity.intent.getStringExtra("JWT_TOKEN"), true, true).enqueue(object: Callback<NumberOfTasks>
        {

            override fun onResponse(call: retrofit2.Call<NumberOfTasks>, response: Response<NumberOfTasks>)
            {
                if (response.isSuccessful())
                {
                    val r = response.body()
                    urgentImportantCard.urgent_important_amount.text = r?.number.toString();
                }
                else
                {
                    System.out.println("Request Error :: " + response.code() + "\nReponse message :: " + response.message());
                }
            }

            override fun onFailure(call: retrofit2.Call<NumberOfTasks>, t: Throwable)
            {
                Log.e("Api_test_call", "Error: " + t.getLocalizedMessage());
            }
        })

        eisonhowerService.numberOfTasks(this@MatrixActivity.intent.getStringExtra("JWT_TOKEN"), true, false).enqueue(object: Callback<NumberOfTasks>
        {

            override fun onResponse(call: retrofit2.Call<NumberOfTasks>, response: Response<NumberOfTasks>)
            {
                if (response.isSuccessful())
                {
                    val r = response.body()
                    notUrgentImportantCard.not_urgent_important_amount.text = r?.number.toString();
                }
                else
                {
                    System.out.println("Request Error :: " + response.code() + "\nReponse message :: " + response.message());
                }
            }

            override fun onFailure(call: retrofit2.Call<NumberOfTasks>, t: Throwable)
            {
                Log.e("Api_test_call", "Error: " + t.getLocalizedMessage());
            }
        })

        eisonhowerService.numberOfTasks(this@MatrixActivity.intent.getStringExtra("JWT_TOKEN"), false, false).enqueue(object: Callback<NumberOfTasks>
        {

            override fun onResponse(call: retrofit2.Call<NumberOfTasks>, response: Response<NumberOfTasks>)
            {
                if (response.isSuccessful())
                {
                    val r = response.body()
                    notUrgentNotImportantCard.not_urgent_not_important_amount.text = r?.number.toString();
                }
                else
                {
                    System.out.println("Request Error :: " + response.code() + "\nReponse message :: " + response.message());
                }
            }

            override fun onFailure(call: retrofit2.Call<NumberOfTasks>, t: Throwable)
            {
                Log.e("Api_test_call", "Error: " + t.getLocalizedMessage());
            }
        })
    }
}
