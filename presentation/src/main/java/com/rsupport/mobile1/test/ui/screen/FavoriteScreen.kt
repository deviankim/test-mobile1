package com.rsupport.mobile1.test.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.state.UIState
import com.rsupport.mobile1.test.ui.theme.RColor
import com.rsupport.mobile1.test.viewmodel.FavoriteViewModel

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val favoriteUIState = viewModel.favoriteHomeUIState.collectAsStateWithLifecycle()

    FavoriteContentWithState(
        favoriteUIState = favoriteUIState.value,
        deleteFavorite = viewModel::deleteFavorite
    )
}

@Composable
fun FavoriteContentWithState(
    favoriteUIState: UIState,
    deleteFavorite: (Int) -> Unit
) {
    when (favoriteUIState) {
        is UIState.Success -> FavoriteContent(
            favoriteUIState = favoriteUIState,
            deleteFavorite = deleteFavorite
        )

        is UIState.Error -> {
            FavoriteErrorContent(
                homeUIState = favoriteUIState
            )
        }

        is UIState.Loading -> {}
    }
}

@Composable
fun FavoriteContent(
    favoriteUIState: UIState.Success,
    deleteFavorite: (Int) -> Unit
) {
    RLazyColumn(
        photoList = favoriteUIState.data,
        refreshAble = false,
        deleteFavorite = deleteFavorite
    )
}

@Composable
fun FavoriteErrorContent(
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
        ){
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