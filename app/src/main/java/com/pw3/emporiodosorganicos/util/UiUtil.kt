package com.pw3.emporiodosorganicos.util

import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.pw3.emporiodosorganicos.R

fun showDialog(context: FragmentActivity, @StringRes title: Int, @StringRes message: Int, onPositiveClicked: () -> Unit) {
    val alertDialog: AlertDialog = context.let {
        val builder = AlertDialog.Builder(it)
        builder.apply {
            setTitle(title)
            setMessage(message)

            setPositiveButton(R.string.save) { _, _ ->
                onPositiveClicked()
            }

            setNegativeButton(R.string.cancel) { _, _ ->
                // User cancelled the dialog
            }
        }
        builder.create()
    }
    alertDialog.show()
}