package com.afkoders.musicakinator.presentation.found_song

import androidx.lifecycle.ViewModelProviders
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment


/**
 * Created by Kalevych Oleksandr on 2020-01-22.
 */

class FoundSongFragment : BaseFragment<FoundSongViewModel>(R.layout.fragment_found_song) {

    override fun provideViewModel(): FoundSongViewModel =
        ViewModelProviders.of(requireActivity(), viewModelFactory)[FoundSongViewModel::class.java]

    override fun setupInputs() {
        // Empty
    }

    override fun setupOutputs() {
        // Empty
    }

}