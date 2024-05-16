package com.rsupport.mobile1.test

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rsupport.mobile1.test.navigation.Destination
import com.rsupport.mobile1.test.navigation.TestNavHost
import com.rsupport.mobile1.test.ui.theme.RColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RApp(){
    val barItems = listOf(Destination.HOME, Destination.FAVORITE)
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            RTopBar()
        },
        bottomBar = {
            RBottomBar(
                barItems,
                navController
            )
        }
    ) { padding ->
        TestNavHost(
            modifier = Modifier.padding(padding),
            navController = navController
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RTopBar(){
    TopAppBar(
        title = { Text(
            text = "RSupport Test",
            fontSize = 20.sp,
            color = RColor.BackGround
        ) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = RColor.Primary)
    )
}

@Composable
fun RBottomBar(
    barItems: List<Destination>,
    navController: NavController
){
    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar(
        containerColor = RColor.Primary,
        contentColor = RColor.Primary
    ) {
        barItems.forEachIndexed { index, barItem ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = RColor.BackGround,
                    unselectedIconColor = RColor.LightPrimary,
                    indicatorColor = RColor.Primary
                ),
                selected = selectedIndex == index,
                onClick = {
                    if(selectedIndex != index) {
                        selectedIndex = index
                        navController.navigate(barItem.title)
                    }
                },
                label = { Text(text = barItem.title) },
                icon = {
                    Icon(
                        imageVector = barItem.icon,
                        contentDescription = barItem.title,
                        modifier = Modifier.size(36.dp),
                    )
                }
            )
        }
    }
}