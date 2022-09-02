package com.rsupport.mobile1.test.network

import com.rsupport.mobile1.test.data.GettyImage


sealed class Uistate {
    object Loading : Uistate()
    data class Success(val input: List<GettyImage>) : Uistate()
    data class Fail(val exception: Exception) : Uistate()
}
