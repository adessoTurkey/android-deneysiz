package com.deneyehayir.deneysiz.scene.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.ui.theme.Orange
import com.deneyehayir.deneysiz.ui.theme.White0
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SplashScreen(
    onSplashComplete: () -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_splash))
    val animationState = animateLottieCompositionAsState(composition)

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.apply {
            setStatusBarColor(
                color = if (animationState.progress != 1f) Orange else White0
            )
            isNavigationBarVisible = animationState.progress != 1f
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Orange),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = animationState.progress
        )
    }

    if (animationState.progress == 1f) {
        LaunchedEffect(Unit) {
            onSplashComplete()
        }
    }
}
