package com.rsupport.mobile1.test.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rsupport.mobile1.test.feature_crawling.presentation.screen.CrawlingScreen
import com.rsupport.mobile1.test.feature_crawling.presentation.viewmodel.CrawlingViewModel

@Composable
fun MainNavigation(
    navController: NavHostController
) {
    val crawlingViewModel: CrawlingViewModel = hiltViewModel()
    NavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = navController,
        startDestination = Route.CRAWLING
    ) {
        composable(Route.CRAWLING) {
            CrawlingScreen(
                navController,
                crawlingViewModel.imageLoadState.collectAsState(),
                onClick = {crawlingViewModel.getImage(it)}
            )
        }


    }
}