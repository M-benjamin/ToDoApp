package com.example.todo.tasklist

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import kotlinx.android.synthetic.main.task_item.view.*
import com.example.todo.model.Task

class TaskListAdapter(val taskList: MutableList<Task>, val onClickDelete: (Task) -> Unit, val onClickOpenTask: (Task) -> Unit, val onClickCloseTask: (Task) -> Unit) : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int = taskList.size


    fun resetAll(newTasks: List<Task>) {
        taskList.clear()
        taskList.addAll(newTasks)
        notifyDataSetChanged()
    }

    fun addTask(newTask: Task?) {
        if (newTask != null) {
            taskList.add(0, newTask)
            notifyItemInserted(0)
        }
    }

    fun removeTask(taskToRemove: Task) {
        val position = taskList.indexOf(taskToRemove)
        taskList.remove(taskToRemove)
        notifyItemRemoved(position)
    }

    fun removeAllTask() {
        taskList.clear()
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var TextView.strikeThrough
            get() = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG > 0
            set(value){
                paintFlags = if (value)
                    paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                else
                    paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()}

        fun bind(task: Task) {
            var check: CheckBox = itemView.cb_item

            itemView.taskItemTextView.text = task.content
            itemView.taskItemTextView.strikeThrough = task.completed


            itemView.btn_delete.setOnClickListener {
                    onClickDelete(task)
            }


            check.setOnCheckedChangeListener { compoundButton, isChecked ->

                if (isChecked) {
                    onClickCloseTask(task)
                } else {
                    onClickOpenTask(task)
                }

                itemView.taskItemTextView.strikeThrough = isChecked

            }

        }

    }
}
