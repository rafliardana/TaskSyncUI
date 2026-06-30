package com.example.tasksync

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class CardAdapter(private val cards: List<TaskCard>) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tag: TextView = view.findViewById(R.id.cardTag)
        val progressIndicator: View = view.findViewById(R.id.cardProgressIndicator)
        val title: TextView = view.findViewById(R.id.cardTitle)
        val assigneeAvatar: android.widget.ImageView = view.findViewById(R.id.assigneeAvatar)
        val assigneeName: TextView = view.findViewById(R.id.assigneeName)
        val attachmentCount: TextView = view.findViewById(R.id.attachmentCount)
        val commentCount: TextView = view.findViewById(R.id.commentCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cards[position]
        holder.title.text = card.title
        holder.tag.text = card.tag ?: "Task"
        holder.assigneeName.text = card.assigneeName
        
        card.assigneeAvatarUrl?.let { url ->
            Glide.with(holder.itemView.context)
                .load(url)
                .transform(CircleCrop())
                .placeholder(android.R.drawable.sym_def_app_icon)
                .into(holder.assigneeAvatar)
        }

        try {
            val color = Color.parseColor(card.tagColor ?: "#2196F3")
            holder.tag.setTextColor(color)
            holder.tag.setBackgroundColor(adjustAlpha(color, 0.1f))
            holder.progressIndicator.setBackgroundColor(color)
        } catch (e: Exception) {
            // Fallback
        }

        holder.attachmentCount.text = card.attachmentCount.toString()
        holder.commentCount.text = card.commentCount.toString()
    }

    private fun adjustAlpha(color: Int, factor: Float): Int {
        val alpha = Math.round(Color.alpha(color) * factor)
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return Color.argb(alpha, red, green, blue)
    }

    override fun getItemCount() = cards.size
}
