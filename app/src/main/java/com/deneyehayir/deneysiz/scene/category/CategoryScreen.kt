package com.deneyehayir.deneysiz.scene.category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import com.deneyehayir.deneysiz.data.remote.model.CategoryType

@Composable
fun CategoryScreen(category: CategoryType) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = category.toString(),
            color = Blue
        )
    }
}
