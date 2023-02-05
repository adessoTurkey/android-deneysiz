package com.deneyehayir.deneysiz.scene.whoweare

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.internal.extension.navigateToEmailApp
import com.deneyehayir.deneysiz.internal.extension.openWebPage
import com.deneyehayir.deneysiz.internal.util.rememberFlowWithLifecycle
import com.deneyehayir.deneysiz.scene.whoweare.model.SocialMediaPageUiModel
import com.deneyehayir.deneysiz.scene.whoweare.model.WhoWeAreSupportActionUiModel
import com.deneyehayir.deneysiz.scene.whoweare.model.WhoWeAreUiModel
import com.deneyehayir.deneysiz.ui.theme.Blue
import com.deneyehayir.deneysiz.ui.theme.DarkBlue
import com.deneyehayir.deneysiz.ui.theme.DarkTextColor
import com.deneyehayir.deneysiz.ui.theme.Orange
import com.deneyehayir.deneysiz.ui.theme.White0
import com.deneyehayir.deneysiz.ui.theme.White1

@Composable
fun WhoWeAreScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onSupportNavigation: () -> Unit,
    onDonationNavigation: () -> Unit
) {
    val viewModel = hiltViewModel<WhoWeAreViewModel>()
    val viewState by rememberFlowWithLifecycle(viewModel.viewState)
        .collectAsState(initial = WhoWeAreViewState.Initial)
    val context = LocalContext.current

    Scaffold(
        modifier = modifier,
        topBar = { WhoWeAreTopBar(onBack = onBack) }
    ) { paddingValues ->
        WhoWeAreScreen(
            modifier = Modifier.padding(paddingValues),
            viewState = viewState,
            onBrowserNavigate = { url ->
                context.openWebPage(url)
            },
            onContactNavigation = {
                context.navigateToEmailApp(
                    mailAddressRes = R.string.who_we_are_support_mail_address,
                    subjectRes = R.string.who_we_are_support_mail_subject
                )
            },
            onSupportNavigation = onSupportNavigation,
            onDonationNavigation = onDonationNavigation,
            onSocialMediaNavigation = { url ->
                context.openWebPage(url)
            }
        )
    }
}

@Composable
private fun WhoWeAreScreen(
    modifier: Modifier = Modifier,
    viewState: WhoWeAreViewState,
    onBrowserNavigate: (String) -> Unit,
    onContactNavigation: () -> Unit,
    onSupportNavigation: () -> Unit,
    onDonationNavigation: () -> Unit,
    onSocialMediaNavigation: (String) -> Unit
) {
    when {
        viewState.isLoading -> {}
        viewState.shouldShowError -> {}
        viewState.whoWeAreUiModel != null -> {
            WhoWeAreScreenContent(
                uiModel = viewState.whoWeAreUiModel,
                onBrowserNavigate = onBrowserNavigate,
                onContactNavigation = onContactNavigation,
                onSupportNavigation = onSupportNavigation,
                onDonationNavigation = onDonationNavigation,
                onSocialMediaNavigation = onSocialMediaNavigation
            )
        }
    }
}

@Composable
private fun WhoWeAreScreenContent(
    uiModel: WhoWeAreUiModel,
    onBrowserNavigate: (String) -> Unit,
    onContactNavigation: () -> Unit,
    onSupportNavigation: () -> Unit,
    onDonationNavigation: () -> Unit,
    onSocialMediaNavigation: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White0)
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        WhoWeAreText(
            text = uiModel.description,
            fontWeight = FontWeight.Normal
        )
        WhoWeAreLink(
            displayUrl = uiModel.displayUrl,
            onBrowserNavigate = { onBrowserNavigate(uiModel.url) }
        )
        Spacer(modifier = Modifier.height(32.dp))
        uiModel.supportActions.forEach { supportAction ->
            WhoWeAreSupportActionItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                supportAction = supportAction,
                onContactNavigation = { onContactNavigation() },
                onSupportNavigation = onSupportNavigation,
                onDonationNavigation = onDonationNavigation
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        WhoWeAreText(
            text = uiModel.footerDescription,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(16.dp))
        SocialMediaPagesContent(
            socialMediaPages = uiModel.socialMediaPages,
            onSocialMediaNavigation = onSocialMediaNavigation
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun WhoWeAreTopBar(
    onBack: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Icon(
            modifier = Modifier
                .padding(16.dp)
                .clickable { onBack() },
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            tint = DarkBlue
        )
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = stringResource(id = R.string.top_bar_title_who_we_are),
            color = DarkTextColor,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp
        )
    }
}

@Composable
fun WhoWeAreText(
    text: String,
    fontWeight: FontWeight
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        color = DarkTextColor,
        fontWeight = fontWeight,
        fontSize = 14.sp
    )
}

@Composable
fun WhoWeAreLink(
    displayUrl: String,
    onBrowserNavigate: () -> Unit
) {
    Text(
        modifier = Modifier.clickable { onBrowserNavigate() },
        text = displayUrl,
        color = Orange,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
}

@Composable
fun WhoWeAreSupportActionItem(
    modifier: Modifier = Modifier,
    supportAction: WhoWeAreSupportActionUiModel,
    onContactNavigation: () -> Unit,
    onSupportNavigation: () -> Unit,
    onDonationNavigation: () -> Unit

) {
    Card(
        modifier = modifier,
        backgroundColor = White1,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        onClick = {
            when (supportAction.id) {
                0 -> onContactNavigation()
                1 -> onSupportNavigation()
                2 -> onDonationNavigation()
            }
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = painterResource(id = supportAction.iconResId),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = supportAction.text,
                color = DarkTextColor,
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}

@Composable
fun SocialMediaPagesContent(
    socialMediaPages: List<SocialMediaPageUiModel>,
    onSocialMediaNavigation: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        socialMediaPages.forEach { socialMediaPage ->
            SocialMediaPageItem(
                modifier = Modifier
                    .width(69.dp)
                    .height(40.dp),
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
