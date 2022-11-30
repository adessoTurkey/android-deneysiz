package com.deneyehayir.deneysiz.scene.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.deneyehayir.deneysiz.scene.search.SearchRoute

const val searchRoute = "search_route"

fun NavController.navigateSearch(navOptions: NavOptions? = null) {
    this.navigate(searchRoute, navOptions)
}

fun NavGraphBuilder.searchScreen(
    navigateToBrandDetail: (Int) -> Unit
) {
    composable(
        route = searchRoute
    ) {
        SearchRoute(navigateToBrandDetail = navigateToBrandDetail)
    }
}
