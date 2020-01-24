package com.afkoders.musicakinator.presentation.search

import android.Manifest.permission.RECORD_AUDIO
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.util.Log
import android.view.MotionEvent
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

/**
 * Created by Kalevych Oleksandr on 2020-01-22.
 */

class SearchFragment : BaseFragment<SearchViewModel>(R.layout.fragment_search) {

    override fun setupInputs() {

        typeSongEditText.addSearchWatcher {
            viewModel.searchSongs(typeSongEditText.text.toString())
                .subscribe { data, err ->
                    Log.e("FUCK", "data: $data")
                    navigateTo(R.id.navigateToFoundSong) {
                        //                        putString(LYRICS_ARGUMENT, typeSongEditText.text.toString())
                    }
                }
        }

        typeSongEditText.addTextChangedListener {
            if (typeSongEditText.text.toString().isEmpty()) {
                setupVoiceButton()
            } else {
                setupClearButton()
            }
        }

        viewModel.setupCallback {
            typeSongEditText.setText(it)
            typeSongEditText.requestFocus()
            typeSongEditText.setSelection(typeSongEditText.text.toString().length)
            typeSongEditText.showKeyboard()
        }

        ivHistory.setOnClickListener {
            navigateTo(R.id.navigateToHistory)
        }
        ivVoiceInput.requestFocus()
        setupVoiceButton()
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

                when (motionEvent.action) {
                    MotionEvent.ACTION_UP -> {
                        viewModel.stopListening()
                        typeSongEditText.hint = getString(R.string.placeholder_whos_singing)
                    }
                    MotionEvent.ACTION_DOWN -> {
                        viewModel.startListening()
                        typeSongEditText.setText("")
                        typeSongEditText.hint = "Listening..."
                    }
                }
                return@setOnTouchListener true
            }
        }
    }

    private fun checkPermission() {
        val permission = ContextCompat.checkSelfPermission(
            requireContext(),
            RECORD_AUDIO
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission to record denied")
            makeRequestForPermission()
        }
    }

    private fun makeRequestForPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(RECORD_AUDIO),
            RECORD_REQUEST_CODE
        )
    }

    override fun provideViewModel(): SearchViewModel =
        ViewModelProviders.of(requireActivity(), viewModelFactory)[SearchViewModel::class.java]

    companion object {
        const val LYRICS_ARGUMENT = "ARGUMENT.lyrics"
        const val RECORD_REQUEST_CODE = 393
    }

    override fun setupOutputs() {
        // Empty
    }
}