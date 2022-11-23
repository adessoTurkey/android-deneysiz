package com.deneyehayir.deneysiz.scene

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.deneyehayir.deneysiz.ui.theme.DeneysizTheme

@Composable
fun MainContent(
    appState: MainAppState = rememberMainAppState()
) {
    DeneysizTheme {
        Scaffold(bottomBar = {
            BottomNavBar(
                bottomNavVisibilityState = appState.shouldShowBottomNavBar,
                navController = appState.navController,
                tabs = appState.bottomNavTabs,
                currentDestination = appState.currentDestination
            )
        }) { innerPaddingModifier ->
            MainNavGraph(
                navController = appState.navController,
                modifier = Modifier.padding(innerPaddingModifier)
            )
        }
    }
}
