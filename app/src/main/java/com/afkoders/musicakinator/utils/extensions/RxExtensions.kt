package com.afkoders.musicakinator.utils.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


fun Disposable.disposeBy(disposeBag: CompositeDisposable) =
    this.also { disposeBag.add(it) }