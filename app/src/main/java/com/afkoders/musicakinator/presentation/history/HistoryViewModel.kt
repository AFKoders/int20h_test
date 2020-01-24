package com.afkoders.musicakinator.presentation.history

import androidx.lifecycle.ViewModel
import com.afkoders.musicakinator.data.prefs.HistoryModel
import com.afkoders.musicakinator.data.prefs.HistoryPrefs
import com.afkoders.musicakinator.di.qualifiers.SchedulerIO
import com.afkoders.musicakinator.di.qualifiers.SchedulerUI
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject


class HistoryViewModel @Inject constructor(
    private val historyPrefs: HistoryPrefs,
    @SchedulerUI private val schedulerUI: Scheduler,
    @SchedulerIO private val schedulerIO: Scheduler
) : ViewModel() {

    fun addDataToHistory(data: List<HistoryModel>) {
        historyPrefs.history = data
    }

    fun getHistoryPreferences(): Single<List<HistoryModel>> =
        Single.just(historyPrefs.history)
            .subscribeOn(schedulerIO)
            .observeOn(schedulerUI)
}