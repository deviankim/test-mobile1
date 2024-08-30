package com.rsupport.mobile1.test.ui.screen

data class MainState(
    val isLoading: Boolean = false,
    val urls: List<String> = listOf(),
    val errorMessage: String = ""
)
