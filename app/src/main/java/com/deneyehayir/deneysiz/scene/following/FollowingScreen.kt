package com.deneyehayir.deneysiz.scene.following

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.internal.extension.getResultOnce
import com.deneyehayir.deneysiz.internal.util.rememberFlowWithLifecycle
import com.deneyehayir.deneysiz.scene.categorydetail.BrandDetail
import com.deneyehayir.deneysiz.scene.categorydetail.ScoreBox
import com.deneyehayir.deneysiz.scene.categorydetail.model.CategoryDetailItemUiModel
import com.deneyehayir.deneysiz.scene.component.LoadingScreen
import com.deneyehayir.deneysiz.scene.component.MainTopAppBar
import com.deneyehayir.deneysiz.scene.isComingBackFavorite
import com.deneyehayir.deneysiz.ui.theme.DarkTextColor
import com.deneyehayir.deneysiz.ui.theme.Orange
import com.deneyehayir.deneysiz.ui.theme.RowColor
import com.deneyehayir.deneysiz.ui.theme.White0
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun FollowingRoute(
    modifier: Modifier = Modifier,
    viewModel: FollowingViewModel = hiltViewModel(),
    navController: NavController,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navigateToBrandDetail: (Int) -> Unit
) {
    val uiState by rememberFlowWithLifecycle(viewModel.uiState)
        .collectAsState(initial = FollowingUiState.Loading)

    DisposableEffect(Unit) {
        navController.getResultOnce<Boolean>(
            keyResult = isComingBackFavorite,
            onResult = { state ->
                if (state) {
                    viewModel.handleComingBackDetailState()
                }
            }
        )
        onDispose { }
    }

    FollowingScreen(
        modifier = modifier,
        uiState = uiState,
        coroutineScope = coroutineScope,
        navigateToBrandDetail = navigateToBrandDetail,
        onDeleteClick = viewModel::handleDeleteClick
    )
}

@Composable
fun FollowingScreen(
    modifier: Modifier,
    uiState: FollowingUiState,
    coroutineScope: CoroutineScope,
    navigateToBrandDetail: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopAppBar(
                titleRes = R.string.top_bar_title_following,
                titleColor = DarkTextColor
            )
        }
    ) { padding ->
        when (uiState) {
            FollowingUiState.Loading -> {
                LoadingScreen()
            }
            FollowingUiState.Empty -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(padding)
                        .padding(top = 48.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    FollowingEmptyItem(
                        modifier = Modifier.padding(horizontal = 48.dp)
                    )
                }
            }
            is FollowingUiState.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(top = 16.dp)
                ) {
                    items(uiState.data.size) { index ->
                        BrandRowFollowing(
                            item = uiState.data[index],
                            coroutineScope = coroutineScope,
                            navigateToBrandDetail = navigateToBrandDetail,
                            onDeleteClick = onDeleteClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BrandRowFollowing(
    item: CategoryDetailItemUiModel,
    coroutineScope: CoroutineScope,
    navigateToBrandDetail: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit
) {
    val swipeableState = rememberSwipeableState(initialValue = 0f)

    val sizePx = with(LocalDensity.current) { (56.dp).toPx() }
    val anchors = mapOf(0f to 0f, -sizePx to 1f) // Maps anchor points (in px) to states

    var cardHeight by remember { mutableStateOf(0) }
    val actionHeightDp = LocalDensity.current.run { cardHeight.toDp() }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned {
                cardHeight = it.size.height
            }
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ ->
                    FractionalThreshold(0.3f)
                },
                orientation = Orientation.Horizontal
            )
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = { navigateToBrandDetail(item.id) })
                .background(color = RowColor)
                .padding(16.dp)
                .align(Alignment.Center)
                .offset {
                    IntOffset(swipeableState.offset.value.roundToInt(), 0)
                }
        ) {
            BrandDetail(
                modifier = Modifier.weight(1f),
                brandName = item.brandName,
                brandParentCompanyName = item.parentCompanyName
            )
            ScoreBox(
                backgroundColor = item.scoreBackgroundColor,
                score = item.score
            )
        }
        BrandSwipeActionRowFollowing(
            modifier = Modifier
                .width(56.dp)
                .height(actionHeightDp)
                .align(Alignment.CenterEnd)
                .offset(x = (56).dp)
                .offset {
                    IntOffset(swipeableState.offset.value.roundToInt(), 0)
                },
            brandId = item.id,
            onDeleteClick = { id ->
                coroutineScope.launch {
                    swipeableState.animateTo(0f)
                }
                onDeleteClick(id)
            }
        )
    }
}

@Composable
fun BrandSwipeActionRowFollowing(
    modifier: Modifier = Modifier,
    brandId: Int,
    onDeleteClick: (Int) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Orange)
            .clickable {
                onDeleteClick.invoke(brandId)
            }
    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.ic_delete),
            contentDescription = null,
            tint = White0
        )
    }
}

@Composable
fun FollowingEmptyItem(
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_empty_following),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = stringResource(id = R.string.following_empty_title),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = DarkTextColor
        )
        Text(
            text = stringResource(id = R.string.following_empty_desc),
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            textAlign = TextAlign.Center,
            color = DarkTextColor
        )
    }
}
