package com.example.tasksync

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DashboardBoardAdapter(
    private val boards: List<Board>,
    private val onItemClick: (Board) -> Unit
) : RecyclerView.Adapter<DashboardBoardAdapter.BoardViewHolder>() {

    class BoardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.boardTitle)
        val background: View = view.findViewById(R.id.boardBackground)
        val icon: android.widget.ImageView = view.findViewById(R.id.boardIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_board_recent, parent, false)
        return BoardViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        val board = boards[position]
        holder.title.text = board.title
        holder.icon.setImageResource(board.iconRes)
        try {
            holder.background.setBackgroundColor(Color.parseColor(board.color))
        } catch (e: Exception) {}
        
        holder.itemView.setOnClickListener { onItemClick(board) }
    }

    override fun getItemCount() = boards.size
}
