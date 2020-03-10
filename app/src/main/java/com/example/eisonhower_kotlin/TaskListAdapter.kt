package com.example.eisonhower_kotlin

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity

class TaskListAdapter(private val context: Context,
                      private val dataSource: Array<String>) : BaseAdapter() {

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

    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.task_card , parent, false)


        val card = rowView.findViewById<TextView>(R.id.task_tile)

        //how to setup style based on the input category
        card.background = context.resources.getDrawable(R.drawable.urgent_important_style)

        val check_button = rowView.findViewById<FontAwesome>(R.id.check_button)

        // validate the task
        check_button.setOnClickListener {
            Toast.makeText(context, "click button", Toast.LENGTH_SHORT).show()
        }

        // edit task
        card.setOnClickListener{
            Toast.makeText(context, "click card", Toast.LENGTH_SHORT).show()

            val intent = Intent(context, EditTaskActivity::class.java)
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