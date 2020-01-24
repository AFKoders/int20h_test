package com.afkoders.musicakinator.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.utils.extensions.addSearchWatcher
import com.afkoders.musicakinator.utils.extensions.navigateTo
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : BaseFragment<SearchViewModel>(R.layout.fragment_search) {

    override fun provideViewModel(): SearchViewModel =
        ViewModelProviders.of(requireActivity(), viewModelFactory)[SearchViewModel::class.java]

    companion object {
        const val LYRICS_ARGUMENT = "ARGUMENT.lyrics"
    }

    override fun setupInputs() {
        ivHistory.setOnClickListener {
            navigateTo(R.id.navigateToHistory)
        }

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

    override fun setupOutputs() {
        // Empty
    }

}