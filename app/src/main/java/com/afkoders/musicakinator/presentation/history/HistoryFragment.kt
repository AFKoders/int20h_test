package com.afkoders.musicakinator.presentation.history

import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.utils.extensions.widget.makeVisibleOrGone
import kotlinx.android.synthetic.main.fragment_history.*

/**
 * Created by Kalevych Oleksandr on 2020-01-22.
 */

class HistoryFragment : BaseFragment<HistoryViewModel>(R.layout.fragment_history) {

    private lateinit var adapter: HistoryAdapter

    override fun setupInputs() {
        // Empty
    }

    override fun setupOutputs() {
        viewModel.getHistoryPreferences()
            .subscribe { history, _ ->
                adapter = HistoryAdapter(history, requireContext())
                rvHistory.adapter = adapter
                toggleEmptyState()
            }.disposeByBagProvider()

        flBack.setOnClickListener {
            findNavController().navigate(HistoryFragmentDirections.actionFragmentHistoryToFragmentSearch())
        }

        flCleanHistory.setOnClickListener {
            viewModel.clearHistory()
            adapter.clear()
            toggleEmptyState()
        }
    }

    private fun toggleEmptyState() {
        tvEmptyHistory.makeVisibleOrGone { adapter.itemCount == 0 }
    }

    override fun provideViewModel(): HistoryViewModel =
        ViewModelProviders.of(requireActivity(), viewModelFactory)[HistoryViewModel::class.java]

}