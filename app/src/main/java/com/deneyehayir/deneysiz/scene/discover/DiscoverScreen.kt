package com.deneyehayir.deneysiz.scene.discover

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.data.remote.model.CategoryType
import com.deneyehayir.deneysiz.domain.model.CategoryItemUiModel
import com.deneyehayir.deneysiz.scene.component.MainTopAppBar
import com.deneyehayir.deneysiz.ui.theme.Blue
import com.deneyehayir.deneysiz.ui.theme.DeneysizTheme
import com.deneyehayir.deneysiz.ui.theme.TextDark

@Composable
fun DiscoverScreen(
    modifier: Modifier = Modifier,
    navigateToSearch: () -> Unit,
    navigateToCategory: (CategoryType) -> Unit,
    navigateToWhoWeAre: () -> Unit
) {
    val discoverViewModel = hiltViewModel<DiscoverViewModel>()
    val categories = discoverViewModel.categoryData.observeAsState().value.orEmpty()
    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopAppBar(
                titleRes = R.string.top_bar_title_discover,
                titleColor = TextDark,
                actionsColor = Blue,
                navigateToWhoWeAre = navigateToWhoWeAre
            )
        }
    ) {
        CategoryList(
            categories = categories,
            navigateToSearch = navigateToSearch,
            navigateToCategory = navigateToCategory
        )
    }
}

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: CategoryItemUiModel,
    navigateToCategory: (CategoryType) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .clickable { navigateToCategory(category.type) }
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
                                Color(0x80000000),
                                Color.Transparent
                            )
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient)
                        }
                    }
                    .then(modifier)
            )
            Text(
                text = stringResource(id = category.nameResource),
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(0.9f),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun CategoryList(
    categories: List<CategoryItemUiModel>,
    navigateToSearch: () -> Unit,
    navigateToCategory: (CategoryType) -> Unit
) {
    if (categories.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(18.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                BrandSearch(navigateToSearch = navigateToSearch)
                Spacer(modifier = Modifier.height(8.dp))
            }

            val header = categories.first()
            item {
                CategoryItem(
                    modifier = Modifier.fillMaxWidth(),
                    category = header,
                    navigateToCategory = navigateToCategory
                )
            }

            val categoryList = categories.takeLast(categories.size - 1)

            items(categoryList.windowed(2, 2, true)) { categoryRow ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    categoryRow.forEach { category ->
                        CategoryItem(
                            modifier = Modifier
                                .height(96.dp)
                                .fillParentMaxWidth(0.5f),
                            category = category,
                            navigateToCategory = navigateToCategory
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BrandSearch(navigateToSearch: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .clickable { navigateToSearch() },
        backgroundColor = Color(0xFFECF0F1)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                tint = Color(0xFF7F8C8D)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = R.string.search_brand),
                color = Color.Black,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Preview
@Composable
fun CategoryListGridPreview() {
    DeneysizTheme {
        CategoryList(
            mutableListOf<CategoryItemUiModel>().apply {
                repeat(9) {
                    add(
                        CategoryItemUiModel(
                            type = CategoryType.ALL_BRANDS,
                            nameResource = R.string.discover_category_all_brands,
                            imageResource = R.drawable.ic_category_all_brands
                        )
                    )
                }
            },
            navigateToSearch = {},
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
            titleColor = TextDark,
            actionsColor = Blue,
            navigateToWhoWeAre = {}
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
