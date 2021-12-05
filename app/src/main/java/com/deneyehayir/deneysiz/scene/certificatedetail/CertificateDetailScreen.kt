package com.deneyehayir.deneysiz.scene.certificatedetail

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.internal.extension.openWebPage
import com.deneyehayir.deneysiz.internal.util.rememberFlowWithLifecycle
import com.deneyehayir.deneysiz.ui.theme.DarkBlue
import com.deneyehayir.deneysiz.ui.theme.DarkTextColor
import com.deneyehayir.deneysiz.ui.theme.LightTextColor
import com.deneyehayir.deneysiz.ui.theme.Orange
import com.deneyehayir.deneysiz.ui.theme.White0

@Composable
fun CertificateDetailScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    val viewModel = hiltViewModel<CertificateDetailViewModel>()
    val viewState by rememberFlowWithLifecycle(viewModel.viewState)
        .collectAsState(initial = CertificateDetailViewState.Initial)
    val context = LocalContext.current

    Scaffold(modifier = modifier) {
        CertificateDetailScreen(
            viewState = viewState,
            context = context,
            onBack = onBack
        )
    }
}

@Composable
private fun CertificateDetailScreen(
    viewState: CertificateDetailViewState,
    context: Context,
    onBack: () -> Unit
) {
    when {
        viewState.isLoading -> {}
        viewState.shouldShowError -> {}
        viewState.certificateDetail != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = White0)
                    .verticalScroll(rememberScrollState())
            ) {
                CertificateDetailHeader(
                    drawableRes = viewState.certificateDetail.iconRes,
                    onBack = onBack
                )
                Spacer(modifier = Modifier.height(8.dp))
                CertificateTitle(
                    name = viewState.certificateDetail.displayName
                )
                CertificateDescription(
                    description = stringResource(id = viewState.certificateDetail.descriptionRes)
                )
                CertificateLink(
                    displayUrl = viewState.certificateDetail.displayUrl,
                    onBrowserNavigate = {
                        context.openWebPage(url = viewState.certificateDetail.url)
                    }
                )
            }
        }
    }
}

@Composable
fun CertificateDetailHeader(
    drawableRes: Int,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            modifier = Modifier
                .padding(16.dp)
                .clickable { onBack() },
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            tint = DarkBlue
        )
        Box(
            modifier = Modifier
                .padding(top = 18.dp)
                .size(70.dp)
                .align(alignment = Alignment.Center)
                .shadow(elevation = 4.dp, shape = CircleShape)
                .background(color = LightTextColor, shape = CircleShape)
        ) {
            Image(
                modifier = Modifier.align(alignment = Alignment.Center),
                painter = painterResource(id = drawableRes),
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )
        }
    }
}

@Composable
fun CertificateTitle(name: String) {
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
fun CertificateDescription(description: String) {
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
fun CertificateLink(
    displayUrl: String,
    onBrowserNavigate: () -> Unit
) {
    Text(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .clickable { onBrowserNavigate() },
        text = displayUrl,
        color = Orange,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun CertificateDetailHeaderPreview() {
    CertificateDetailHeader(
        drawableRes = R.drawable.ic_certificate_v_label,
        onBack = {}
    )
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun CertificateTitlePreview() {
    CertificateTitle("Leaping Bunny")
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun CertificateDescriptionPreview() {
    CertificateDescription("Lorem ipsum")
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun CertificateLinkPreview() {
    CertificateLink(
        displayUrl = "leapingbunny.org",
        onBrowserNavigate = {}
    )
}
