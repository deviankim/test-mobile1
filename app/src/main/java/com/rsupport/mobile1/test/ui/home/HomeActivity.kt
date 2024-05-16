package com.rsupport.mobile1.test.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rsupport.mobile1.test.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.apply {
            replace(R.id.fragmentContainer, HomeFragment())
            commit()
        }
    }
}