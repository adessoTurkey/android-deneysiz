package com.deneyehayir.deneysiz.scene.categorydetail

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.domain.model.CategoryDetailItemUiModel
import com.deneyehayir.deneysiz.domain.model.CategoryItemUiModel
import com.deneyehayir.deneysiz.internal.util.rememberFlowWithLifecycle
import com.deneyehayir.deneysiz.ui.theme.DarkBlue
import com.deneyehayir.deneysiz.ui.theme.DeneysizTheme
import com.deneyehayir.deneysiz.ui.theme.Gray
import com.deneyehayir.deneysiz.ui.theme.TextDark

@Composable
fun CategoryDetailScreen(
    modifier: Modifier = Modifier,
    categoryItem: CategoryItemUiModel,
    onBack: () -> Unit,
    onSuggestBrand: () -> Unit
) {
    val categoryDetailViewModel = hiltViewModel<CategoryDetailViewModel>()
    val viewState by rememberFlowWithLifecycle(categoryDetailViewModel.viewState)
        .collectAsState(initial = CategoryDetailViewState.Initial)

    Scaffold(
        modifier = modifier,
        topBar = {
            CategoryDetailTopBar(
                titleRes = categoryItem.nameResource,
                onBack = onBack,
                onSuggestBrand = onSuggestBrand
            )
        }
    ) {
        CategoryDetailScreen(
            categoryDetails = viewState.brandsList,
            currentSortOption = viewState.sortOption,
            onSortSelected = { sortOption ->
                categoryDetailViewModel.sortOption.value = sortOption
            }
        )
    }
}

@Composable
private fun CategoryDetailScreen(
    categoryDetails: List<CategoryDetailItemUiModel>,
    onSortSelected: (SortOption) -> Unit,
    currentSortOption: SortOption

) {
    if (categoryDetails.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    SortSelectionButton(
                        sortOptions = SortOption.values().toList(),
                        onSortSelected = onSortSelected,
                        currentSortOption = currentSortOption
                    )
                }
            }
            item {
                Text(
                    modifier = Modifier.padding(
                        top = 16.dp,
                        start = 16.dp,
                        bottom = 8.dp
                    ),
                    text = stringResource(
                        id = R.string.category_detail_found_count,
                        categoryDetails.size
                    ),
                    color = DarkBlue,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            }
            items(categoryDetails) { item ->
                BrandRow(
                    backgroundColorRes = item.scoreBackgroundColorRes,
                    brandId = item.id,
                    brandName = item.brandName,
                    brandParentCompanyName = item.parentCompanyName,
                    score = item.score
                )
                Divider(color = Color(0xFFECF0F1), thickness = 1.dp)
            }
        }
    }
}

@Composable
fun SortSelectionButton(
    sortOptions: List<SortOption>,
    onSortSelected: (SortOption) -> Unit,
    currentSortOption: SortOption
) {
    val color = Color(0xFF0652DD)

    Box {
        var sortPopupOpen by remember { mutableStateOf(false) }

        OutlinedButton(
            onClick = { sortPopupOpen = true },
            border = BorderStroke(1.dp, color = color),
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = color.copy(alpha = 0.1f)
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_sort),
                contentDescription = null,
                tint = color
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(id = R.string.category_detail_sort),
                color = color,
                fontSize = 17.sp
            )
        }

        DropdownMenu(
            modifier = Modifier.background(color = DarkBlue),
            offset = DpOffset((-20).dp, 0.dp),
            expanded = sortPopupOpen,
            onDismissRequest = { sortPopupOpen = false }
        ) {
            for (sort in sortOptions) {
                DropdownMenuItem(
                    onClick = {
                        onSortSelected(sort)
                        sortPopupOpen = false
                    }
                ) {
                    Text(
                        text = stringResource(id = sort.nameRes),
                        fontWeight = if (sort == currentSortOption) {
                            FontWeight.Bold
                        } else {
                            FontWeight.Normal
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryDetailTopBar(
    @StringRes titleRes: Int,
    onBack: () -> Unit,
    onSuggestBrand: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(Color.White)
            .height(56.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterVertically)
                .clickable { onBack() },
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            tint = DarkBlue
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f),
            text = stringResource(id = titleRes),
            color = TextDark,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Icon(
            modifier = Modifier
                .padding(16.dp)
                .clickable { onSuggestBrand() },
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = null,
            tint = DarkBlue
        )
    }
}

@Composable
fun BrandRow(
    @ColorRes backgroundColorRes: Int,
    brandId: Int,
    brandName: String,
    brandParentCompanyName: String,
    score: Int
) {
    Row(
        modifier = Modifier
            .clickable(onClick = {})
            .padding(16.dp)
    ) {
        BrandDetail(
            modifier = Modifier.weight(1f),
            brandName = brandName,
            brandParentCompanyName = brandParentCompanyName
        )
        ScoreBox(
            backgroundColorRes = backgroundColorRes,
            score = score
        )
    }
}

@Composable
fun BrandDetail(
    modifier: Modifier,
    brandName: String,
    brandParentCompanyName: String
) {
    Column(modifier = modifier) {
        Text(
            text = brandName,
            color = TextDark,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            lineHeight = 55.sp
        )
        Text(
            text = brandParentCompanyName,
            color = Gray,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp
        )
    }
}

@Composable
fun ScoreBox(
    @ColorRes backgroundColorRes: Int,
    score: Int
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(
                color = colorResource(id = backgroundColorRes),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
    ) {
        Text(
            text = stringResource(id = R.string.category_detail_score, score),
            color = Color.White,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp
        )
    }
}

@Preview
@Composable
fun CenteredTopBarPreview() {
    CategoryDetailTopBar(
        titleRes = R.string.discover_category_makeup,
        onBack = {},
        onSuggestBrand = {}
    )
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun BrandRowPreview() {
    BrandRow(
        brandId = 5,
        brandName = "Hawaiian Tropic",
        brandParentCompanyName = "Rossmann",
        backgroundColorRes = R.color.brand_score_dark_green,
        score = 7
    )
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun CategoryDetailListPreview() {
    DeneysizTheme {
        CategoryDetailScreen(
            categoryDetails = mutableListOf<CategoryDetailItemUiModel>().apply {
                repeat(5) {
                    add(
                        CategoryDetailItemUiModel(
                            id = 1,
                            brandName = "Hawaiian Tropic",
                            parentCompanyName = "Rossmann",
                            score = 7,
                            scoreBackgroundColorRes = R.color.brand_score_light_green
                        )
                    )
                }
            },
            onSortSelected = {},
            currentSortOption = SortOption.SCORE_DESC
        )
    }
}
