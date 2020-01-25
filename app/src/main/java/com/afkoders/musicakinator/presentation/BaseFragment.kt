package com.afkoders.musicakinator.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.afkoders.musicakinator.utils.extensions.disposeBy
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseFragment<T : ViewModel>(@LayoutRes val layoutRes: Int) : DaggerFragment() {
    protected lateinit var viewModel: T

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = provideViewModel()
        return inflater.inflate(layoutRes, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupInputs()
        setupOutputs()
    }

    fun Disposable.disposeByBagProvider() = disposeBy(compositeDisposable)

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    abstract fun provideViewModel(): T

    abstract fun setupInputs()

    abstract fun setupOutputs()
}