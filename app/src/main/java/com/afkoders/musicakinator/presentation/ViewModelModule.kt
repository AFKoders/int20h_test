package com.afkoders.musicakinator.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.afkoders.musicakinator.presentation.history.HistoryViewModel
import com.afkoders.musicakinator.presentation.interation_with_akinator.InteractionViewModel
import com.afkoders.musicakinator.presentation.search.SearchViewModel
import com.afkoders.musicakinator.utils.ViewModelFactory
import com.afkoders.musicakinator.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(InteractionViewModel::class)
    internal abstract fun interactionViewModel(viewModel: InteractionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun searchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    internal abstract fun historyViewModel(viewModel: HistoryViewModel): ViewModel
}