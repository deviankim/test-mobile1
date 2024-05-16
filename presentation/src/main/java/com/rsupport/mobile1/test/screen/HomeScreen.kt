package com.rsupport.mobile1.test.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
        is UIState.Success -> {
            HomeContent(
                uiState = uiState,
                getPhotoData = getPhotoData,
                addFavorite = addFavorite,
                deleteFavorite = deleteFavorite
            )
        }

        is UIState.Error -> {
            HomeErrorContent(
                uiState = uiState
            )
        }

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

    RLazyColumn(
        uiState = uiState,
        refresh = true,
        addFavorite = addFavorite,
        deleteFavorite = deleteFavorite,
        getPhotoData = getPhotoData
    )
}

@Composable
fun HomeErrorContent(
    uiState: UIState.Error
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TestColor.BackGround),
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
            text = uiState.mainMassage,
            fontWeight = FontWeight.Black,
            color = TestColor.Primary,
            fontSize = 16.sp
        )
        Text(
            text = uiState.subMassage,
            fontSize = 14.sp
        )
    }
}