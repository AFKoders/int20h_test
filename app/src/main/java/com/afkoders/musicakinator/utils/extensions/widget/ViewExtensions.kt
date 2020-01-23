package com.afkoders.musicakinator.utils.extensions.widget

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.afkoders.musicakinator.utils.extensions.openActivityForResult


fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.getAppCompatActivity(): AppCompatActivity = this.context as AppCompatActivity

fun View.makeGone() {
    visibility = View.GONE
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

inline fun View.makeVisibleOrGone(isVisible: () -> Boolean = { false }) {
    if (isVisible.invoke()) makeVisible() else makeGone()
}

inline fun <reified T : Activity> View.openActivity(initializer: Intent.() -> Unit = {}) {
    val intent = Intent(context, T::class.java).apply { initializer() }
    context.startActivity(intent)
}

inline fun <reified T : Activity> View.openActivityForResult(
    requestCode: Int,
    options: Bundle? = null,
    block: Intent.() -> Unit = {}
) {
    getAppCompatActivity().openActivityForResult<T>(requestCode, options, block)
}