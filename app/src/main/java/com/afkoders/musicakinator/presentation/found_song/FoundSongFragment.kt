package com.afkoders.musicakinator.presentation.found_song

import android.content.res.ColorStateList
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.presentation.interation_with_akinator.Interaction
import com.afkoders.musicakinator.presentation.interation_with_akinator.InteractionViewModel
import com.afkoders.musicakinator.presentation.interation_with_akinator.InteractionViewModel.Companion.APP_NAME
import com.afkoders.musicakinator.utils.extensions.finish
import com.afkoders.musicakinator.utils.extensions.widget.makeGone
import com.afkoders.musicakinator.utils.extensions.widget.makeVisible
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.fragment_found_song.*
import kotlinx.android.synthetic.main.fragment_result_success.*


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

    private val exoPlayer by lazy { SimpleExoPlayer.Builder(requireActivity()).build() }

    override fun provideViewModel() =
        ViewModelProviders.of(requireActivity(), viewModelFactory)[InteractionViewModel::class.java]

    override fun onStop() {
        super.onStop()
        exoPlayer.stop()
        exoPlayer.seekTo(0)
    }

    override fun setupInputs() {
        if (!viewModel.isTracksCompatible()) {
            openSearchScreen()
            return
        }

        val trackInfo = viewModel.getTrackByAttempt()

        flBack.bindClick { finish(R.id.fragmentSearch) }

        btnYes.bindClick {
            viewModel.route(isAkinatorMadeRightGuess = true)
            openSuccessScreen()
        }

        btnNo.bindClick {
            viewModel.update()
            when (val route = viewModel.route(isAkinatorMadeRightGuess = false)) {
                is Interaction.Retry -> if (route.shouldRetry) openRetryScreen() else openFailureScreen()
                is Interaction.Failure -> openFailureScreen()

            }
        }

        flBack.bindClick { openSearchScreen() }

        btnSong.chipBackgroundColor = ColorStateList(states, colors)
        btnLyrics.chipBackgroundColor = ColorStateList(states, colors)

        btnLyrics.bindClick {
            groupNotLyrics.makeGone()
            groupLyrics.makeVisible()
            flGradient.makeVisible()
        }

        btnSong.bindClick {
            groupNotLyrics.makeVisible()
            groupLyrics.makeGone()
            flGradient.makeGone()
        }

        trackInfo.apply {
            tvLyricsTitle.text = artistName
            tvLyricsSubtitle.text = trackName
            tvLyricsText.text = this.trackLyrics
        }

        playerView.apply {
            this.player = exoPlayer
            controllerHideOnTouch = false
            controllerShowTimeoutMs = 0
        }

        playSong(trackInfo.preview)

        tvTrackAuthor.text = trackInfo.artistName
        tvTrackName.text = trackInfo.trackName

        Glide.with(requireActivity()).load(trackInfo.trackImage).into(ivAlbumPhoto)
    }

    private fun playSong(source: String) {
        val audioSource = ProgressiveMediaSource.Factory(
            DefaultDataSourceFactory(
                context,
                Util.getUserAgent(requireContext(), APP_NAME)
            )
        )
            .createMediaSource(Uri.parse(source))
        exoPlayer.prepare(audioSource)
        exoPlayer.playWhenReady = false
    }

    private fun openFailureScreen() {
        findNavController().navigate(FoundSongFragmentDirections.actionFragmentFoundSongToFailureFragment())
    }

    private fun openSuccessScreen() {
        findNavController().navigate(FoundSongFragmentDirections.actionFragmentFoundSongToSuccessFragment())
    }

    private fun openRetryScreen() {
        findNavController().navigate(FoundSongFragmentDirections.actionFragmentFoundSongToRetryFragment())
    }

    private fun openSearchScreen() {
        exoPlayer.stop()
        finish(R.id.fragmentSearch)
    }

    override fun setupOutputs() {
        // Empty
    }
}