package com.deneyehayir.deneysiz.scene.support

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.internal.extension.openWebPage
import com.deneyehayir.deneysiz.internal.util.rememberFlowWithLifecycle
import com.deneyehayir.deneysiz.scene.support.model.SupportActionUiModel
import com.deneyehayir.deneysiz.scene.support.model.SupportUiModel
import com.deneyehayir.deneysiz.scene.whoweare.model.SocialMediaPageUiModel
import com.deneyehayir.deneysiz.ui.theme.Blue
import com.deneyehayir.deneysiz.ui.theme.DarkBlue
import com.deneyehayir.deneysiz.ui.theme.DarkTextColor
import com.deneyehayir.deneysiz.ui.theme.Gray
import com.deneyehayir.deneysiz.ui.theme.Gray2
import com.deneyehayir.deneysiz.ui.theme.White0
import com.deneyehayir.deneysiz.ui.theme.White1

@Composable
fun SupportScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    val viewModel = hiltViewModel<SupportViewModel>()
    val viewState by rememberFlowWithLifecycle(viewModel.viewState)
        .collectAsState(initial = SupportViewState.Initial)
    val context = LocalContext.current

    Scaffold(
        modifier = modifier,
        topBar = { SupportTopBar(onBack = onBack) }
    ) { paddingValues ->
        SupportScreen(
            modifier = Modifier.padding(paddingValues),
            viewState = viewState,
            onExpandableToggleClick = { supportAction ->
                viewModel.updateExpandedState(supportAction)
            },
            onSocialMediaNavigation = { url ->
                context.openWebPage(url)
            }
        )
    }
}

@Composable
fun SupportScreen(
    modifier: Modifier = Modifier,
    viewState: SupportViewState,
    onExpandableToggleClick: (supportAction: SupportActionUiModel) -> Unit,
    onSocialMediaNavigation: (String) -> Unit
) {
    when {
        viewState.isLoading -> {}
        viewState.shouldShowError -> {}
        viewState.supportUiModel != null -> {
            SupportScreenContent(
                uiModel = viewState.supportUiModel,
                onExpandableToggleClick = onExpandableToggleClick,
                onSocialMediaNavigation = onSocialMediaNavigation
            )
        }
    }
}

@Composable
private fun SupportScreenContent(
    uiModel: SupportUiModel,
    onExpandableToggleClick: (supportAction: SupportActionUiModel) -> Unit,
    onSocialMediaNavigation: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White0)
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        SupportText(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.support_top_bar_title),
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        SupportText(
            modifier = Modifier.fillMaxWidth(),
            text = uiModel.description,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(24.dp))
        SupportActionContent(
            modifier = Modifier.fillMaxWidth(),
            supportActions = uiModel.supportActions,
            onExpandableToggleClick = onExpandableToggleClick,
            onSocialMediaNavigation = onSocialMediaNavigation
        )
    }
}

@Composable
fun SupportTopBar(
    onBack: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Icon(
            modifier = Modifier
                .padding(16.dp)
                .clickable { onBack() },
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            tint = DarkBlue
        )
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .shadow(elevation = 4.dp, shape = CircleShape)
                    .background(color = White1, shape = CircleShape)
                    .align(alignment = Alignment.Center)
            ) {
                Icon(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(alignment = Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_question_mark),
                    contentDescription = null,
                    tint = DarkBlue
                )
            }
        }
    }
}

@Composable
fun SupportText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        color = DarkTextColor,
        fontWeight = fontWeight,
        fontSize = fontSize,
        textAlign = textAlign
    )
}

