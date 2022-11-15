package com.deneyehayir.deneysiz.scene

import androidx.compose.runtime.*
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.deneyehayir.deneysiz.R
import kotlinx.coroutines.CoroutineScope


@Composable
fun rememberMainAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): MainAppState {
    return remember(navController, coroutineScope) {
        MainAppState(navController, coroutineScope)
    }
}

@Stable
class MainAppState(
    val navController: NavHostController, val coroutineScope: CoroutineScope
) {

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination


    val shouldShowBottomNavBar: Boolean
        @Composable get() = currentDestination?.route in bottomNavTabs.map { it.route }

    val shouldShowTopAppBar: Boolean
        @Composable get() = currentDestination?.route in bottomNavTabs.map { it.route }


    val bottomNavTabs: List<MainScreen> = listOf(
        MainScreen.SearchMain, MainScreen.Discover, MainScreen.DoYouKnow
    )

}

