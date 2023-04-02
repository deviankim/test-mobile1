package com.rsupport.mobile1.test.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentContainerView.id, GettyImageFragment.newInstance())
            .commitAllowingStateLoss()
    }
}