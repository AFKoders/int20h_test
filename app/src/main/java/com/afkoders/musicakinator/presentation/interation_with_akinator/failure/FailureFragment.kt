package com.afkoders.musicakinator.presentation.interation_with_akinator.failure

import androidx.lifecycle.ViewModelProviders
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.presentation.interation_with_akinator.InteractionViewModel


class FailureFragment : BaseFragment<InteractionViewModel>(R.layout.fragment_result_failure) {

    override fun provideViewModel() =
        ViewModelProviders.of(requireActivity())[InteractionViewModel::class.java]

    override fun setupInputs() {
        // Empty
    }

    override fun setupOutputs() {
        // Empty
    }

}