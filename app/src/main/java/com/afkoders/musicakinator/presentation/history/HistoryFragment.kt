package com.afkoders.musicakinator.presentation.history

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.utils.extensions.addSearchWatcher
import com.afkoders.musicakinator.utils.prefs.HistoryPrefs
import com.afkoders.musicakinator.utils.prefs.PrefsModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * Created by Kalevych Oleksandr on 2020-01-22.
 */

 class HistoryFragment : BaseFragment<HistoryViewModel>(R.layout.fragment_history) {

 private var prefs : HistoryPrefs? = null

 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
  super.onViewCreated(view, savedInstanceState)


  prefs = HistoryPrefs(requireContext(), Gson())
  prefs?.addToHistory(PrefsModel("some track", "some artist", "some time"))
  rvHistory.adapter = HistoryAdapter(prefs?.history?: arrayListOf(), requireContext())

 }

 override fun provideViewModel(): HistoryViewModel =
  ViewModelProviders.of(requireActivity(), viewModelFactory)[HistoryViewModel::class.java]

}