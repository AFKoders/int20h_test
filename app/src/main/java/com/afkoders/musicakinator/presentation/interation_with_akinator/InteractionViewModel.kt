package com.afkoders.musicakinator.presentation.interation_with_akinator

import androidx.lifecycle.ViewModel
import javax.inject.Inject


class InteractionViewModel @Inject constructor() : ViewModel() {
    val trackData = mutableListOf<IntermixModel>()

    var attemptsCount: Int = MAX_ATTEMPTS_COUNT
        private set

    private var routeOnNextInteraction: Interaction = Interaction.Retry(normalizedId())

    private fun updateAttemptsCount(shouldContinueGuessing: Boolean) {
        attemptsCount = if (shouldContinueGuessing) attemptsCount.minus(1) else MAX_ATTEMPTS_COUNT
    }

    private fun updateRoute(shouldContinueGuessing: Boolean) {
        routeOnNextInteraction = if (shouldContinueGuessing) {
            Interaction.Retry(normalizedId())
        } else {
            Interaction.Failure
        }
    }

    private fun normalizedId() = MAX_ATTEMPTS_COUNT - attemptsCount

    fun update() {
        val shouldContinueGuessing = attemptsCount > 0

        updateAttemptsCount(shouldContinueGuessing)
        updateRoute(shouldContinueGuessing)
    }

    fun route(isAkinatorMadeRightGuess: Boolean): Interaction {
        return if (isAkinatorMadeRightGuess) {
            Interaction.Success(getTrackByAttempt())
        } else {
            routeOnNextInteraction
        }
    }

    fun getTrackByAttempt() = trackData[normalizedId()]

    fun reset() {
        updateAttemptsCount(false)
        updateRoute(true)
    }

    companion object {
        const val MAX_ATTEMPTS_COUNT = 5
    }
}