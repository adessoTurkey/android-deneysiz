package com.deneyehayir.deneysiz.scene

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.domain.model.CategoryItemUiModel
import com.deneyehayir.deneysiz.scene.categorydetail.CategoryDetailScreen
import com.deneyehayir.deneysiz.scene.discover.DiscoverScreen
import com.deneyehayir.deneysiz.ui.theme.DeneysizTheme
import com.deneyehayir.deneysiz.ui.theme.Gray
import com.deneyehayir.deneysiz.ui.theme.Orange

sealed class MainScreen(
    val route: String,
    @StringRes val titleResource: Int,
    @DrawableRes val iconResource: Int
) {
    object Discover : MainScreen(
        route = "main/discover",
        titleResource = R.string.bottom_nav_tab_discover,
        iconResource = R.drawable.ic_discover
    )

    object DoYouKnow : MainScreen(
        route = "main/doyouknow",
        titleResource = R.string.bottom_nav_tab_do_you_know,
        iconResource = R.drawable.ic_do_you_know
    )
}

sealed class DetailScreen(
    val route: String
) {
    object Category : DetailScreen(route = "detail/category")
}

@Composable
fun BottomNavBar(
    navController: NavController,
    tabs: List<MainScreen>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val selectedTab = tabs.firstOrNull { it.route == currentRoute }

    BottomNavigation(
        backgroundColor = Color(0xFFECF0F1)
    ) {
        tabs.forEach { tab ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = tab.iconResource),
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = stringResource(id = tab.titleResource))
                },
                selectedContentColor = Orange,
                unselectedContentColor = Gray,
                selected = tab == selectedTab,
                onClick = {
                    if (tab.route != currentRoute) {
                        navController.navigate(tab.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainScreen.Discover.route,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(MainScreen.Discover.route) {
            DiscoverScreen(
                modifier = modifier,
                navigateToSearch = {},
                navigateToCategory = { categoryItem ->
                    navController.apply {
                        currentBackStackEntry?.arguments = Bundle().apply {
                            putParcelable("categoryItem", categoryItem)
                        }
                        navigate(DetailScreen.Category.route)
                    }
                },
                navigateToWhoWeAre = {}
            )
        }
        composable(MainScreen.DoYouKnow.route) {
            DiscoverScreen(
                navigateToSearch = {},
                navigateToCategory = {},
                navigateToWhoWeAre = {}
            )
        }
        composable(route = DetailScreen.Category.route) {
            // TODO can be improved regarding https://issuetracker.google.com/issues/182194894#comment14
            navController.previousBackStackEntry?.arguments?.getParcelable<CategoryItemUiModel>(
                "categoryItem"
            )?.let { categoryItem ->
                CategoryDetailScreen(
                    modifier = modifier,
                    categoryItem,
                    onBack = { navController.navigateUp() },
                    onSuggestBrand = {}
                )
            }
        }
    }
}

@Preview
@Composable
fun BottomNavPreview() {
    DeneysizTheme {
        BottomNavBar(
            navController = rememberNavController(),
            tabs = listOf(MainScreen.Discover, MainScreen.DoYouKnow)
        )
    }
}
