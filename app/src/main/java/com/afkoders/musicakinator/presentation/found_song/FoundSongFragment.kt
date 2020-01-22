package com.afkoders.musicakinator.presentation.found_song

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.afkoders.musicakinator.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_found_song.*
import timber.log.Timber


/**
 * Created by Kalevych Oleksandr on 2020-01-22.
 */

class FoundSongFragment : DaggerFragment() {
    private lateinit var viewModel: FoundSongViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(requireActivity()).get(FoundSongViewModel::class.java)
        button.setOnClickListener {
            viewModel.search()
                .subscribe { result, err ->
                    Timber.d("FUCK. result: $result")
                }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_found_song,null, false)
        return view
    }

}