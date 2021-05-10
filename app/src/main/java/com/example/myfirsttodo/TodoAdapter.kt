package com.example.myfirsttodo

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(
    private val todos : MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    fun addToDo(todo:Todo){
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }
    fun deleteToDos() {
        todos.removeIf { todo -> todo.isChecked }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    private fun toggleStrikeThrough(textViewTodoTitle : TextView , isChecked : Boolean){
        if (isChecked) {
            textViewTodoTitle.paintFlags = textViewTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            textViewTodoTitle.paintFlags = textViewTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()

        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
            val curTodo = todos[position]
            holder.itemView.apply {
                textViewTodo.text = curTodo.title
                cbDone.isChecked = curTodo.isChecked
                toggleStrikeThrough(textViewTodo , curTodo.isChecked )
                cbDone.setOnCheckedChangeListener { _, isChecked ->
                    toggleStrikeThrough(textViewTodo , curTodo.isChecked )
                        curTodo.isChecked = !curTodo.isChecked
                }
            }

    }

    override fun getItemCount(): Int {
            return todos.size
    }
}