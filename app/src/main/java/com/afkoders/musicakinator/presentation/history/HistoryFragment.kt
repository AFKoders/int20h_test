package com.afkoders.musicakinator.presentation.history

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.data.prefs.HistoryPrefs
import com.afkoders.musicakinator.data.prefs.HistoryModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_history.*

/**
 * Created by Kalevych Oleksandr on 2020-01-22.
 */

class HistoryFragment : BaseFragment<HistoryViewModel>(R.layout.fragment_history) {

    override fun setupInputs() {
        // Empty
    }

    override fun setupOutputs() {
        viewModel.addDataToHistory(listOf(HistoryModel("test", "test", "1231231123123")))
        viewModel.getHistoryPreferences()
            .subscribe { history, err ->
                rvHistory.adapter = HistoryAdapter(history, requireContext())
            }.disposeByBagProvider()
    }

    override fun provideViewModel(): HistoryViewModel =
        ViewModelProviders.of(requireActivity(), viewModelFactory)[HistoryViewModel::class.java]

}