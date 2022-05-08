package com.deneyehayir.deneysiz.scene.faq

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.internal.extension.openWebPage
import com.deneyehayir.deneysiz.internal.util.rememberFlowWithLifecycle
import com.deneyehayir.deneysiz.scene.faq.model.FaqItemLinkUiModel
import com.deneyehayir.deneysiz.ui.theme.DarkBlue
import com.deneyehayir.deneysiz.ui.theme.DarkTextColor
import com.deneyehayir.deneysiz.ui.theme.Orange
import com.deneyehayir.deneysiz.ui.theme.White0

@Composable
fun FaqScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    val viewModel = hiltViewModel<FaqViewModel>()
    val viewState by rememberFlowWithLifecycle(viewModel.viewState)
        .collectAsState(initial = FaqViewState.Initial)
    val context = LocalContext.current

    Scaffold(
        modifier = modifier,
        topBar = {
            Icon(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onBack() },
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = DarkBlue
            )
        }
    ) {
        FaqScreen(
            viewState = viewState,
            onBrowserNavigate = { url ->
                context.openWebPage(url)
            }
        )
    }
}

@Composable
private fun FaqScreen(
    viewState: FaqViewState,
    onBrowserNavigate: (String) -> Unit
) {
    when {
        viewState.isLoading -> {}
        viewState.shouldShowError -> {}
        viewState.faqItemUiModel != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = White0)
                    .verticalScroll(rememberScrollState())
            ) {
                FaqTitle(name = viewState.faqItemUiModel.title)
                FaqDescription(description = viewState.faqItemUiModel.description)
                FaqLinks(
                    links = viewState.faqItemUiModel.links,
                    onBrowserNavigate = onBrowserNavigate
                )
                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }
}

@Composable
fun FaqTitle(name: String) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            color = DarkTextColor,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )
    }
}

@Composable
fun FaqDescription(description: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        text = description,
        color = DarkTextColor,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
}

@Composable
fun FaqLinks(
    modifier: Modifier = Modifier,
    links: List<FaqItemLinkUiModel>,
    onBrowserNavigate: (String) -> Unit
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        links.forEach { link ->
            Text(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .clickable { onBrowserNavigate(link.url) },
                text = link.displayName,
                color = Orange,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
        }
    }
}
