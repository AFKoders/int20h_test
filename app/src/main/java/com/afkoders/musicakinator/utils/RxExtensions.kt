package com.afkoders.musicakinator.utils

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit


const val DEBOUNCE_TIME = 500L

fun Observer<Any>.fire() {
    this.onNext(Notification.INSTANCE)
}

fun <T> Observable<T>.void(): Observable<Any> = this.map { Notification.INSTANCE }

fun <T> Single<T>.void(): Single<Any> = this.map { Notification.INSTANCE }

fun <T> Observable<T>.throttleFirst(): Observable<T> =
    throttleFirst(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)

fun <T> BehaviorSubject<T>.get(withFallback: T) = value ?: withFallback

fun Disposable.disposeBy(disposeBag: CompositeDisposable) =
    this.also { disposeBag.add(it) }

enum class Notification {
    INSTANCE
}