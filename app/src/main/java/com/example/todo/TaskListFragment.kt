package com.example.todo

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.todo.databinding.TaskListFragmentBinding
import com.example.todo.tasklist.TaskListViewModel


class TaskListFragment : Fragment() {
    private lateinit var binding: TaskListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TaskListFragmentBinding.inflate(inflater)
        binding.taskListViewModel = ViewModelProviders.of(this).get(TaskListViewModel::class.java)
        binding.recyclerView.adapter = binding.taskListViewModel?.adapter

        binding.fab.setOnClickListener { _ -> showAddTaskDialog() }

        binding.fabrefresh.setOnClickListener { _ ->
            refreshTasks()
        }

        binding.fabdelete.setOnClickListener { _ ->
            binding.taskListViewModel?.deleteAll()
        }

        refreshTasks()
        return binding.root
    }

    private fun showAddTaskDialog() {
        val dialog = AlertDialog.Builder(activity)
        dialog.setTitle("Add ToDo Item")
        val dialogView = layoutInflater.inflate(R.layout.dialog_add, null)
        val toDoName = dialogView.findViewById<EditText>(R.id.ev_todo)
        dialog.setView(dialogView)
        dialog.setPositiveButton("Add") { _, _ -> addTask(toDoName) }
        dialog.setNegativeButton("Cancel") { _, _ -> }
        dialog.show()
    }

    private fun addTask(toDoName: EditText) {
        if (toDoName.text.isNotEmpty()) {
            binding.taskListViewModel?.addTask(toDoName.text.toString())
            Toast.makeText(activity, "La tache a été bien ajoutée", Toast.LENGTH_SHORT).show()
        }
    }

    private fun refreshTasks() {
        binding.taskListViewModel?.refreshTasks()
    }
}
