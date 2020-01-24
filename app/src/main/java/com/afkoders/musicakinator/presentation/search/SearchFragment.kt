package com.afkoders.musicakinator.presentation.search

import android.Manifest.permission.RECORD_AUDIO
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProviders
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.utils.extensions.addSearchWatcher
import com.afkoders.musicakinator.utils.extensions.navigateTo
import com.afkoders.musicakinator.utils.extensions.showKeyboard
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.*

/**
 * Created by Kalevych Oleksandr on 2020-01-22.
 */

class SearchFragment : BaseFragment<SearchViewModel>(R.layout.fragment_search) {

    var speechRecognizer: SpeechRecognizer? = null
    var speechRecognizerIntent: Intent? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        typeSongEditText.addSearchWatcher {
            viewModel.searchSongs(typeSongEditText.text.toString())
                .subscribe { data, err ->
                    Log.e("FUCK", "data: $data")
                    navigateTo(R.id.navigateToFoundSong) {
                        //                        putString(LYRICS_ARGUMENT, typeSongEditText.text.toString())
                    }
                }
        }

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext())

        speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent?.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        speechRecognizerIntent?.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE,
            Locale.getDefault()
        )

        setupSpeechRecognition()

        typeSongEditText.addTextChangedListener {
            if (typeSongEditText.text.toString().isEmpty()) {
                setupVoiceButton()
            } else {
                setupClearButton()
            }
        }

        ivHistory.setOnClickListener {
            navigateTo(R.id.navigateToHistory)
        }
        ivVoiceInput.requestFocus()
        setupVoiceButton()
    }

    private fun setupSpeechRecognition() {
        speechRecognizer?.setRecognitionListener(object : RecognitionListener {
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
                    typeSongEditText.setText(matches[0])
                typeSongEditText.requestFocus()
                typeSongEditText.setSelection(typeSongEditText.text.toString().length)
                typeSongEditText.showKeyboard()
            }
        })
    }

    private fun setupClearButton() {
        ivVoiceInput.apply {
            setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_close_gray, null))
            setOnClickListener {
                typeSongEditText.setText("")
            }
        }
    }

    private fun setupVoiceButton() {
        ivVoiceInput.apply {
            setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_micro, null))
            setOnTouchListener { view, motionEvent ->
                checkPermission()
                setupSpeechRecognition()
                when (motionEvent.action) {
                    MotionEvent.ACTION_UP -> {
                        speechRecognizer?.stopListening()
                        typeSongEditText.hint = getString(R.string.placeholder_whos_singing)
                    }
                    MotionEvent.ACTION_DOWN -> {
                        speechRecognizer?.startListening(speechRecognizerIntent)
                        typeSongEditText.setText("")
                        typeSongEditText.hint = "Listening..."
                    }
                }
                return@setOnTouchListener true
            }
        }
    }

    private fun checkPermission() {
        val permission = ContextCompat.checkSelfPermission(requireContext(),
            RECORD_AUDIO)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission to record denied")
            makeRequestForPermission()
        }
    }

    private fun makeRequestForPermission() {
        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(RECORD_AUDIO),
            RECORD_REQUEST_CODE)
    }

    override fun provideViewModel(): SearchViewModel =
        ViewModelProviders.of(requireActivity(), viewModelFactory)[SearchViewModel::class.java]

    companion object {
        const val LYRICS_ARGUMENT = "ARGUMENT.lyrics"
        const val RECORD_REQUEST_CODE = 393
    }
}