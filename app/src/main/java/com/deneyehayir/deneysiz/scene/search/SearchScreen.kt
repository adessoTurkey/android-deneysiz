package com.deneyehayir.deneysiz.scene.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.internal.extension.navigateToEmailApp
import com.deneyehayir.deneysiz.internal.util.rememberFlowWithLifecycle
import com.deneyehayir.deneysiz.scene.categorydetail.BrandRow
import com.deneyehayir.deneysiz.scene.categorydetail.model.CategoryDetailItemUiModel
import com.deneyehayir.deneysiz.scene.component.ErrorDialog
import com.deneyehayir.deneysiz.scene.component.LoadingScreen
import com.deneyehayir.deneysiz.ui.component.DeneysizButton
import com.deneyehayir.deneysiz.ui.component.SearchErrorItem
import com.deneyehayir.deneysiz.ui.component.SearchTextInputItem
import com.deneyehayir.deneysiz.ui.theme.DarkTextColor
import com.deneyehayir.deneysiz.ui.theme.SearchDetailTextButtonColor
import kotlinx.coroutines.CoroutineScope

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navigateToBrandDetail: (Int) -> Unit
) {
    val viewState by rememberFlowWithLifecycle(viewModel.uiState).collectAsState(
        initial = SearchUiState.Initial
    )
    val queryStringState by rememberFlowWithLifecycle(viewModel.queryFlowText).collectAsState(
        initial = ""
    )
    val context = LocalContext.current
    SearchScreen(
        modifier = modifier,
        viewState = viewState,
        queryString = queryStringState,
        coroutineScope = coroutineScope,
        onSearchDetailUiEvent = viewModel::handleUiEvents,
        navigateToBrandDetail = navigateToBrandDetail,
        onFollowClick = viewModel::handleFollowClick,
        onSuggestBrandClick = {
            context.navigateToEmailApp(
                mailAddressRes = R.string.search_suggest_brand_mail_address,
                subjectRes = R.string.search_suggest_brand_mail_subject
            )
        }
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewState: SearchUiState,
    queryString: String,
    coroutineScope: CoroutineScope,
    onSearchDetailUiEvent: (SearchUiEvents) -> Unit,
    navigateToBrandDetail: (Int) -> Unit,
    onSuggestBrandClick: () -> Unit,
    onFollowClick: (CategoryDetailItemUiModel) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SearchTextInputItem(
                modifier = Modifier.weight(0.8f),
                textValue = queryString,
                onValueChange = {
                    onSearchDetailUiEvent.invoke(SearchUiEvents.QueryTextChange(it))
                }
            )

            TextButton(
                modifier = Modifier.weight(0.2f),
                onClick = {
                    onSearchDetailUiEvent.invoke(SearchUiEvents.CancelButtonClick)
                }
            ) {
                Text(
                    text = stringResource(
                        id = com.deneyehayir.deneysiz.R.string.search_cancel_button_text
                    ),
                    color = SearchDetailTextButtonColor
                )
            }
        }

        when {
            viewState.isLoading -> {
                LoadingScreen()
            }
            viewState.errorContent != null -> {
                ErrorDialog(
                    content = viewState.errorContent,
                    onRetry = {
                        onSearchDetailUiEvent.invoke(SearchUiEvents.ErrorRetryClick)
                    },
                    onClose = {
                        onSearchDetailUiEvent.invoke(SearchUiEvents.ErrorCloseClick)
                    }
                )
            }
            viewState.isBrandNotFoundError -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SearchErrorItem(
                        modifier.padding(horizontal = 48.dp)
                    )
                    DeneysizButton(
                        modifier = Modifier.padding(top = 24.dp),
                        text = stringResource(id = R.string.error_dialog_button_text)
                    ) {
                        onSuggestBrandClick.invoke()
                    }
                }
            }
            viewState.data.isNotEmpty() -> {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    item {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(top = 16.dp),
                            text = stringResource(
                                id = R.string.search_result_info_text,
                                viewState.data.size
                            ),
                            color = DarkTextColor
                        )
                    }

                    items(viewState.data) { item ->
                        BrandRow(
                            item = item.toCategoryDetailModel(),
                            navigateToBrandDetail = navigateToBrandDetail,
                            onFollowClick = onFollowClick,
                            scope = coroutineScope
                        )
                    }
                }
            }
        }
    }
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun SearchDetailScreenPreview() {
    SearchScreen(
        viewState = SearchUiState.Initial,
        queryString = "",
        onSearchDetailUiEvent = {},
        navigateToBrandDetail = {},
        onSuggestBrandClick = {},
        coroutineScope = rememberCoroutineScope(),
        onFollowClick = {}
    )
}
