package com.example.eisonhower_kotlin

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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

        val retrofit = Retrofit.Builder()
            .baseUrl("http://vps.lemartret.com:3000/")
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

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matrix)


        val urgentNotImportantCard = findViewById<ConstraintLayout>(R.id.urgent_not_important_layout)
        val urgentImportantCard = findViewById<ConstraintLayout>(R.id.urgent_important_layout)
        val notUrgentImportantCard = findViewById<ConstraintLayout>(R.id.not_urgent_important_layout)
        val notUrgentNotImportantCard = findViewById<ConstraintLayout>(R.id.not_urgent_not_important_layout)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://vps.lemartret.com:3000/")
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
            //Toast.makeText(this@MatrixActivity, relativeview.z.toString() + " " + Toto.z.toString() , Toast.LENGTH_SHORT).show()
       //}

    }
}
