package com.afkoders.musicakinator.presentation.interation_with_akinator


interface IntermixModel {
    val openInDeezer: Any
    val trackId: Int
    val trackName: String
    val trackLyrics: String
    val trackImage: String
    val artistName: String
}

data class IntermixModelImpl(
    override val openInDeezer: Any,
    override val trackId: Int,
    override val trackName: String,
    override val trackLyrics: String,
    override val trackImage: String,
    override val artistName: String
) : IntermixModel