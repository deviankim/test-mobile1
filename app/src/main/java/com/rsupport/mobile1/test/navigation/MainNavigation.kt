package com.rsupport.mobile1.test.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rsupport.mobile1.test.feature_crawling.CrawlingScreen

@Composable
fun MainNavigation(
    navController: NavHostController
) {
    NavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = navController,
        startDestination = Route.CRAWLING
    ) {
        composable(Route.CRAWLING) {
            CrawlingScreen(navController)
        }


    }
}