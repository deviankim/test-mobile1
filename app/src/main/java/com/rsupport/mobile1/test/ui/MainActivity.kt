package com.rsupport.mobile1.test.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import com.jiun.myapplication2.ui.theme.MobileTestTheme
import com.rsupport.mobile1.test.ui.screen.MainScreen
import com.rsupport.mobile1.test.ui.screen.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                MobileTestTheme {
                    MainScreen(mainViewModel = mainViewModel)
                }
            }
        }
    }
}