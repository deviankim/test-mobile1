package com.rsupport.mobile1.test.ui.dialog

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getString
import com.rsupport.mobile1.test.R

/**
 * Created by Lee on 2024-08-23.
 * A custom dialog class to confirm an action with the user.
 * Displays a title, a message, and two buttons: Confirm and Cancel.
 */

class ConfirmDialog(
    context: Context,
    title: String,
    message: String,
    private val onConfirm: () -> Unit
) {
    private val dialog: AlertDialog = AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(getString(context, R.string.confirm_ok)) { _: DialogInterface, _: Int ->
            onConfirm()
        }
        .setNegativeButton(getString(context, R.string.confirm_cancel), null)
        .create()

    fun show() {
        dialog.show()
    }
}