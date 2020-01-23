package com.afkoders.musicakinator.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.utils.extensions.addSearchWatcher
import com.afkoders.musicakinator.utils.extensions.navigateTo
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * Created by Kalevych Oleksandr on 2020-01-22.
 */

class SearchFragment : BaseFragment<SearchViewModel>(R.layout.fragment_search) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        typeSongEditText.addSearchWatcher {
            viewModel.searchSongs(typeSongEditText.text.toString())
                .subscribe { data, err ->
                    Log.e("FUCK", "data: $data")
                    navigateTo(R.id.navigateToFoundSong) {
//                        putString(LYRICS_ARGUMENT, typeSongEditText.text.toString())
                    }
                }
        }
    }

    override fun provideViewModel(): SearchViewModel =
        ViewModelProviders.of(requireActivity(), viewModelFactory)[SearchViewModel::class.java]

    companion object {
        const val LYRICS_ARGUMENT = "ARGUMENT.lyrics"
    }

}