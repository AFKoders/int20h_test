package com.afkoders.musicakinator.data.prefs

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * Created by Kalevych Oleksandr on 2020-01-23.
 */


class HistoryPrefs constructor(context: Context, private val gson: Gson) {

    companion object {
        private const val HISTORY_PREFS = "history_prefs"
        private const val HISTORY_LIST = "history_list"
    }

    private val prefs =
        context.getSharedPreferences(HISTORY_PREFS, Context.MODE_PRIVATE)

    fun addToHistory(track: History) {
        val newList = history
        newList.add(track)
        history = newList
    }

    var history: MutableList<History>
        set(value) = prefs.edit().putString(HISTORY_LIST, gson.toJson(value)).apply()
        get() = gson.fromJson(
            prefs.getString(HISTORY_LIST, ""),
            object : TypeToken<List<History>>() {}.type
        ) ?: mutableListOf()

    fun clear() {
        prefs.edit().clear().apply()
    }
}