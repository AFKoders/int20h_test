package com.afkoders.musicakinator.presentation.search

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.utils.extensions.addSearchWatcher
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * Created by Kalevych Oleksandr on 2020-01-22.
 */

 class SearchFragment : BaseFragment<SearchViewModel>(R.layout.fragment_search) {

 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
  super.onViewCreated(view, savedInstanceState)

  typeSongEditText.addSearchWatcher {
      var bundle = bundleOf("lyrics" to typeSongEditText.text.toString())
      findNavController().navigate(R.id.navigateToFoundSong, bundle)
  }
 }

 override fun provideViewModel(): SearchViewModel =
  ViewModelProviders.of(requireActivity(), viewModelFactory)[SearchViewModel::class.java]

}