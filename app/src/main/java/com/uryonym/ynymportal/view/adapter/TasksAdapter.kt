package com.uryonym.ynymportal.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uryonym.ynymportal.R
import com.uryonym.ynymportal.data.Task

class TasksAdapter : RecyclerView.Adapter<TaskItemViewHolder>() {
    var data = listOf<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.task_item, parent, false) as TextView

        return TaskItemViewHolder(view)
    }
}

class TaskItemViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)