package com.afkoders.musicakinator.utils.speechRecognition

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import java.util.*

/**
 * Created by Kalevych Oleksandr on 2020-01-24.
 */


class SpeechRecognitionHelper constructor(context: Context) {

    val speechRecognizer: SpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
    var speechRecognizerIntent: Intent? = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).putExtra(
        RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
    ).putExtra(
        RecognizerIntent.EXTRA_LANGUAGE,
        Locale.getDefault()
    )

    fun setupRecognizingListener(block: (text: String) -> Unit) {
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(p0: Bundle?) {
                Log.d("SpeechRecognition", "ready to speech")
            }

            override fun onRmsChanged(p0: Float) {
                Log.d("SpeechRecognition", "rms")

            }

            override fun onBufferReceived(p0: ByteArray?) {
                Log.d("SpeechRecognition", "buffer")
            }

            override fun onPartialResults(p0: Bundle?) {
                Log.d("SpeechRecognition", "partial")
            }

            override fun onEvent(p0: Int, p1: Bundle?) {
                Log.d("SpeechRecognition", "onEvent")
            }

            override fun onBeginningOfSpeech() {
                Log.d("SpeechRecognition", "onBeginning")

            }

            override fun onEndOfSpeech() {
                Log.d("SpeechRecognition", "onEnd")

            }

            override fun onError(p0: Int) {
                Log.d("SpeechRecognition", "onError")

            }

            override fun onResults(p0: Bundle?) {
                val matches = p0?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                Log.d("SpeechRecognition", "result")

                if (matches != null) {
                    for (i in matches) {
                        Log.d("SpeechRecognition", "rec " + i)
                    }
                }
                //displaying the first match
                if (matches != null)
                    block.invoke(matches[0])
            }
        })
    }

    fun stopListening() {
        speechRecognizer.stopListening()
    }

    fun startListening() {
        speechRecognizer.startListening(speechRecognizerIntent)
    }
}