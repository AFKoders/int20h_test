package com.afkoders.musicakinator.presentation.found_song

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.utils.extensions.makeGone
import com.afkoders.musicakinator.utils.extensions.makeVisible
import kotlinx.android.synthetic.main.fragment_found_song.*
import timber.log.Timber


/**
 * Created by Kalevych Oleksandr on 2020-01-22.
 */

class FoundSongFragment : BaseFragment<FoundSongViewModel>(R.layout.fragment_found_song) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("lyrics").let {
            loadingBackgorund.makeVisible()
            viewModel.searchArtist()
                .subscribe { result
                             , err ->
                    loadingBackgorund.makeGone()
                    Timber.d("FUCK. result: $result")
                }
        }
    }

    private fun setupLoadingView() {

    }

    override fun provideViewModel(): FoundSongViewModel =
        ViewModelProviders.of(requireActivity(), viewModelFactory)[FoundSongViewModel::class.java]

}