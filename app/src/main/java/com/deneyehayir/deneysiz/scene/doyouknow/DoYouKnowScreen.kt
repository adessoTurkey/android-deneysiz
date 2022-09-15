package com.deneyehayir.deneysiz.scene.doyouknow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.domain.model.CertificateType
import com.deneyehayir.deneysiz.internal.util.rememberFlowWithLifecycle
import com.deneyehayir.deneysiz.scene.branddetail.model.CertificateUiModel
import com.deneyehayir.deneysiz.scene.component.MainTopAppBar
import com.deneyehayir.deneysiz.scene.component.TopAppBarWhoWeAreAction
import com.deneyehayir.deneysiz.scene.doyouknow.model.DoYouKnowUiModel
import com.deneyehayir.deneysiz.scene.doyouknow.model.FaqItemUiModel
import com.deneyehayir.deneysiz.ui.theme.Blue
import com.deneyehayir.deneysiz.ui.theme.DarkBlue
import com.deneyehayir.deneysiz.ui.theme.DarkTextColor
import com.deneyehayir.deneysiz.ui.theme.LightTextColor
import com.deneyehayir.deneysiz.ui.theme.White1

@Composable
fun DoYouKnowScreen(
    modifier: Modifier = Modifier,
    navigateToWhoWeAre: () -> Unit,
    onNavigateCertificateDetail: (Int) -> Unit,
    onNavigateFaqDetail: (Int) -> Unit
) {
    val viewModel = hiltViewModel<DoYouKnowViewModel>()
    val viewState by rememberFlowWithLifecycle(viewModel.viewState)
        .collectAsState(initial = DoYouKnowViewState.Initial)

    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopAppBar(
                titleRes = R.string.top_bar_title_do_you_know,
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
        DoYouKnowScreen(
            viewState = viewState,
            onNavigateCertificateDetail = onNavigateCertificateDetail,
            onNavigateFaqDetail = onNavigateFaqDetail
        )
    }
}

@Composable
private fun DoYouKnowScreen(
    viewState: DoYouKnowViewState,
    onNavigateCertificateDetail: (Int) -> Unit,
    onNavigateFaqDetail: (Int) -> Unit
) {
    when {
        viewState.isLoading -> {}
        viewState.shouldShowError -> {}
        viewState.doYouKnowUiModel != null -> {
            DoYouKnowScreenContent(
                uiModel = viewState.doYouKnowUiModel,
                onNavigateCertificateDetail = onNavigateCertificateDetail,
                onNavigateFaqDetail = onNavigateFaqDetail
            )
        }
    }
}

@Composable
private fun DoYouKnowScreenContent(
    uiModel: DoYouKnowUiModel,
    onNavigateCertificateDetail: (Int) -> Unit,
    onNavigateFaqDetail: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = uiModel.description,
                color = DarkTextColor,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        items(uiModel.windowedCertificates) { certificateRow ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                certificateRow.forEach { certificate ->
                    CertificateItem(
                        modifier = Modifier
                            .height(96.dp)
                            .fillParentMaxWidth(0.5f),
                        certificate = certificate,
                        onNavigateCertificateDetail = onNavigateCertificateDetail
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = uiModel.faqTitle,
                color = DarkTextColor,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp
            )
        }
        item { Spacer(modifier = Modifier.height(4.dp)) }
        item {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = uiModel.faqDescription,
                color = DarkTextColor,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        items(uiModel.faqList) { item ->
            FaqItem(
                faqItem = item,
                onNavigateFaqDetail = onNavigateFaqDetail
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item { Spacer(modifier = Modifier.height(128.dp)) }
    }
}

@Composable
fun FaqItem(
    modifier: Modifier = Modifier,
    faqItem: FaqItemUiModel,
    onNavigateFaqDetail: (Int) -> Unit
) {
    Card(
        modifier = modifier.background(color = LightTextColor),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        onClick = { onNavigateFaqDetail(faqItem.id) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = faqItem.title,
                color = DarkTextColor,
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp,
                modifier = Modifier.weight(1f)
            )
            Icon(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.ic_arrow),
                contentDescription = null,
                tint = DarkBlue
            )
        }
    }
}

@Composable
private fun CertificateItem(
    modifier: Modifier = Modifier,
    certificate: CertificateUiModel,
    onNavigateCertificateDetail: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .background(color = White1)
            .clickable { onNavigateCertificateDetail(certificate.certificate.id) },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Image(
            modifier = Modifier.padding(
                vertical = 12.dp,
                horizontal = 6.dp
            ),
            painter = painterResource(id = certificate.iconRes),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            alpha = certificate.opacity
        )
    }
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun DoYouKnowScreenContentPreview() {
    DoYouKnowScreenContent(
        uiModel = DoYouKnowUiModel(
            description = "description",
            certificates = listOf(
                CertificateUiModel(CertificateType.LeapingBunny, true),
                CertificateUiModel(CertificateType.BeautyWithoutBunnies, true),
                CertificateUiModel(CertificateType.VLabel, true),
                CertificateUiModel(CertificateType.VeganSociety, true)
            ),
            faqTitle = "Merak Edilenler",
            faqDescription = "description",
            faqList = listOf(
                FaqItemUiModel(id = 0, "Çin'de Satış ve Üretim"),
                FaqItemUiModel(id = 1, "Hayvan Deneyleri - Genel Bilgiler"),
                FaqItemUiModel(id = 2, "Vegan Ürünler"),
                FaqItemUiModel(id = 3, "Puanlama Sistemi"),
            )
        ),
        onNavigateCertificateDetail = {},
        onNavigateFaqDetail = {}
    )
}
