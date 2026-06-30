package com.example.tasksync

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ColumnAdapter(private val columns: List<TaskColumn>) : RecyclerView.Adapter<ColumnAdapter.ColumnViewHolder>() {

    class ColumnViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.columnTitle)
        val statusDot: View = view.findViewById(R.id.columnStatusDot)
        val rvCards: RecyclerView = view.findViewById(R.id.rvCards)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColumnViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_column, parent, false)
        return ColumnViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColumnViewHolder, position: Int) {
        val column = columns[position]
        holder.title.text = column.title
        
        try {
            holder.statusDot.setBackgroundColor(Color.parseColor(column.statusColor))
        } catch (e: Exception) {}

        holder.rvCards.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.rvCards.adapter = CardAdapter(column.cards)
    }

    override fun getItemCount() = columns.size
}
