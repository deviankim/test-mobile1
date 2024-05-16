package com.rsupport.mobile1.test.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.blue.domain.model.PhotoData
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.state.HomeUIState
import com.rsupport.mobile1.test.ui.theme.RColor
import com.rsupport.mobile1.test.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.homeUIState.collectAsStateWithLifecycle()

    LaunchedEffect(true){
        viewModel.getPhotoData()
    }

    HomeContentWithState(
        homeUiState = uiState.value,
        getPhotoData = viewModel::getPhotoData,
        addFavorite = viewModel::addFavorite,
        deleteFavorite = viewModel::deleteFavorite
    )
}

@Composable
fun HomeContentWithState(
    homeUiState: HomeUIState,
    getPhotoData: () -> Unit,
    addFavorite: (PhotoData) -> Unit,
    deleteFavorite: (Int) -> Unit
) {
    when (homeUiState) {
        is HomeUIState.Error -> {
            HomeErrorContent(
                homeUiState = homeUiState
            )
        }
        is HomeUIState.Success -> {
            HomeContent(
                homeUiState = homeUiState,
                getPhotoData = getPhotoData,
                addFavorite = addFavorite,
                deleteFavorite = deleteFavorite
            )
        }
        is HomeUIState.Loading -> {
            LoadingProgress()
        }
    }
}

@Composable
fun HomeContent(
    homeUiState: HomeUIState.Success,
    getPhotoData: () -> Unit,
    addFavorite: (PhotoData) -> Unit,
    deleteFavorite: (Int) -> Unit
) {
    RLazyColumn(
        photoList = homeUiState.data,
        refreshAble = true,
        addFavorite = addFavorite,
        deleteFavorite = deleteFavorite,
        getPhotoData = getPhotoData
    )
}

@Composable
fun HomeErrorContent(
    homeUiState: HomeUIState.Error
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RColor.BackGround),
        verticalArrangement = Arrangement.spacedBy(
            space = 6.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(id = R.drawable.rsupport_icon),
            contentScale = ContentScale.Crop,
            contentDescription = "rsupport_icon"
        )
        Text(
            text = homeUiState.mainMassage,
            fontWeight = FontWeight.Black,
            color = RColor.Primary,
            fontSize = 16.sp
        )
        Text(
            text = homeUiState.subMassage,
            fontSize = 14.sp
        )
    }
}

