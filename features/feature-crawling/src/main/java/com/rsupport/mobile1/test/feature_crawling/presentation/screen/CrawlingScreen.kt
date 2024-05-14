package com.rsupport.mobile1.test.feature_crawling.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.rsupport.mobile1.test.core.ImageLoadState
import com.rsupport.mobile1.test.ui_component.R

@Composable
fun CrawlingScreen(
    navHostController: NavHostController,
    imageLoadState: State<ImageLoadState>,
    onClick: (Int) -> Unit
) {
    var imageUrls: List<String> = emptyList()
    when (imageLoadState.value) {
        is ImageLoadState.Success -> {
            imageUrls = (imageLoadState.value as ImageLoadState.Success).images
        }

        is ImageLoadState.Empty -> {
            Text(text = stringResource(id = R.string.empty_image))
        }

        is ImageLoadState.Failure -> {
            Text(text = stringResource(id = R.string.fail_crawling_image) + " ${(imageLoadState.value as ImageLoadState.Failure).error.message}")
        }

        else -> {}
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                contentPadding = PaddingValues(4.dp)
            ) {
                items(imageUrls.size) { index ->
                    AsyncImage(
                        model = imageUrls[index],
                        contentDescription = R.string.loaded_image.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            PageInputField(onNumberChange = onClick)
        }
        if (imageLoadState.value is ImageLoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

}


@Composable
fun PageInputField(onNumberChange: (Int) -> Unit) {
    var number by remember { mutableStateOf(1) }

    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                if (number > 1) {
                    number -= 1
                    onNumberChange(number)
                }
            }
        ) {
            Text(text = stringResource(id = R.string.minus))
        }

        OutlinedTextField(
            value = number.toString(),
            onValueChange = {
                number = it.toIntOrNull() ?: number
                onNumberChange(number)
            },
            singleLine = true,
            textStyle = TextStyle(textAlign = TextAlign.Center),
            modifier = Modifier
                .width(100.dp)
                .padding(horizontal = 8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(
            onClick = {
                number += 1
                onNumberChange(number)
            }
        ) {
            Text(text = stringResource(id = R.string.plus))
        }
    }
}
