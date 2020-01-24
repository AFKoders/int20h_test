package com.afkoders.musicakinator.presentation.interation_with_akinator.success

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.presentation.interation_with_akinator.InteractionViewModel
import com.bumptech.glide.Glide
import com.afkoders.musicakinator.utils.extensions.finish
import com.afkoders.musicakinator.utils.extensions.startIntentOrShowAlert
import kotlinx.android.synthetic.main.fragment_result_success.*


class SuccessFragment : BaseFragment<InteractionViewModel>(R.layout.fragment_result_success) {

    override fun provideViewModel() =
        ViewModelProviders.of(requireActivity(), viewModelFactory)[InteractionViewModel::class.java]

    override fun setupInputs() {
        ivCloseResults.setOnClickListener { finish(R.id.fragmentSearch) }
        ctaBackToSearch.setOnClickListener { finish(R.id.fragmentSearch) }

        val trackInfo = viewModel.getTrackByAttempt()


        ctaOpenInDeezer.setOnClickListener {
            requireActivity().startIntentOrShowAlert(
                Intent(Intent.ACTION_VIEW, Uri.parse(trackInfo.openInDeezerLink)),
                getString(R.string.error_no_browser_app)
            )
        }

        Glide.with(requireActivity()).load(trackInfo.trackImage).into(ivMusicCover)
        tvTrackAuthor.text = trackInfo.artistName
        tvTrackName.text = trackInfo.trackName
    }

    override fun setupOutputs() {
        // Empty
    }
}