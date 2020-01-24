package com.afkoders.musicakinator.presentation.found_song

import android.content.res.ColorStateList
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
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

    override fun setupInputs() {
        if (!viewModel.isTracksCompatible()) {
            openSearchScreen()
            return
        }

        val trackInfo = viewModel.getTrackByAttempt()

        flBack.setOnClickListener { finish(R.id.fragmentSearch) }

        btnYes.setOnClickListener {
            viewModel.route(isAkinatorMadeRightGuess = true)
            findNavController().navigate(FoundSongFragmentDirections.actionFragmentFoundSongToSuccessFragment())
        }

        btnNo.setOnClickListener {
            viewModel.update()
            when (val route = viewModel.route(isAkinatorMadeRightGuess = false)) {
                is Interaction.Retry -> if (route.shouldRetry) openSuccessScreen() else openFailureScreen()
                is Interaction.Failure -> openFailureScreen()

            }
        }

        flBack.setOnClickListener { openSearchScreen() }

        btnSong.chipBackgroundColor = ColorStateList(states, colors)
        btnLyrics.chipBackgroundColor = ColorStateList(states, colors)

        btnLyrics.setOnClickListener {
            groupNotLyrics.makeGone()
            groupLyrics.makeVisible()
        }

        btnSong.setOnClickListener {
            groupNotLyrics.makeVisible()
            groupLyrics.makeGone()
        }

        trackInfo.apply {
            tvLyricsTitle.text = trackName
            tvLyricsSubtitle.text = artistName
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
        exoPlayer.playWhenReady
    }

    private fun openFailureScreen() {
        exoPlayer.release()
        findNavController().navigate(FoundSongFragmentDirections.actionFragmentFoundSongToFailureFragment())
    }

    private fun openSuccessScreen() {
        exoPlayer.release()
        findNavController().navigate(FoundSongFragmentDirections.actionFragmentFoundSongToRetryFragment())
    }

    private fun openSearchScreen() {
        exoPlayer.release()
        finish(R.id.fragmentSearch)
    }

    override fun setupOutputs() {
        // Empty
    }
}