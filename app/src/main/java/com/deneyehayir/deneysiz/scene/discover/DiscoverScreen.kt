package com.deneyehayir.deneysiz.scene.discover

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.domain.model.CategoryType
import com.deneyehayir.deneysiz.internal.util.rememberFlowWithLifecycle
import com.deneyehayir.deneysiz.scene.component.MainTopAppBar
import com.deneyehayir.deneysiz.scene.component.TopAppBarWhoWeAreAction
import com.deneyehayir.deneysiz.scene.discover.model.CategoryItemUiModel
import com.deneyehayir.deneysiz.scene.discover.model.CategoryUiModel
import com.deneyehayir.deneysiz.ui.theme.Blue
import com.deneyehayir.deneysiz.ui.theme.DarkTextColor
import com.deneyehayir.deneysiz.ui.theme.DeneysizTheme
import com.deneyehayir.deneysiz.ui.theme.GradientDark
import com.deneyehayir.deneysiz.ui.theme.Transparent

@Composable
fun DiscoverScreen(
    modifier: Modifier = Modifier,
    navigateToCategory: (CategoryItemUiModel) -> Unit,
    navigateToWhoWeAre: () -> Unit
) {
    val discoverViewModel = hiltViewModel<DiscoverViewModel>()
    val discoveryViewState by rememberFlowWithLifecycle(discoverViewModel.discoverViewState)
        .collectAsState(initial = DiscoverViewState.Initial)
    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopAppBar(
                titleRes = R.string.top_bar_title_discover,
                titleColor = DarkTextColor,
                actions = {
                    TopAppBarWhoWeAreAction(
                        color = Blue,
                        navigateToWhoWeAre = navigateToWhoWeAre
                    )
                }
            )
        }
    ) {
        when {
            discoveryViewState.isLoading -> {}
            discoveryViewState.shouldShowError -> {}
            else -> CategoryContent(
                categoryData = discoveryViewState.categoryUiModel,
                navigateToCategory = navigateToCategory
            )
        }
    }
}

/*
@Composable
private fun DiscoverScreen(
    modifier: Modifier = Modifier,
    navigateToSearch: () -> Unit,
    navigateToCategory: (CategoryItemUiModel) -> Unit,
    navigateToWhoWeAre: () -> Unit,
    discoveryViewState: DiscoverViewState
) {
    // TODO: improve condition
    when {
        discoveryViewState.isLoading -> {}
        discoveryViewState.shouldShowError -> {}
        else -> CategoryContent(
            categoryData = discoveryViewState.categoryUiModel,
            navigateToCategory = navigateToCategory
        )
    }
}*/

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: CategoryItemUiModel,
    textWidthFraction: Float = 0.9f,
    navigateToCategory: (CategoryItemUiModel) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .clickable { navigateToCategory(category) }
            .then(modifier)
    ) {
        Box {
            Image(
                painter = painterResource(id = category.imageResource),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .drawWithCache {
                        val gradient = Brush.horizontalGradient(
                            colors = listOf(
                                GradientDark,
                                Transparent
                            )
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient)
                        }
                    }
                    .fillMaxSize()
            )
            Text(
                text = stringResource(id = category.nameResource),
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(textWidthFraction),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun CategoryContent(
    categoryData: CategoryUiModel,
    navigateToCategory: (CategoryItemUiModel) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            CategoryItem(
                modifier = Modifier.fillMaxWidth(),
                category = categoryData.headerItem,
                textWidthFraction = 0.4f,
                navigateToCategory = navigateToCategory
            )
        }

        items(categoryData.windowedListItems) { categoryRow ->
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                categoryRow.forEachIndexed { index, category ->
                    CategoryItem(
                        modifier = Modifier
                            .height(96.dp)
                            .fillParentMaxWidth(0.5f)
                            .padding(
                                start = if (index == 1) 8.dp else 0.dp,
                                end = if (index == 0) 8.dp else 0.dp
                            ),
                        category = category,
                        navigateToCategory = navigateToCategory
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CategoryListGridPreview() {
    DeneysizTheme {
        CategoryContent(
            categoryData = CategoryUiModel(
                headerItem = CategoryItemUiModel(
                    type = CategoryType.ALL_BRANDS,
                    nameResource = R.string.discover_category_all_brands,
                    imageResource = R.drawable.ic_category_all_brands
                ),
                listItems = mutableListOf<CategoryItemUiModel>().apply {
                    repeat(9) {
                        add(
                            CategoryItemUiModel(
                                type = CategoryType.ALL_BRANDS,
                                nameResource = R.string.discover_category_all_brands,
                                imageResource = R.drawable.ic_category_all_brands
                            )
                        )
                    }
                }
            ),
            navigateToCategory = {}
        )
    }
}

@Preview
@Composable
fun DiscoverAppBarPreview() {
    DeneysizTheme {
        MainTopAppBar(
            titleRes = R.string.top_bar_title_discover,
            titleColor = DarkTextColor,
            actions = {}
        )
    }
}

@Preview
@Composable
fun DiscoverHeaderPreview() {
    DeneysizTheme {
        CategoryItem(
            modifier = Modifier.fillMaxWidth(),
            category = CategoryItemUiModel(
                type = CategoryType.ALL_BRANDS,
                nameResource = R.string.discover_category_all_brands,
                imageResource = R.drawable.ic_category_all_brands
            ),
            navigateToCategory = {}
        )
    }
}
