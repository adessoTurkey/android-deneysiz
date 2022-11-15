package com.deneyehayir.deneysiz.scene

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.scene.branddetail.BrandDetailScreen
import com.deneyehayir.deneysiz.scene.categorydetail.CategoryDetailScreen
import com.deneyehayir.deneysiz.scene.discover.DiscoverScreen
import com.deneyehayir.deneysiz.scene.donation.DonationScreen
import com.deneyehayir.deneysiz.scene.doyouknow.DoYouKnowScreen
import com.deneyehayir.deneysiz.scene.doyouknowcontent.DoYouKnowContentScreen
import com.deneyehayir.deneysiz.scene.search.navigation.navigateToSearch
import com.deneyehayir.deneysiz.scene.search.navigation.searchScreen
import com.deneyehayir.deneysiz.scene.searchmain.navigation.navigateToSearchMain
import com.deneyehayir.deneysiz.scene.searchmain.navigation.searchMainRoute
import com.deneyehayir.deneysiz.scene.searchmain.navigation.searchMainScreen
import com.deneyehayir.deneysiz.scene.splash.SplashScreen
import com.deneyehayir.deneysiz.scene.support.SupportScreen
import com.deneyehayir.deneysiz.scene.whoweare.WhoWeAreScreen
import com.deneyehayir.deneysiz.ui.theme.BottomNavColor
import com.deneyehayir.deneysiz.ui.theme.DeneysizTheme
import com.deneyehayir.deneysiz.ui.theme.Gray
import com.deneyehayir.deneysiz.ui.theme.Orange

const val navCategoryId = "categoryId"
const val navCategoryStringRes = "categoryStringRes"
const val navBrandDetailBrandId = "brandId"
const val navDoYouKnowContentId = "doYouKnowContentId"
const val splashScreenRoute = "splash"

