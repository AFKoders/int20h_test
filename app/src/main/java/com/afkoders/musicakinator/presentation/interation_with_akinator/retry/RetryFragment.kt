package com.afkoders.musicakinator.presentation.interation_with_akinator.retry

import androidx.lifecycle.ViewModelProviders
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.presentation.interation_with_akinator.Interaction
import com.afkoders.musicakinator.presentation.interation_with_akinator.InteractionViewModel
import com.afkoders.musicakinator.utils.extensions.finish
import kotlinx.android.synthetic.main.fragment_result_retry.*


class RetryFragment : BaseFragment<InteractionViewModel>(R.layout.fragment_result_retry) {

    override fun provideViewModel() =
        ViewModelProviders.of(requireActivity(), viewModelFactory)[InteractionViewModel::class.java]

    override fun setupInputs() {
        val route = viewModel.route(isAkinatorMadeRightGuess = false) as Interaction.Retry

        tvMessageResult.text = resources.getQuantityString(
            R.plurals.interaction_retry,
            route.retriesCount,
            route.retriesCount
        )

        ctaRetry.bindClick { finish() }
        ivCloseResults.bindClick { finish() }
    }

    override fun setupOutputs() {
        // Empty
    }

}