package com.afkoders.musicakinator.utils.extensions

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit


fun Disposable.disposeBy(disposeBag: CompositeDisposable) =
    this.also { disposeBag.add(it) }

fun <T : Any> Observable<T>.throttleFirst(): Observable<T> = throttleFirst(THROTTLE_TIME, TimeUnit.MILLISECONDS)

const val THROTTLE_TIME = 500L
