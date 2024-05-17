package com.rsupport.mobile1.test.ui.screen

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
import com.rsupport.mobile1.test.state.UIState
import com.rsupport.mobile1.test.ui.theme.RColor
import com.rsupport.mobile1.test.viewmodel.HomeViewModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeUIState = viewModel.homeUIState.collectAsStateWithLifecycle()

    LaunchedEffect(true){
        viewModel.getPhotoData()
    }

    HomeContentWithState(
        homeUIState = homeUIState.value,
        getPhotoData = viewModel::getPhotoData,
        addFavorite = viewModel::addFavorite,
        deleteFavorite = viewModel::deleteFavorite
    )
}

@Composable
fun HomeContentWithState(
    homeUIState: UIState,
    getPhotoData: () -> Unit,
    addFavorite: (PhotoData) -> Unit,
    deleteFavorite: (Int) -> Unit
) {
    when (homeUIState) {
        is UIState.Error -> {
            HomeErrorContent(
                homeUIState = homeUIState
            )
        }
        is UIState.Success -> {
            HomeContent(
                homeUIState = homeUIState,
                getPhotoData = getPhotoData,
                addFavorite = addFavorite,
                deleteFavorite = deleteFavorite
            )
        }
        is UIState.Loading -> {
            LoadingProgress()
        }
    }
}

@Composable
fun HomeContent(
    homeUIState: UIState.Success,
    getPhotoData: () -> Unit,
    addFavorite: (PhotoData) -> Unit,
    deleteFavorite: (Int) -> Unit
) {
    RLazyColumn(
        photoList = homeUIState.data,
        refreshAble = true,
        addFavorite = addFavorite,
        deleteFavorite = deleteFavorite,
        getPhotoData = getPhotoData
    )
}

@Composable
fun HomeErrorContent(
    homeUIState: UIState.Error
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
            text = homeUIState.mainMassage,
            fontWeight = FontWeight.Black,
            color = RColor.Primary,
            fontSize = 16.sp
        )
        Text(
            text = homeUIState.subMassage,
            fontSize = 14.sp
        )
    }
}

