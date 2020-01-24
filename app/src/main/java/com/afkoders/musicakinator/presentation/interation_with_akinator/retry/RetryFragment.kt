package com.afkoders.musicakinator.presentation.interation_with_akinator.retry

import androidx.lifecycle.ViewModelProviders
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.presentation.interation_with_akinator.InteractionViewModel


class RetryFragment : BaseFragment<InteractionViewModel>(R.layout.fragment_result_retry) {

    override fun provideViewModel() =
        ViewModelProviders.of(requireActivity())[InteractionViewModel::class.java]

    override fun setupInputs() {
        // Empty
    }

    override fun setupOutputs() {
        // Empty
    }

}