@Composable
fun SupportActionContent(
    modifier: Modifier = Modifier,
    supportActions: List<SupportActionUiModel>,
    onExpandableToggleClick: (supportAction: SupportActionUiModel) -> Unit,
    onSocialMediaNavigation: (String) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        supportActions.forEach { supportAction ->
            SupportActionItemCard(
                modifier = Modifier.fillMaxWidth(),
                supportAction = supportAction,
                onExpandableToggleClick = onExpandableToggleClick,
                onSocialMediaNavigation = onSocialMediaNavigation
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SupportActionItemCard(
    modifier: Modifier = Modifier,
    supportAction: SupportActionUiModel,
    onExpandableToggleClick: (supportAction: SupportActionUiModel) -> Unit,
    onSocialMediaNavigation: (String) -> Unit
) {
    Card(
        modifier = modifier.clickable { onExpandableToggleClick(supportAction) },
        backgroundColor = White1,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            SupportActionItemHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                supportAction = supportAction,
                onExpandableToggleClick = onExpandableToggleClick
            )
            AnimatedVisibility(visible = supportAction.isExpanded) {
                Column {
                    SupportActionExtendedContent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 16.dp
                            ),
                        text = supportAction.description
                    )
                    if (supportAction.socialMediaPages.isNotEmpty()) {
                        SocialMediaPagesContent(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    bottom = 16.dp
                                ),
                            socialMediaPages = supportAction.socialMediaPages,
                            onSocialMediaNavigation = onSocialMediaNavigation
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SupportActionItemHeader(
    modifier: Modifier = Modifier,
    supportAction: SupportActionUiModel,
    onExpandableToggleClick: (supportAction: SupportActionUiModel) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = supportAction.title,
            color = DarkTextColor,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(
                    color = Gray2,
                    shape = CircleShape
                )
                .clickable { onExpandableToggleClick(supportAction) }
        ) {
            Icon(
                modifier = Modifier
                    .wrapContentSize()
                    .align(alignment = Alignment.Center),
                painter = painterResource(
                    id = if (supportAction.isExpanded) {
                        R.drawable.ic_expanded
                    } else {
                        R.drawable.ic_collapsed
                    }
                ),
                contentDescription = null
            )
        }
    }
}

@Composable
fun SupportActionExtendedContent(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        color = Gray,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
}

// duplicate composable. there are a lot of things that can be used in common in the project
@Composable
fun SocialMediaPagesContent(
    modifier: Modifier = Modifier,
    socialMediaPages: List<SocialMediaPageUiModel>,
    onSocialMediaNavigation: (String) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        socialMediaPages.forEach { socialMediaPage ->
            SocialMediaPageItem(
                modifier = Modifier
                    .width(62.dp)
                    .height(36.dp),
                socialMediaPage = socialMediaPage,
                onSocialMediaNavigation = { onSocialMediaNavigation(socialMediaPage.url) }
            )
        }
    }
}

@Composable
fun SocialMediaPageItem(
    modifier: Modifier = Modifier,
    socialMediaPage: SocialMediaPageUiModel,
    onSocialMediaNavigation: (String) -> Unit
) {
    Card(
        modifier = modifier,
        backgroundColor = Blue,
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        onClick = { onSocialMediaNavigation(socialMediaPage.url) }
    ) {
        Icon(
            modifier = Modifier.wrapContentSize(),
            painter = painterResource(id = socialMediaPage.iconResId),
            contentDescription = null
        )
    }
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun SupportTopBarPreview() {
    SupportTopBar(onBack = {})
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun SupportScreenContentPreview() {
    SupportScreenContent(
        uiModel = SupportUiModel(
            description = "Bize destek olmanın birden çok yolu var…",
            supportActions = listOf(
                SupportActionUiModel(
                    0,
                    "title1",
                    "description1",
                    socialMediaPages = listOf(),
                    true
                ),
                SupportActionUiModel(
                    0,
                    "title1",
                    "description1",
                    socialMediaPages = listOf(),
                    false
                ),
                SupportActionUiModel(
                    0,
                    "title1",
                    "description1",
                    socialMediaPages = listOf(),
                    false
                )
            )
        ),
        onExpandableToggleClick = {},
        onSocialMediaNavigation = {}
    )
}
