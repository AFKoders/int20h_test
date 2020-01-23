package com.afkoders.musicakinator.presentation.found_song

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_found_song.*
import timber.log.Timber


/**
 * Created by Kalevych Oleksandr on 2020-01-22.
 */

class FoundSongFragment : BaseFragment<FoundSongViewModel>(R.layout.fragment_found_song) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            viewModel.search()
                .subscribe { result, err ->
                    Timber.d("FUCK. result: $result")
                }
        }
    }

    override fun provideViewModel(): FoundSongViewModel =
        ViewModelProviders.of(requireActivity(), viewModelFactory)[FoundSongViewModel::class.java]

}