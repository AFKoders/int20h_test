package com.afkoders.musicakinator.presentation.search

import androidx.lifecycle.ViewModel
import com.afkoders.musicakinator.data.models.Song
import com.afkoders.musicakinator.data.repository.AuddRepository
import com.afkoders.musicakinator.di.qualifiers.SchedulerIO
import com.afkoders.musicakinator.di.qualifiers.SchedulerUI
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Kalevych Oleksandr on 2020-01-22.
 */

class SearchViewModel @Inject constructor(
) : ViewModel() {
}