package com.deneyehayir.deneysiz.scene.searchmain.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.deneyehayir.deneysiz.scene.searchmain.SearchMainRoute

const val searchMainGraph = "searchMainGraph"
const val searchMainRoute = "searchMainRoute"

fun NavController.navigateToSearchMain(navOptions: NavOptions? = null) {
    this.navigate(searchMainGraph, navOptions)
}

fun NavGraphBuilder.searchMainScreen(
    onInputFieldClick: () -> Unit,
    navigateToWhoWeAre: () -> Unit,
    nestedGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = searchMainGraph,
        startDestination = searchMainRoute
    ) {
        composable(
            route = searchMainRoute
        ) {
            SearchMainRoute(
                onInputFieldClick = onInputFieldClick,
                navigateToWhoWeAre = navigateToWhoWeAre
            )
        }
        nestedGraph()
    }
}
