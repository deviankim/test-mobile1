package com.rsupport.mobile1.test.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.blue.domain.model.PhotoData
import com.rsupport.mobile1.test.state.UIState
import com.rsupport.mobile1.test.ui.theme.TestColor
import com.rsupport.mobile1.test.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.homeUIState.collectAsStateWithLifecycle()

    HomeContentWithState(
        uiState = uiState.value,
        getPhotoData = viewModel::getPhotoData,
        addFavorite = viewModel::addFavorite,
        deleteFavorite = viewModel::deleteFavorite
    )
}

@Composable
fun HomeContentWithState(
    uiState: UIState,
    getPhotoData: () -> Unit,
    addFavorite: (PhotoData) -> Unit,
    deleteFavorite: (Int) -> Unit
) {


    when (uiState) {
        is UIState.Success -> HomeContent(
            uiState = uiState,
            getPhotoData = getPhotoData,
            addFavorite = addFavorite,
            deleteFavorite = deleteFavorite
        )
        is UIState.Error -> {}
        is UIState.Loading -> {}
    }
}

@Composable
fun HomeContent(
    uiState: UIState.Success,
    getPhotoData: () -> Unit,
    addFavorite: (PhotoData) -> Unit,
    deleteFavorite: (Int) -> Unit
) {

    getPhotoData()

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
                if(!data.favorite)
                    addFavorite(data.copy(favorite = true))
                else
                    deleteFavorite(data.photoId)
            }
        }
    }
}