package com.example.eisonhower_kotlin

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.eisonhower_kotlin.network.EisonhowerService
import com.example.eisonhower_kotlin.network.responseObject.Task
import com.example.eisonhower_kotlin.network.toCreateTask
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class TaskListAdapter(private val context: Context,
                      private val dataSource: Array<String>,
                      private val idList : Array<String>,
                      private val tasksList : Array<Task>,
                      private val jwtToken : String,
                      private val style : Int) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater



    //1
    override fun getCount(): Int {
        return dataSource.size
    }

    //2
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun getTaskId(position: Int): String {
        return idList[position]
    }

    fun getTask(position: Int): Task {
        return tasksList[position]
    }

    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.task_card , parent, false)


        val card = rowView.findViewById<TextView>(R.id.task_tile)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://vps.lemartret.com:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val eisonhowerService = retrofit.create(EisonhowerService::class.java)

        //how to setup style based on the input category
        card.background = context.resources.getDrawable(style)

        val check_button = rowView.findViewById<FontAwesome>(R.id.check_button)

        // validate the task

        check_button.setOnClickListener {
            Log.d("Log", "id: " + this.getTask(position).id.toString() + "\njwt: " + jwtToken)
            eisonhowerService.deleteTask(
                jwtToken,
                this.getTask(position).id.toString()
            ).enqueue(object: Callback<Void> {
                override fun onResponse(
                    call: retrofit2.Call<Void>,
                    response: Response<Void>
                ) {
                    if (response.isSuccessful()) {
                        Toast.makeText(context, R.id.task_tile.toString() + " is done", Toast.LENGTH_SHORT).show()
                        val intent = Intent(context, EditTaskActivity::class.java)
                        intent.putExtra("JWT_TOKEN", jwtToken)
                        startActivity(context, intent, null)
                    } else {
                        System.out.println("Request Error :: " + response.code() + "\nReponse message :: " + response.message())
                        Toast.makeText(context, response.code().toString() + " " + response.message(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: retrofit2.Call<Void>, t: Throwable) {
                    Log.e("Api_test_call", "Error: " + t.getLocalizedMessage());
                }
            })
        }

        // edit task
        card.setOnClickListener{
            Toast.makeText(context, "click card", Toast.LENGTH_SHORT).show()

            val intent = Intent(context, EditTaskActivity::class.java)
            intent.putExtra("TASK_ID", getTaskId(position))
            intent.putExtra("JWT_TOKEN", jwtToken)
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(context, intent, null)

            //val intent = Intent(context, EditTaskActivity::class.java)
            //startActivity(intent)
        }

        // setup the title of the card
        card.text = getItem(position) as String

        return rowView
    }
}