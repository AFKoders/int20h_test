package com.afkoders.musicakinator.utils.prefs

import android.content.Context
import com.google.gson.Gson
import org.json.JSONArray


/**
 * Created by Kalevych Oleksandr on 2020-01-23.
 */


class HistoryPrefs constructor(
    private val context: Context,
    val gson: Gson
) {

    companion object {
        private const val HISTORY_PREFS = "history_prefs"
        private const val HISTORY_LIST = "history_list"
    }

    private val prefs =
        context.applicationContext.getSharedPreferences(HISTORY_PREFS, Context.MODE_PRIVATE)

    fun addToHistory(track: PrefsModel){
        val newList = history as ArrayList
        newList.add(track)
        history = newList
    }

    var history: List<PrefsModel>
        set(value) = prefs.edit().putString(HISTORY_LIST, gson.toJson(value)).apply()
        get() = prefs.getString(HISTORY_LIST, "")?.let {
            val array = JSONArray(it)
            (0 until array.length()).map {
                array.getJSONObject(it).let {
                    PrefsModel(
                        it.getString("name"),
                        it.getString("artist"),
                        it.getString("time")
                    )
                }
            }
        } ?: listOf()

     fun clear() {
        prefs.edit().clear().apply()
    }
}