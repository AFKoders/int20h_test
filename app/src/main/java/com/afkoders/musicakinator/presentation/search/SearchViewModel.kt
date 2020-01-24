package com.afkoders.musicakinator.presentation.search

import androidx.lifecycle.ViewModel
import com.afkoders.musicakinator.utils.speechRecognition.SpeechRecognitionHelper
import javax.inject.Inject


class SearchViewModel @Inject constructor(
    private val speechRecognitionHelper: SpeechRecognitionHelper
    ) : ViewModel() {

    fun stopListening() {
        speechRecognitionHelper.stopListening()
    }

    fun startListening(block: (text: String) -> Unit) {
        speechRecognitionHelper.setupRecognizingListener(block)
        speechRecognitionHelper.startListening()
    }
}