package com.deneyehayir.deneysiz.scene.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.deneyehayir.deneysiz.ui.theme.DeneysizTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeneysizTheme {
                Surface(color = MaterialTheme.colors.background) { Greeting() }
            }
        }
    }
}

@Composable
fun Greeting() {
    val mainViewModel = viewModel<MainViewModel>()
    val dummyUiModel = mainViewModel.dummyData.observeAsState()
    Text(text = "Hello ${dummyUiModel.value}!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DeneysizTheme { Greeting() }
}