sealed class MainScreen(
    val route: String, @StringRes val titleResource: Int, @DrawableRes val iconResource: Int
) {
    object SearchMain : MainScreen(
        route = searchMainRoute,
        titleResource = R.string.bottom_nav_tab_search,
        iconResource = R.drawable.ic_search
    )

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
    object Category : DetailScreen(
        route = "detail/category?categoryId={$navCategoryId}" + "&categoryStringRes={$navCategoryStringRes}"
    ) {
        fun createRoute(
            categoryId: String, @StringRes categoryStringRes: Int
        ): String = "detail/category?categoryId=$categoryId&categoryStringRes=$categoryStringRes"
    }

    object BrandDetail : DetailScreen(
        route = "brandDetail/brandId={$navBrandDetailBrandId}"
    ) {
        fun createRoute(
            brandId: Int
        ): String = "brandDetail/brandId=$brandId"
    }

    object DoYouKnowContentDetail : DetailScreen(
        route = "doYouKnowContentDetail/id={$navDoYouKnowContentId}"
    ) {
        fun createRoute(
            contentId: Int
        ): String = "doYouKnowContentDetail/id=$contentId"
    }

    object WhoWeAreScreen : DetailScreen(
        route = "whoWeAre"
    )

    object DonationScreen : DetailScreen(
        route = "donation"
    )

    object SupportScreen : DetailScreen(
        route = "support"
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomNavBar(
    bottomNavVisibilityState: Boolean,
    navController: NavController,
    tabs: List<MainScreen>,
    currentDestination: NavDestination?
) {

    AnimatedVisibility(visible = bottomNavVisibilityState,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        BottomNavigation(
            backgroundColor = BottomNavColor
        ) {
            tabs.forEach { tab ->
                val selected = currentDestination.isTopLevelDestinationInHierarchy(tab)
                BottomNavigationItem(icon = {
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
                    selected = selected,
                    onClick = {
                        if (tab.route != currentDestination?.route) {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    })
            }
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: MainScreen) =
    this?.hierarchy?.any {
        it.route?.contains(destination.route, true) ?: false
    } ?: false

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = splashScreenRoute,
) {
    NavHost(navController = navController, startDestination = startDestination) {

        searchMainScreen(onInputFieldClick = {
            navController.navigateToSearch()
        }, nestedGraph = {
            searchScreen(navigateToBrandDetail = { brandId ->
                navController.navigate(
                    DetailScreen.BrandDetail.createRoute(
                        brandId = brandId
                    )
                )
            })
        }, navigateToWhoWeAre = {
            navController.navigate(
                DetailScreen.WhoWeAreScreen.route
            )
        })

        composable(splashScreenRoute) {
            SplashScreen(onSplashComplete = {
                navController.navigateToSearchMain(navOptions = navOptions {
                    popUpTo(splashScreenRoute) {
                        inclusive = true
                    }
                })
            })
        }
        composable(MainScreen.Discover.route) {
            DiscoverScreen(modifier = modifier,
                navigateToSearch = {},
                navigateToCategory = { categoryItem ->
                    navController.navigate(
                        DetailScreen.Category.createRoute(
                            categoryId = categoryItem.type.type,
                            categoryStringRes = categoryItem.nameResource
                        )
                    )
                },
                navigateToWhoWeAre = {
                    navController.navigate(
                        DetailScreen.WhoWeAreScreen.route
                    )
                })
        }
        composable(MainScreen.DoYouKnow.route) {
            DoYouKnowScreen(modifier = modifier, navigateToWhoWeAre = {
                navController.navigate(
                    DetailScreen.WhoWeAreScreen.route
                )
            }, onNavigateCertificateDetail = { contentId ->
                navController.navigate(
                    DetailScreen.DoYouKnowContentDetail.createRoute(
                        contentId = contentId
                    )
                )
            }, onNavigateFaqDetail = { contentId ->
                navController.navigate(
                    DetailScreen.DoYouKnowContentDetail.createRoute(
                        contentId = contentId
                    )
                )
            })
        }
        composable(
            route = DetailScreen.Category.route, arguments = listOf(navArgument(navCategoryId) {
                type = NavType.StringType
            }, navArgument(navCategoryStringRes) {
                type = NavType.IntType
            })
        ) { backstackEntry ->
            val categoryId = backstackEntry.arguments?.getString(navCategoryId)
            val categoryStringRes = backstackEntry.arguments?.getInt(navCategoryStringRes)
            if (categoryId != null && categoryStringRes != null) {
                CategoryDetailScreen(
                    modifier = modifier,
                    onBrandDetail = { brandId ->
                        navController.navigate(
                            DetailScreen.BrandDetail.createRoute(
                                brandId = brandId
                            )
                        )
                    },
                    onBack = { navController.navigateUp() },
                )
            }
        }
        composable(
            route = DetailScreen.BrandDetail.route,
            arguments = listOf(navArgument(navBrandDetailBrandId) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val brandId = backStackEntry.arguments?.getInt(navBrandDetailBrandId)
            if (brandId != null) {
                BrandDetailScreen(modifier = modifier,
                    onBack = { navController.navigateUp() },
                    onNavigateCertificateDetail = { contentId ->
                        navController.navigate(
                            DetailScreen.DoYouKnowContentDetail.createRoute(
                                contentId = contentId
                            )
                        )
                    })
            }
        }
        composable(
            route = DetailScreen.DoYouKnowContentDetail.route,
            arguments = listOf(navArgument(navDoYouKnowContentId) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val contentId = backStackEntry.arguments?.getInt(navDoYouKnowContentId)
            if (contentId != null) {
                DoYouKnowContentScreen(modifier = modifier, onBack = { navController.navigateUp() })
            }
        }
        composable(route = DetailScreen.WhoWeAreScreen.route) {
            WhoWeAreScreen(modifier = modifier,
                onBack = { navController.navigateUp() },
                onSupportNavigation = {
                    navController.navigate(
                        DetailScreen.SupportScreen.route
                    )
                },
                onDonationNavigation = {
                    navController.navigate(
                        DetailScreen.DonationScreen.route
                    )
                })
        }
        composable(route = DetailScreen.DonationScreen.route) {
            DonationScreen(modifier = modifier, onBack = { navController.navigateUp() })
        }
        composable(route = DetailScreen.SupportScreen.route) {
            SupportScreen(modifier = modifier, onBack = { navController.navigateUp() })
        }
    }
}

@Preview
@Composable
fun BottomNavPreview() {
    DeneysizTheme {
//        BottomNavBar(
//            navController = rememberNavController(),
//            tabs = listOf(MainScreen.Discover, MainScreen.DoYouKnow)
//        )
    }
}
