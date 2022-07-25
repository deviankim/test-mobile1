package com.rsupport.mobile1.test.activity.common

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.rsupport.mobile1.test.R

class ProgressDialog(context: Context) : AppCompatDialog(context, R.style.DialogStyle_progress) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_progress)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }
}