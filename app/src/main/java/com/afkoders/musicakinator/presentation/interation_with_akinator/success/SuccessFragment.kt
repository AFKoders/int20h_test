package com.afkoders.musicakinator.presentation.interation_with_akinator.success

import androidx.lifecycle.ViewModelProviders
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.presentation.interation_with_akinator.InteractionViewModel


class SuccessFragment : BaseFragment<InteractionViewModel>(R.layout.fragment_result_success) {

    override fun provideViewModel() =
        ViewModelProviders.of(requireActivity())[InteractionViewModel::class.java]

    override fun setupInputs() {
        // Empty
    }

    override fun setupOutputs() {
        // Empty
    }

}