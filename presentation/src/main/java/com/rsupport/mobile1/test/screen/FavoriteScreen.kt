package com.rsupport.mobile1.test.screen

import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.state.UIState
import com.rsupport.mobile1.test.ui.theme.TestColor
import com.rsupport.mobile1.test.viewmodel.FavoriteViewModel

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val uiState = viewModel.favoriteUIState.collectAsStateWithLifecycle()

    Log.e("TAG", "FavoriteScreen: $uiState")

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

        is UIState.Error -> {
            FavoriteErrorContent(
                uiState = uiState
            )
        }

        is UIState.Loading -> {}
    }
}

@Composable
fun FavoriteContent(
    uiState: UIState.Success,
    deleteFavorite: (Int) -> Unit
) {
    RLazyColumn(
        uiState = uiState,
        refresh = false,
        deleteFavorite = deleteFavorite
    )
}

@Composable
fun FavoriteErrorContent(
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
        ){
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

@Preview
@Composable
fun FavoriteErrorContentPreview(){
    FavoriteErrorContent(uiState = UIState.Error("캡쳐한 이미지가 없어요.", "캡쳐한 이미지가 없어요."))
}