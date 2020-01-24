package com.afkoders.musicakinator.presentation.interation_with_akinator.success

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.presentation.interation_with_akinator.InteractionViewModel
import com.afkoders.musicakinator.utils.extensions.finish
import com.afkoders.musicakinator.utils.extensions.startIntentOrShowAlert
import kotlinx.android.synthetic.main.fragment_result_success.*


class SuccessFragment : BaseFragment<InteractionViewModel>(R.layout.fragment_result_success) {

    override fun provideViewModel() =
        ViewModelProviders.of(requireActivity(), viewModelFactory)[InteractionViewModel::class.java]

    override fun setupInputs() {
        ivCloseResults.setOnClickListener { finish(R.id.fragmentSearch) }
        ctaBackToSearch.setOnClickListener { finish(R.id.fragmentSearch) }
        ctaOpenInDeezer.setOnClickListener {
            val deezerLink = viewModel.getTrackByAttempt().openInDeezerLink
            requireContext().startIntentOrShowAlert(
                Intent(Intent.ACTION_VIEW, Uri.parse(deezerLink)),
                getString(R.string.error_no_browser_app)
            )
        }
    }

    private fun backToSearch() {
        findNavController().navigate(SuccessFragmentDirections.actionSuccessFragmentToFragmentSearch())
    }

    override fun setupOutputs() {
        // Empty
    }
}