package com.gy.jetpack_node.ext

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.gy.jetpack_node.ViewModelFactory

fun <T : ViewModel> Fragment.obtainViewModel(viewmodelClass:Class<T>)
      = ViewModelProviders.of(this,activity?.application?.let { ViewModelFactory.getInstance(it) }).get(viewmodelClass)

fun Fragment.snackBarShow(view: View, str: String) {
    Snackbar.make(view, str, Snackbar.LENGTH_SHORT).show()
}

fun Fragment.clipTxt(txt: String) {
    val cm = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val mClipData = ClipData.newPlainText("Label", txt)
    cm.primaryClip = mClipData
}
