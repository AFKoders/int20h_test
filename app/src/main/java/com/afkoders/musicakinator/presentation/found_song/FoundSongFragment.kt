package com.afkoders.musicakinator.presentation.found_song

import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment

class FoundSongFragment : BaseFragment<FoundSongViewModel>(R.layout.fragment_found_song) {

    override fun provideViewModel(): FoundSongViewModel =
        ViewModelProviders.of(requireActivity(), viewModelFactory)[FoundSongViewModel::class.java]

    button1.setOnClickListener {
        findNavController()
            .navigate(FoundSongFragmentDirections.actionFragmentFoundSongToSuccessFragment())
    }

    button2.setOnClickListener {
        findNavController()
            .navigate(FoundSongFragmentDirections.actionFragmentFoundSongToRetryFragment())
    }

    button3.setOnClickListener {
        findNavController()
            .navigate(FoundSongFragmentDirections.actionFragmentFoundSongToFailureFragment())
    }
}