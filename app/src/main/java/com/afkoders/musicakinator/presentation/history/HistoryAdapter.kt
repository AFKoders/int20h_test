package com.afkoders.musicakinator.presentation.history

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.RecyclerView
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.data.prefs.History
import com.afkoders.musicakinator.utils.extensions.dpToPx
import com.afkoders.musicakinator.utils.extensions.startIntentOrShowAlert
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.history_item.view.*

/**
 * Created by Kalevych Oleksandr on 2020-01-23.
 */

class HistoryAdapter(
    private val items: ArrayList<History>,
    private val context: Context
) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    fun clear(){
        items.clear()
        notifyDataSetChanged()
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
        if (position == 0) holder.addExtraMargin()

        val trackInfo = items[position]

        holder.view.tag = trackInfo.openInDeezerLink
        holder.tvName?.text = trackInfo.trackName
        holder.tvAuthor?.text = trackInfo.artistName
        holder.tvTime?.text = trackInfo.time
        Glide.with(context).load(trackInfo.trackImage).into(holder.ivCover)
    }
}

class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val tvName = view.tvTrackName
    val tvAuthor = view.tvTrackAuthor
    val tvTime = view.tvTime
    val ivCover = view.ivMusicCover
    val ctaOpenInDeezer = view.ctaOpenInDeezer

    init {
        ctaOpenInDeezer.setOnClickListener {
            it.context.startIntentOrShowAlert(
                Intent(Intent.ACTION_VIEW, Uri.parse(view.tag as String)),
                it.context.getString(R.string.error_no_browser_app)
            )
        }
    }

    fun addExtraMargin() {
        view.updatePadding(top = 68.dpToPx(view.context))
    }
}