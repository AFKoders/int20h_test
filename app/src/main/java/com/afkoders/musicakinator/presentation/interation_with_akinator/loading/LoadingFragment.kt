package com.afkoders.musicakinator.presentation.interation_with_akinator.loading

import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.presentation.interation_with_akinator.InteractionViewModel
import com.afkoders.musicakinator.utils.extensions.finish

class LoadingFragment : BaseFragment<InteractionViewModel>(R.layout.fragment_loading) {

    val args: LoadingFragmentArgs by navArgs()

    override fun provideViewModel() =
        ViewModelProviders.of(requireActivity(), viewModelFactory)[InteractionViewModel::class.java]

    override fun setupInputs() {
        // Empty
    }

    override fun setupOutputs() {
        args.lyrics?.let {
            viewModel.searchSongs(it)
                .subscribe { intermixes, err ->
                    viewModel.reset()
                    viewModel.putItems(intermixes)

                    findNavController().navigate(
                        LoadingFragmentDirections.actionFragmentLoadingToFoundSongFragment()
                    )
                }.disposeByBagProvider()
        } ?: goToSearch()
    }

    private fun goToSearch() {
        finish(R.id.fragmentSearch, false)
    }

}