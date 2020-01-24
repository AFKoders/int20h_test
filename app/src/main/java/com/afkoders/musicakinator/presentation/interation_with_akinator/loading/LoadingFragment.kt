package com.afkoders.musicakinator.presentation.interation_with_akinator.loading

import androidx.lifecycle.ViewModelProviders
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.presentation.interation_with_akinator.InteractionViewModel

class LoadingFragment : BaseFragment<InteractionViewModel>(R.layout.fragment_loading) {

    override fun provideViewModel() =
        ViewModelProviders.of(requireActivity(), viewModelFactory)[InteractionViewModel::class.java]

    override fun setupInputs() {
        // Empty
    }

    override fun setupOutputs() {
        // TODO get argument with lyrics

        viewModel.searchSongs("")
            .subscribe { intermixes, err ->
                // TODO for Yaroslav process somehow with router
            }.disposeByBagProvider()
    }

}