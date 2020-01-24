package com.afkoders.musicakinator.presentation.interation_with_akinator.success

import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.presentation.interation_with_akinator.InteractionViewModel
import kotlinx.android.synthetic.main.fragment_result_success.*


class SuccessFragment : BaseFragment<InteractionViewModel>(R.layout.fragment_result_success) {

    override fun provideViewModel() =
        ViewModelProviders.of(requireActivity(), viewModelFactory)[InteractionViewModel::class.java]

    override fun setupInputs() {
        ivCloseResults.setOnClickListener { backToSearch() }
        ctaBackToSearch.setOnClickListener { backToSearch() }
    }

    private fun backToSearch() {
        findNavController().navigate(SuccessFragmentDirections.actionSuccessFragmentToFragmentSearch())
    }

    override fun setupOutputs() {
        // Empty
    }
}