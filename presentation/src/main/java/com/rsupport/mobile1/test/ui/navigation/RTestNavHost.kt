package com.rsupport.mobile1.test.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rsupport.mobile1.test.ui.screen.FavoriteScreen
import com.rsupport.mobile1.test.ui.screen.HomeScreen

@Composable
fun TestNavHost(
    modifier: Modifier,
    navController: NavHostController,
){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destination.HOME.title
    ){
        composable(Destination.HOME.title){
            HomeScreen()
        }
        composable(Destination.FAVORITE.title){
            FavoriteScreen()
        }
    }
}