package com.rsupport.mobile1.test.ui.dialog

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getString
import com.rsupport.mobile1.test.R

/**
 * Created by Lee on 2024-08-23.
 * A custom dialog class that only handles the dismissal event.
 * Displays a title, a message, and a single "OK" button.
 * After the dialog is dismissed, a provided action is performed.
 */

class DismissDialog(
    context: Context,
    title: String,
    message: String,
    private val onDismiss: () -> Unit
) {
    private val dialog: AlertDialog = AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(getString(context, R.string.confirm_ok)) { _: DialogInterface, _: Int ->
        }.setOnDismissListener {
            onDismiss() // Call the dismiss action when the dialog is dismissed
        }
        .create()

    fun show() {
        dialog.show()
    }
}