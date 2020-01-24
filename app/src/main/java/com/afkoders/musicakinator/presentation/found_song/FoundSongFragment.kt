package com.afkoders.musicakinator.presentation.found_song

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.presentation.interation_with_akinator.Interaction
import com.afkoders.musicakinator.presentation.interation_with_akinator.InteractionViewModel
import kotlinx.android.synthetic.main.fragment_found_song.*


/**
 * Created by Kalevych Oleksandr on 2020-01-22.
 */

class FoundSongFragment : BaseFragment<InteractionViewModel>(R.layout.fragment_found_song) {

    private val states = arrayOf(
        intArrayOf(-android.R.attr.state_checked),
        intArrayOf(android.R.attr.state_checked)
    )

    private val colors by lazy {
        intArrayOf(
            ContextCompat.getColor(requireActivity(), android.R.color.transparent),
            ContextCompat.getColor(requireActivity(), R.color.ctaPrimaryBackgroundColor)
        )
    }

    override fun provideViewModel() =
        ViewModelProviders.of(requireActivity(), viewModelFactory)[InteractionViewModel::class.java]

    override fun setupInputs() {
        btnYes.setOnClickListener {
            viewModel.route(isAkinatorMadeRightGuess = true)
            findNavController().navigate(FoundSongFragmentDirections.actionFragmentFoundSongToSuccessFragment())
        }

        btnNo.setOnClickListener {
            viewModel.update()
            when (viewModel.route(isAkinatorMadeRightGuess = false)) {
                is Interaction.Retry -> {
                    findNavController().navigate(FoundSongFragmentDirections.actionFragmentFoundSongToRetryFragment())
                }
                is Interaction.Failure -> {
                    findNavController().navigate(FoundSongFragmentDirections.actionFragmentFoundSongToFailureFragment())
                }
            }
        }

        btnSong.chipBackgroundColor = ColorStateList(states, colors)
        btnLyrics.chipBackgroundColor = ColorStateList(states, colors)
    }

    override fun setupOutputs() {
        // Empty
    }
}