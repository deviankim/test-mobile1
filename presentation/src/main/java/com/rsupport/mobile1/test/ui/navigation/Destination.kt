package com.rsupport.mobile1.test.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class Destination(
    val title: String,
    val icon: ImageVector
){
    HOME(
        title = "Home",
        icon = Icons.Default.Home,
    ),
    FAVORITE(
        title = "Favorite",
        icon = Icons.Default.Favorite,
    )
}
