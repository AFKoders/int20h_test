package com.afkoders.musicakinator.presentation.interation_with_akinator


sealed class Interaction {
    data class Success(val intermix: Intermix) : Interaction()
    data class Retry(val retriesCount: Int) : Interaction() {
        val shouldRetry get() = retriesCount > 0
    }

    object Failure : Interaction()
}