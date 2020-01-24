package com.afkoders.musicakinator.presentation.interation_with_akinator.failure

import androidx.lifecycle.ViewModelProviders
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.presentation.interation_with_akinator.InteractionViewModel
import com.afkoders.musicakinator.utils.extensions.finish
import kotlinx.android.synthetic.main.fragment_result_failure.*


class FailureFragment : BaseFragment<InteractionViewModel>(R.layout.fragment_result_failure) {

    override fun provideViewModel() =
        ViewModelProviders.of(requireActivity(), viewModelFactory)[InteractionViewModel::class.java]

    override fun setupInputs() {
        ivCloseResults.setOnClickListener { finish(R.id.fragmentSearch) }
        ctaBackToSearch.setOnClickListener { finish(R.id.fragmentSearch) }
    }

    override fun setupOutputs() {
        // Empty
    }

}