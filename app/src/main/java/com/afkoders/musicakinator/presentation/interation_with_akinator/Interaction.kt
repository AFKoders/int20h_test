package com.afkoders.musicakinator.presentation.interation_with_akinator


sealed class Interaction {
    data class Success(val intermixModel: IntermixModel) : Interaction()
    data class Retry(val retriesCount: Int) : Interaction()
    object Failure : Interaction()
}