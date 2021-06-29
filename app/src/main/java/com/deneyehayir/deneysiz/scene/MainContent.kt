package com.deneyehayir.deneysiz.scene

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.deneyehayir.deneysiz.ui.theme.DeneysizTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MainContent() {
    DeneysizTheme {
        val tabs = remember {
            listOf(
                MainScreen.Discover,
                MainScreen.DoYouKnow
            )
        }
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val bottomTabRoutes = remember { tabs.map { it.route } }

        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = true
            )
        }

        Scaffold(
            bottomBar = {
                if (currentRoute in bottomTabRoutes) {
                    BottomNavBar(navController = navController, tabs = tabs)
                }
            }
        ) { innerPaddingModifier ->
            MainNavGraph(
                navController = navController,
                modifier = Modifier.padding(innerPaddingModifier)
            )
        }
    }
}
