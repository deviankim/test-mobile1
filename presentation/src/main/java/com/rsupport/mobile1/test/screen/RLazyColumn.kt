package com.rsupport.mobile1.test.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.blue.domain.model.PhotoData
import com.rsupport.mobile1.test.state.UIState
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RLazyColumn(
    uiState: UIState.Success,
    refresh: Boolean,
    addFavorite: (PhotoData) -> Unit = {},
    deleteFavorite: (Int) -> Unit,
    getPhotoData: () -> Unit = {}
) {
    val pullRefreshState = rememberPullToRefreshState()
    if (pullRefreshState.isRefreshing) {
        LaunchedEffect(true) {
            getPhotoData()
            delay(1000)
            pullRefreshState.endRefresh()
        }
    }

    Box(
        modifier = Modifier.nestedScroll(pullRefreshState.nestedScrollConnection)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(start = 30.dp, end = 30.dp, top = 14.dp)
        ) {
            items(uiState.data.size) { index ->
                val data = uiState.data[index]
                RPhotoCard(
                    photoURL = data.photoURL,
                    title = data.title,
                    artist = data.artist,
                    favorite = data.favorite,
                    uploadDate = data.uploadDate
                ) {
                    if (!data.favorite)
                        addFavorite(data.copy(favorite = true))
                    else
                        deleteFavorite(data.photoId)
                }
            }
        }
        if(refresh)
            PullToRefreshContainer(
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
    }
}