package com.rsupport.mobile1.test.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.blue.domain.model.PhotoData
import com.rsupport.mobile1.test.state.UIState
import com.rsupport.mobile1.test.viewmodel.FavoriteViewModel
import com.rsupport.mobile1.test.viewmodel.HomeViewModel

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val uiState = viewModel.favoriteUIState.collectAsStateWithLifecycle()

    Log.e("TAG", "FavoriteScreen: $uiState", )

    FavoriteContentWithState(
        uiState = uiState.value,
        deleteFavorite = viewModel::deleteFavorite
    )
}

@Composable
fun FavoriteContentWithState(
    uiState: UIState,
    deleteFavorite: (Int) -> Unit
) {
    when (uiState) {
        is UIState.Success -> FavoriteContent(
            uiState = uiState,
            deleteFavorite = deleteFavorite
        )
        is UIState.Error -> {}
        is UIState.Loading -> {}
    }
}

@Composable
fun FavoriteContent(
    uiState: UIState.Success,
    deleteFavorite: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        contentPadding = PaddingValues(start = 30.dp, end = 30.dp, top = 14.dp)
    ) {
        items(uiState.data.size) { index ->
            val data = uiState.data[index]
            PhotoCard(
                photoURL = data.photoURL,
                title = data.title,
                artist = data.artist,
                favorite = data.favorite,
                uploadDate = data.uploadDate
            ) {
                deleteFavorite(data.photoId)
            }
        }
    }
}