package com.example.myfirsttodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())
        rViewTodoItems.adapter = todoAdapter
        rViewTodoItems.layoutManager = LinearLayoutManager(this)
        btnAddTodoItems.setOnClickListener {
            val todoTitle = eTodoItems.text.toString()
            if (todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addToDo(todo)
                eTodoItems.text.clear()
            }
        }
        btnDeleteTodoItems.setOnClickListener {
            todoAdapter.deleteToDos()
        }
    }
}