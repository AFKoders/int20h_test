package com.afkoders.musicakinator.presentation.search

import android.Manifest.permission.RECORD_AUDIO
import android.content.pm.PackageManager
import android.view.MotionEvent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.BaseFragment
import com.afkoders.musicakinator.utils.extensions.widget.*
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : BaseFragment<SearchViewModel>(R.layout.fragment_search) {

    override fun setupInputs() {
        typeSongEditText.addSearchWatcher {
            findNavController().navigate(SearchFragmentDirections.navigateToLoading(typeSongEditText.text.toString()))
        }

        ivClearInput.bindClick {
            typeSongEditText.erase()
        }

        ivVoiceInput.setOnTouchListener { _, motionEvent ->
            checkPermission()

            when (motionEvent.action) {
                MotionEvent.ACTION_UP -> {
                    viewModel.stopListening()
                    typeSongEditText.hint = getString(R.string.placeholder_whos_singing)
                }
                MotionEvent.ACTION_DOWN -> {
                    viewModel.startListening(recognitionCallback)
                    typeSongEditText.erase()
                    typeSongEditText.hint = getString(R.string.placeholder_input_listening)
                }
            }
            return@setOnTouchListener true
        }

        typeSongEditText.addTextChangedListener {
            if (typeSongEditText.text.toString().isBlank()) {
                ivClearInput.makeGone()
                ivVoiceInput.makeVisible()
            } else {
                ivClearInput.makeVisible()
                ivVoiceInput.makeGone()
            }
        }

        flHistory.bindClick {
            typeSongEditText.hideKeyboard()
            findNavController().navigate(SearchFragmentDirections.navigateToHistory())
        }

        typeSongEditText.addSearchWatcher {
            typeSongEditText.hideKeyboard()
            findNavController().navigate(
                SearchFragmentDirections.navigateToLoading(typeSongEditText.text.toString())
            )
            ivVoiceInput.requestFocus()
            typeSongEditText.erase()
        }
    }

    private val recognitionCallback: (text: String) -> Unit = {
        typeSongEditText.apply {
            setText(it)
            requestFocus()
            setSelection(typeSongEditText.text.toString().length)
            showKeyboard()
        }
    }

    private fun checkPermission() {
        val permission = ContextCompat.checkSelfPermission(
            requireContext(),
            RECORD_AUDIO
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
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
        const val RECORD_REQUEST_CODE = 393
    }

    override fun setupOutputs() {
        // Empty
    }
}