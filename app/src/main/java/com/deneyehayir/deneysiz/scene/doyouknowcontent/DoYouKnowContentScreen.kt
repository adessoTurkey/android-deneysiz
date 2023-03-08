package com.deneyehayir.deneysiz.scene.doyouknowcontent

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.domain.model.MarkupDomainData
import com.deneyehayir.deneysiz.domain.model.MarkupType
import com.deneyehayir.deneysiz.domain.model.ParagraphDomainData
import com.deneyehayir.deneysiz.domain.model.ParagraphType
import com.deneyehayir.deneysiz.internal.extension.openWebPage
import com.deneyehayir.deneysiz.internal.util.rememberFlowWithLifecycle
import com.deneyehayir.deneysiz.scene.doyouknowcontent.model.DoYouKnowContentUiModel
import com.deneyehayir.deneysiz.ui.theme.DarkBlue
import com.deneyehayir.deneysiz.ui.theme.DarkTextColor
import com.deneyehayir.deneysiz.ui.theme.Orange

@Composable
fun DoYouKnowContentScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    val viewModel = hiltViewModel<DoYouKnowContentViewModel>()
    val viewState by rememberFlowWithLifecycle(viewModel.viewState)
        .collectAsState(initial = DoYouKnowContentViewState.Initial)
    val context = LocalContext.current

    Scaffold(modifier = modifier) { paddingValues ->
        DoYouKnowContentScreen(
            modifier = Modifier.padding(paddingValues),
            viewState = viewState,
            onLinkClick = { url ->
                context.openWebPage(url)
            },
            onBack = onBack
        )
    }
}

@Composable
private fun DoYouKnowContentScreen(
    modifier: Modifier = Modifier,
    viewState: DoYouKnowContentViewState,
    onLinkClick: (String) -> Unit,
    onBack: () -> Unit
) {
    when {
        viewState.isLoading -> {}
        viewState.shouldShowError -> {}
        viewState.contentUiModel != null -> {
            Box(modifier = Modifier.fillMaxSize()) {
                DoYouKnowContentDetail(
                    contentUiModel = viewState.contentUiModel,
                    onLinkClick = onLinkClick
                )
                DoYouKnowContentBackButton(onBack)
            }
        }
    }
}

@Composable
fun DoYouKnowContentDetail(
    contentUiModel: DoYouKnowContentUiModel,
    onLinkClick: (String) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        if (contentUiModel.titleIcons.isNotEmpty()) {
            item {
                DoYouKnowContentIcons(contentUiModel.titleIcons, true)
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
        }
        items(contentUiModel.paragraphs) { paragraph ->
            when (paragraph.type) {
                ParagraphType.Title -> DoYouKnowContentTitle(paragraph.toAnnotatedString())
                ParagraphType.Header -> DoYouKnowContentHeader(paragraph.toAnnotatedString())
                ParagraphType.Text -> DoYouKnowContentText(
                    paragraph.toAnnotatedString(),
                    paragraph.markups,
                    onLinkClick
                )
            }
        }
        if (contentUiModel.footerIcons.isNotEmpty()) {
            item {
                DoYouKnowContentIcons(contentUiModel.footerIcons, false)
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
        }
    }
}

@Composable
fun DoYouKnowContentBackButton(
    onBack: () -> Unit
) {
    Icon(
        modifier = Modifier
            .clickable { onBack() }
            .padding(16.dp)
            .size(24.dp),
        painter = painterResource(id = R.drawable.ic_back),
        contentDescription = null,
        tint = DarkBlue
    )
}

@Composable
fun DoYouKnowContentIcons(
    titleIcons: List<Int>,
    isTitle: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = if (isTitle) Arrangement.Center else Arrangement.Start
    ) {
        titleIcons.forEachIndexed { index, icon ->
            if (index != 0) Spacer(modifier = Modifier.size(6.dp))
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(shape = CircleShape)
            ) {
                Image(
                    modifier = Modifier.size(60.dp),
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                )
            }
            if (index != titleIcons.lastIndex) Spacer(
                modifier = Modifier.size(
                    6.dp
                )
            )
        }
    }
}

@Composable
fun DoYouKnowContentTitle(
    title: AnnotatedString
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = DarkTextColor,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )
    }
}

@Composable
fun DoYouKnowContentHeader(
    header: AnnotatedString
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 24.dp, vertical = 4.dp)
    ) {
        Text(
            text = header,
            color = DarkTextColor,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

@Composable
fun DoYouKnowContentText(
    annotatedString: AnnotatedString,
    markups: List<MarkupDomainData>,
    onLinkClick: (String) -> Unit,
) {
    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = 4.dp
            ),
        text = annotatedString,
        style = TextStyle(
            color = DarkTextColor,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        onClick = {
            annotatedString.spanStyles.forEachIndexed { index, annotatedRange ->
                if (annotatedRange.tag == TAG_URL_LINK && markups.getOrNull(index) != null) {
                    onLinkClick(markups[index].href.orEmpty())
                }
            }
        }
    )
}

private fun ParagraphDomainData.toAnnotatedString(): AnnotatedString {
    val styles: List<AnnotatedString.Range<SpanStyle>> = markups
        .map { it.toAnnotatedStringItem() }
    return AnnotatedString(text = content, spanStyles = styles)
}

fun MarkupDomainData.toAnnotatedStringItem(): AnnotatedString.Range<SpanStyle> {
    return when (this.type) {
        MarkupType.Link -> {
            AnnotatedString.Range(
                item = TextStyle(
                    color = Orange,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ).toSpanStyle(),
                start = start,
                end = end,
                tag = TAG_URL_LINK
            )
        }
        MarkupType.Bold -> {
            AnnotatedString.Range(
                item = TextStyle(
                    color = DarkTextColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ).toSpanStyle(),
                start = start,
                end = end
            )
        }
    }
}

private const val TAG_URL_LINK = "url_link"
