package com.rsupport.mobile1.test.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.ui.theme.RColor

@Composable
fun LoadingProgress(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RColor.BlackTransparent)
            .clickable(true){},
        contentAlignment = Alignment.Center,
    ) {
        Box {
            Image(
                modifier = Modifier.size(70.dp),
                painter = painterResource(id = R.drawable.rsupport_icon),
                contentScale = ContentScale.Crop,
                contentDescription = "rsupport_icon"
            )
        }
        CircularProgressIndicator(
            modifier = Modifier.size(100.dp),
            strokeWidth = 7.dp,
            color = RColor.Primary,
        )
    }
}

@Preview
@Composable
fun LoadingStatePreview(){
    LoadingProgress()
}