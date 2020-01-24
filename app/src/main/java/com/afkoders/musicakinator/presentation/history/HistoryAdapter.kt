package com.afkoders.musicakinator.presentation.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.data.prefs.HistoryModel
import kotlinx.android.synthetic.main.history_item.view.*

/**
 * Created by Kalevych Oleksandr on 2020-01-23.
 */

class HistoryAdapter(val items : List<HistoryModel>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.history_item, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName?.text = items.get(position).name
        holder.tvAuthor?.text = items.get(position).artist
        holder.tvTime?.text = items.get(position).time
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvName = view.tvName
    val tvAuthor = view.tvAuthor
    val tvTime = view.tvTime

}