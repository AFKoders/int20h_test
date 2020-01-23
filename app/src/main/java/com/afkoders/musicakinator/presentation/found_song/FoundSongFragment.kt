package com.afkoders.musicakinator.presentation.found_song

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.utils.extensions.makeGone
import com.afkoders.musicakinator.utils.extensions.makeVisible
import com.deezer.sdk.model.PaginatedList
import com.deezer.sdk.model.Track
import com.deezer.sdk.network.request.event.JsonRequestListener
import kotlinx.android.synthetic.main.fragment_found_song.*
import timber.log.Timber
import java.lang.Exception


/**
 * Created by Kalevych Oleksandr on 2020-01-22.
 */

class FoundSongFragment : BaseFragment<FoundSongViewModel>(R.layout.fragment_found_song) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("lyrics").let {
            loadingBackgorund.makeVisible()
            viewModel.searchArtist(it?:"")
                .subscribe { result, err ->
                    viewModel.searchTrackByName(requireActivity().applicationContext, result.get(0).artist, result.get(0).title,
                        object : JsonRequestListener() {
                            override fun onResult(p0: Any?, p1: Any?) {
                                Log.d("DeezerLog", "result = "+ p0.toString())
                                val track = p0 as PaginatedList<Track>
                                Log.d("DeezerLog", "result title = "+ track[0].title)
                                loadingBackgorund.makeGone()
                                tvFoundedSongTitle.text = track[0].title
                            }

                            override fun onUnparsedResult(p0: String?, p1: Any?) {
                            }

                            override fun onException(p0: Exception?, p1: Any?) {
                            }
                        })
                    Timber.d("FUCK. result: $result")
                }
        }

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

    override fun provideViewModel(): FoundSongViewModel =
        ViewModelProviders.of(requireActivity(), viewModelFactory)[FoundSongViewModel::class.java]

}