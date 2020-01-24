package com.afkoders.musicakinator.presentation.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.data.prefs.History
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.history_item.view.*

/**
 * Created by Kalevych Oleksandr on 2020-01-23.
 */

class HistoryAdapter(
    private val items: List<History>,
    private val context: Context
) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.history_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName?.text = items[position].trackName
        holder.tvAuthor?.text = items[position].artistName
        holder.tvTime?.text = items[position].time
        Glide.with(context).load(items[position].trackImage).into(holder.ivCover)
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvName = view.tvName
    val tvAuthor = view.tvAuthor
    val tvTime = view.tvTime
    val ivCover = view.ivMusicCover
}