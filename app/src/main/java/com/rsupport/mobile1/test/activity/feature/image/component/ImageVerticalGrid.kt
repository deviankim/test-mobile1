package com.rsupport.mobile1.test.activity.feature.image.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ImageVerticalGrid(
    modifier: Modifier = Modifier,
    imageListState: LazyGridState = rememberLazyGridState(),
    gridCellCount: Int = 3,
    imageUrlList: List<String>,
    contentPadding: PaddingValues = PaddingValues(vertical = 8.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(1.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(1.dp),
) {

    LazyVerticalGrid(
        modifier = modifier,
        state = imageListState,
        contentPadding = contentPadding,
        columns = GridCells.Fixed(gridCellCount),
        verticalArrangement = verticalArrangement,
        horizontalArrangement = horizontalArrangement,
    ) {
        items(
            items = imageUrlList,
        ) { imageUrl ->
            AsyncImage(
                modifier = Modifier
                    .aspectRatio(1f)
                    .background(Color.LightGray),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "Image",
            )
        }
    }

}