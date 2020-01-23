package com.afkoders.musicakinator.presentation.found_song

import androidx.lifecycle.ViewModel
import com.afkoders.musicakinator.di.qualifiers.SchedulerIO
import com.afkoders.musicakinator.di.qualifiers.SchedulerUI
import io.reactivex.Scheduler
import javax.inject.Inject

class FoundSongViewModel @Inject constructor(
    @SchedulerUI private val schedulerUI: Scheduler,
    @SchedulerIO private val schedulerIO: Scheduler
) : ViewModel()