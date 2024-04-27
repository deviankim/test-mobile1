package com.rsupport.mobile1.test.activity.feature.image

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rsupport.mobile1.test.activity.feature.image.component.ImageVerticalGrid
import com.rsupport.mobile1.test.activity.core.designsystem.theme.Mobile1TestAppTheme
import com.rsupport.mobile1.test.activity.core.ui.OnBottomReached

@Composable
fun ImageRoute(
  viewModel: ImageViewModel = viewModel(),
) {
  val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

  val photoListState = rememberLazyGridState()

  photoListState.OnBottomReached {
    viewModel.getNextPage()
  }

  ImageScreen(
    uiState = uiState,
    imageListState = photoListState,
  )
}

@Composable
fun ImageScreen(
  uiState: ImageUiState = ImageUiState(),
  imageListState: LazyGridState = rememberLazyGridState(),
) {
    ImageVerticalGrid(
      modifier = Modifier.fillMaxSize(),
      imageListState = imageListState,
      imageUrlList = uiState.imageUrlList,
    )
}

@Preview(showBackground = true)
@Composable
fun ImageScreenPreview() {
  Mobile1TestAppTheme {
    ImageScreen()
  }
}
