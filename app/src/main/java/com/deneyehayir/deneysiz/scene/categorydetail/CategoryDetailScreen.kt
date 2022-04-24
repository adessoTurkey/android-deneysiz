package com.deneyehayir.deneysiz.scene.categorydetail

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.internal.extension.navigateToEmailApp
import com.deneyehayir.deneysiz.internal.util.rememberFlowWithLifecycle
import com.deneyehayir.deneysiz.scene.categorydetail.model.CategoryDetailItemUiModel
import com.deneyehayir.deneysiz.scene.categorydetail.model.SortOption
import com.deneyehayir.deneysiz.scene.component.ErrorDialog
import com.deneyehayir.deneysiz.scene.component.LoadingScreen
import com.deneyehayir.deneysiz.ui.theme.Blue
import com.deneyehayir.deneysiz.ui.theme.DarkBlue
import com.deneyehayir.deneysiz.ui.theme.DarkTextColor
import com.deneyehayir.deneysiz.ui.theme.DeneysizTheme
import com.deneyehayir.deneysiz.ui.theme.DividerColor
import com.deneyehayir.deneysiz.ui.theme.Gray
import com.deneyehayir.deneysiz.ui.theme.RowColor
import com.deneyehayir.deneysiz.ui.theme.ScoreDarkGreen
import com.deneyehayir.deneysiz.ui.theme.ScoreLightGreen
import com.deneyehayir.deneysiz.ui.theme.White0
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CategoryDetailScreen(
    modifier: Modifier = Modifier,
    onBrandDetail: (Int) -> Unit,
    onBack: () -> Unit
) {
    val viewModel = hiltViewModel<CategoryDetailViewModel>()
    val viewState by rememberFlowWithLifecycle(viewModel.categoryDetailViewState)
        .collectAsState(initial = CategoryDetailViewState.Initial)
    val context = LocalContext.current

    Scaffold(
        modifier = modifier,
        topBar = {
            CategoryDetailTopBar(
                titleRes = viewModel.categoryStringRes,
                onBack = onBack,
                onSuggestBrand = {
                    context.navigateToEmailApp(
                        mailAddressRes = R.string.support_mail_address,
                        subjectRes = R.string.support_subject_suggest
                    )
                }
            )
        }
    ) {
        CategoryDetailScreen(
            viewState = viewState,
            onBrandDetail = onBrandDetail,
            onSortSelected = { sortOption ->
                viewModel.onSortSelected(sortOption)
            },
            onRetry = viewModel.onRetry,
            onErrorClose = viewModel.onErrorClose
        )
    }
}

@Composable
private fun CategoryDetailScreen(
    viewState: CategoryDetailViewState,
    onBrandDetail: (Int) -> Unit,
    onSortSelected: (SortOption) -> Unit,
    onRetry: () -> Unit,
    onErrorClose: () -> Unit,
) {
    when {
        viewState.isLoading -> {
            LoadingScreen()
        }
        viewState.errorContent != null -> {
            ErrorDialog(
                content = viewState.errorContent,
                onRetry = onRetry,
                onClose = onErrorClose
            )
        }
        viewState.sortedBrandsList.isNotEmpty() -> {
            val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
            val scope = rememberCoroutineScope()

            ModalBottomSortSelection(
                state = state,
                scope = scope,
                sortOptions = viewState.sortingOptions,
                onSortSelected = onSortSelected,
                content = {
                    CategoryDetailList(
                        state = state,
                        scope = scope,
                        categoryDetails = viewState.sortedBrandsList,
                        currentSortOption = viewState.sortOption,
                        navigateToBrandDetail = onBrandDetail
                    )
                }
            )
        }
    }
}

@Composable
private fun CategoryDetailList(
    state: ModalBottomSheetState,
    scope: CoroutineScope,
    categoryDetails: List<CategoryDetailItemUiModel>,
    currentSortOption: SortOption,
    navigateToBrandDetail: (Int) -> Unit
) {
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
                SortSelectionButton(currentSortOption.sortLabelRes) {
                    scope.launch { state.show() }
                }
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
                backgroundColor = item.scoreBackgroundColor,
                brandId = item.id,
                brandName = item.brandName,
                brandParentCompanyName = item.parentCompanyName,
                score = item.score,
                navigateToBrandDetail = navigateToBrandDetail
            )
            ListDivider()
        }
    }
}

@Composable
private fun ModalBottomSortSelection(
    state: ModalBottomSheetState,
    scope: CoroutineScope,
    sortOptions: List<SortOption>,
    onSortSelected: (SortOption) -> Unit,
    content: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        sheetState = state,
        sheetContent = {
            BottomSheetContent(
                sortOptions = sortOptions,
                state = state,
                scope = scope,
                onSortSelected = onSortSelected
            )
        },
        content = content
    )
}

@Composable
fun BottomSheetContent(
    state: ModalBottomSheetState,
    scope: CoroutineScope,
    sortOptions: List<SortOption>,
    onSortSelected: (SortOption) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        itemsIndexed(sortOptions) { index, sort ->
            Text(
                text = stringResource(id = sort.nameRes),
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth()
                    .clickable {
                        onSortSelected(sort)
                        scope.launch { state.hide() }
                    },
                color = DarkTextColor,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (index < sortOptions.lastIndex) {
                ListDivider()
            }
        }
    }
}

@Composable
fun SortSelectionButton(
    sortLabelRes: Int,
    onSortButtonClicked: () -> Unit
) {
    OutlinedButton(
        onClick = { onSortButtonClicked() },
        border = BorderStroke(1.dp, color = Blue),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Blue.copy(alpha = 0.1f)
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_sort),
            contentDescription = null,
            tint = Blue
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = stringResource(
                id = R.string.category_detail_sort,
                stringResource(id = sortLabelRes)
            ),
            color = Blue,
            fontSize = 17.sp
        )
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
            .background(White0)
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
            color = DarkTextColor,
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
    backgroundColor: Color,
    brandId: Int,
    brandName: String,
    brandParentCompanyName: String,
    score: Int,
    navigateToBrandDetail: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(onClick = { navigateToBrandDetail(brandId) })
            .background(color = RowColor)
            .padding(16.dp)
    ) {
        BrandDetail(
            modifier = Modifier.weight(1f),
            brandName = brandName,
            brandParentCompanyName = brandParentCompanyName
        )
        ScoreBox(
            backgroundColor = backgroundColor,
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
            color = DarkTextColor,
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
    backgroundColor: Color,
    score: Int
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
    ) {
        Text(
            text = stringResource(id = R.string.category_detail_score, score),
            color = White0,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp
        )
    }
}

@Composable
private fun ListDivider() {
    Divider(
        color = DividerColor,
        thickness = 1.dp
    )
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
        backgroundColor = ScoreDarkGreen,
        score = 7,
        navigateToBrandDetail = {}
    )
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun CategoryDetailListPreview() {
    DeneysizTheme {
        CategoryDetailScreen(
            viewState = CategoryDetailViewState(
                isLoading = false,
                errorContent = null,
                brandsList = mutableListOf<CategoryDetailItemUiModel>().apply {
                    repeat(5) {
                        add(
                            CategoryDetailItemUiModel(
                                id = 1,
                                brandName = "Hawaiian Tropic",
                                parentCompanyName = "Rossmann",
                                score = 7,
                                scoreBackgroundColor = ScoreLightGreen
                            )
                        )
                    }
                },
                sortOption = SortOption.SCORE_DESC
            ),
            onBrandDetail = {},
            onSortSelected = {},
            onRetry = {},
            onErrorClose = {}
        )
    }
}
