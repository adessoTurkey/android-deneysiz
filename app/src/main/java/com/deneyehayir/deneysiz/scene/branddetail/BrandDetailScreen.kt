package com.deneyehayir.deneysiz.scene.branddetail

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.domain.model.CertificateType
import com.deneyehayir.deneysiz.internal.extension.navigateToEmailApp
import com.deneyehayir.deneysiz.internal.util.rememberFlowWithLifecycle
import com.deneyehayir.deneysiz.scene.branddetail.model.BrandDetailParentCompanyUiModel
import com.deneyehayir.deneysiz.scene.branddetail.model.BrandDetailUiModel
import com.deneyehayir.deneysiz.scene.branddetail.model.BrandInfoUiModel
import com.deneyehayir.deneysiz.scene.branddetail.model.BrandScoreType
import com.deneyehayir.deneysiz.scene.branddetail.model.CertificateUiModel
import com.deneyehayir.deneysiz.scene.branddetail.model.ScoreUiModel
import com.deneyehayir.deneysiz.ui.theme.BlueTextColor
import com.deneyehayir.deneysiz.ui.theme.DarkBlue
import com.deneyehayir.deneysiz.ui.theme.DarkTextColor
import com.deneyehayir.deneysiz.ui.theme.DividerColor
import com.deneyehayir.deneysiz.ui.theme.Gray
import com.deneyehayir.deneysiz.ui.theme.LightTextColor
import com.deneyehayir.deneysiz.ui.theme.Transparent
import com.deneyehayir.deneysiz.ui.theme.White0
import com.deneyehayir.deneysiz.ui.theme.White1
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BrandDetailScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onNavigateCertificateDetail: (String) -> Unit
) {
    val viewModel = hiltViewModel<BrandDetailViewModel>()
    val viewState by rememberFlowWithLifecycle(viewModel.brandDetailViewState)
        .collectAsState(initial = BrandDetailViewState.Initial)
    val context = LocalContext.current
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        topBar = {
            BrandDetailTopBar(
                onBack = onBack,
                onReport = {
                    scope.launch { bottomSheetState.show() }
                }
            )
        }
    ) {
        BrandDetailScreen(
            bottomSheetState = bottomSheetState,
            scope = scope,
            viewState = viewState,
            onNavigateToEmailApp = {
                context.navigateToEmailApp(
                    mailAddressRes = R.string.brand_detail_support_mail_address,
                    subjectRes = R.string.brand_detail_support_mail_subject
                )
            },
            onScoreDetail = viewModel.onScoreDetail,
            onNavigateCertificateDetail = onNavigateCertificateDetail
        )
        Spacer(modifier = Modifier.size(24.dp))
    }
}

@Composable
private fun BrandDetailScreen(
    bottomSheetState: ModalBottomSheetState,
    scope: CoroutineScope,
    viewState: BrandDetailViewState,
    onNavigateToEmailApp: () -> Unit,
    onScoreDetail: () -> Unit,
    onNavigateCertificateDetail: (String) -> Unit
) {
    when {
        viewState.isLoading -> {}
        viewState.shouldShowError -> {}
        viewState.brandDetailData != null -> {
            ModalBottomSheetSupportLayout(
                state = bottomSheetState,
                scope = scope,
                onNavigateToEmailApp = onNavigateToEmailApp,
                content = {
                    BrandDetailScreenContent(
                        brandDetailData = viewState.brandDetailData,
                        shouldScoreDetailDialogShown = viewState.shouldScoreDetailDialogShown,
                        onScoreDetail = onScoreDetail,
                        onNavigateCertificateDetail = onNavigateCertificateDetail
                    )
                }
            )
        }
    }
}

@Composable
private fun BrandDetailScreenContent(
    brandDetailData: BrandDetailUiModel,
    shouldScoreDetailDialogShown: Boolean,
    onScoreDetail: () -> Unit,
    onNavigateCertificateDetail: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White0)
            .verticalScroll(rememberScrollState())
    ) {
        BrandStickyTopContent(
            brandName = brandDetailData.brandName,
            parentCompany = brandDetailData.parentCompany,
            scoreData = brandDetailData.scoreData,
            onScoreDetail = onScoreDetail
        )
        if (shouldScoreDetailDialogShown) {
            ScoreDetailDialog(
                scoreData = brandDetailData.scoreData,
                onClose = onScoreDetail
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        CertificatesContent(
            certificateList = brandDetailData.certificateList,
            onNavigateCertificateDetail = onNavigateCertificateDetail
        )
        Spacer(modifier = Modifier.height(24.dp))
        BrandInfoContent(
            brandInfoList = brandDetailData.brandInfoList
        )
        DescriptionText(description = brandDetailData.description)
        UpdateDateText(date = brandDetailData.updateDate)
    }
}

@Composable
fun BrandDetailTopBar(
    onBack: () -> Unit,
    onReport: () -> Unit
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

        Spacer(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
        )

        Icon(
            modifier = Modifier
                .padding(16.dp)
                .clickable { onReport() },
            painter = painterResource(id = R.drawable.ic_alert),
            contentDescription = null,
            tint = DarkBlue
        )
    }
}

@Composable
fun BrandStickyTopContent(
    modifier: Modifier = Modifier,
    brandName: String,
    parentCompany: BrandDetailParentCompanyUiModel,
    scoreData: ScoreUiModel,
    onScoreDetail: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = brandName,
            color = DarkTextColor,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(30.dp))
        if (parentCompany.isAvailable) {
            Text(
                text = parentCompany.name,
                color = Gray,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        ScoreArea(
            modifier = Modifier.padding(paddingValues = PaddingValues(8.dp)),
            displayText = scoreData.scoreDisplayText,
            backgroundColor = scoreData.backgroundColor,
            shouldDisplayDetail = true,
            onScoreDetail = onScoreDetail
        )
    }
}

@Composable
fun ScoreArea(
    modifier: Modifier = Modifier,
    displayText: String,
    backgroundColor: Color,
    shouldDisplayDetail: Boolean = false,
    onScoreDetail: (() -> Unit)? = null
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .then(if (onScoreDetail != null) Modifier.clickable { onScoreDetail() } else Modifier)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .then(modifier)
    ) {
        Text(
            text = displayText,
            color = White0,
            fontWeight = if (shouldDisplayDetail) FontWeight.Normal else FontWeight.Medium,
            fontSize = 17.sp
        )
        if (shouldDisplayDetail) {
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_info_filled),
                contentDescription = null,
                tint = White0
            )
        }
    }
}

@Composable
fun CertificatesContent(
    certificateList: List<CertificateUiModel>,
    onNavigateCertificateDetail: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        certificateList.forEach { certificate ->
            CertificateItem(
                modifier = Modifier.size(75.dp),
                certificate = certificate,
                onNavigateCertificateDetail = onNavigateCertificateDetail
            )
        }
    }
}

@Composable
fun CertificateItem(
    modifier: Modifier = Modifier,
    certificate: CertificateUiModel,
    onNavigateCertificateDetail: (String) -> Unit
) {
    Card(
        modifier = modifier
            .background(color = White1)
            .clickable { onNavigateCertificateDetail(certificate.certificate.type) },
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

@Composable
fun BrandInfoContent(brandInfoList: List<BrandInfoUiModel>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        brandInfoList.forEach { brandInfo ->
            BrandInfoItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                brandInfo = brandInfo
            )
        }
    }
}

@Composable
fun BrandInfoItem(
    modifier: Modifier = Modifier,
    brandInfo: BrandInfoUiModel
) {
    Card(
        modifier = modifier.background(
            color = LightTextColor,
            shape = RoundedCornerShape(8.dp)
        ),
        elevation = 4.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = painterResource(id = brandInfo.iconRes),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(id = brandInfo.titleStringRes),
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
fun DescriptionText(description: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 16.dp),
        text = description,
        color = DarkTextColor,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    )
}

@Composable
fun UpdateDateText(date: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 16.dp),
        text = stringResource(id = R.string.brand_detail_update_date, date),
        color = Gray,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
    )
}

@Composable
fun ScoreDetailDialog(
    scoreData: ScoreUiModel,
    onClose: () -> Unit
) {
    val headerSize = 96
    Dialog(onDismissRequest = { onClose() }) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            ScoreDetailDialogContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = (headerSize / 2).dp
                    ),
                scoreData = scoreData,
                onClose = onClose
            )
            ScoreDetailDialogHeader(
                modifier = Modifier.size(headerSize.dp),
                onClose = onClose
            )
        }
    }
}

@Composable
fun ScoreDetailDialogHeader(
    modifier: Modifier = Modifier,
    onClose: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Transparent)
            .clickable { onClose() },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = modifier
                .shadow(elevation = 4.dp, shape = CircleShape)
                .background(color = LightTextColor, shape = CircleShape)
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_dialog_score),
                contentDescription = null
            )
        }
    }
}

@Composable
fun ScoreDetailDialogContent(
    modifier: Modifier = Modifier,
    scoreData: ScoreUiModel,
    onClose: () -> Unit
) {
    Column(
        modifier = modifier
            .background(color = LightTextColor, shape = RoundedCornerShape(8.dp))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = null,
            tint = DarkBlue,
            modifier = Modifier
                .padding(top = 22.dp, end = 22.dp)
                .align(Alignment.End)
                .clickable { onClose() }
        )
        Spacer(modifier = Modifier.height(22.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ScoreArea(
                modifier = Modifier
                    .width(51.dp)
                    .height(24.dp),
                displayText = scoreData.scoreDisplayText,
                backgroundColor = scoreData.backgroundColor
            )
        }
        Text(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.brand_detail_pop_up_description),
            color = DarkTextColor,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        scoreData.popupItems.forEach { scoreType ->
            ScoreDialogInfoRow(brandScoreType = scoreType)
            Divider(
                color = DividerColor,
                thickness = 1.dp
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun ScoreDialogInfoRow(
    brandScoreType: BrandScoreType
) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = brandScoreType.titleRes),
                color = DarkTextColor,
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = brandScoreType.subtitleRes),
                color = Gray,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
        }
        Column {
            Spacer(modifier = Modifier.height(2.dp))
            ScoreArea(
                modifier = Modifier
                    .width(51.dp)
                    .height(24.dp),
                displayText = brandScoreType.scoreDisplayText,
                backgroundColor = brandScoreType.color
            )
        }
    }
}

@Composable
private fun ModalBottomSheetSupportLayout(
    state: ModalBottomSheetState,
    scope: CoroutineScope,
    onNavigateToEmailApp: () -> Unit,
    content: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        sheetState = state,
        sheetContent = {
            SupportBottomSheetContent(
                state = state,
                scope = scope,
                onNavigateToEmailApp = onNavigateToEmailApp
            )
        },
        content = content
    )
}

@Composable
fun SupportBottomSheetContent(
    modifier: Modifier = Modifier,
    state: ModalBottomSheetState,
    scope: CoroutineScope,
    onNavigateToEmailApp: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.brand_detail_support_approve),
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth()
                .clickable {
                    scope.launch { state.hide() }
                    onNavigateToEmailApp()
                },
            color = BlueTextColor,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Divider(
            color = DividerColor,
            thickness = 1.dp
        )
        Text(
            text = stringResource(id = R.string.brand_detail_support_deny),
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth()
                .clickable {
                    scope.launch { state.hide() }
                },
            color = BlueTextColor,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun BrandDetailTopBarPreview() {
    BrandDetailTopBar(
        {}, {}
    )
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun BrandStickyTopContentPreview() {
    BrandStickyTopContent(
        brandName = "Alterra",
        parentCompany = BrandDetailParentCompanyUiModel(
            isAvailable = true,
            name = "Dirk Rossmann",
            isSafe = true
        ),
        scoreData = ScoreUiModel(
            totalValue = 5,
            description = R.string.brand_detail_pop_up_description,
            popupItems = emptyList()
        ),
        onScoreDetail = {}
    )
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun CallCertificateListPreview() {
    CertificatesContent(
        certificateList = listOf(
            CertificateUiModel(CertificateType.LeapingBunny, true),
            CertificateUiModel(CertificateType.BeautyWithoutBunnies, true),
            CertificateUiModel(CertificateType.VLabel, false),
            CertificateUiModel(CertificateType.VeganSociety, false)
        ),
        onNavigateCertificateDetail = {}
    )
}

@Preview(backgroundColor = 0xFF0000, showBackground = true)
@Composable
fun BrandInfoListPreview() {
    BrandInfoContent(
        brandInfoList = listOf(
            BrandInfoUiModel(R.string.brand_detail_info_vegan_product, R.drawable.ic_positive),
            BrandInfoUiModel(R.string.brand_detail_info_china_offer, R.drawable.ic_positive),
            BrandInfoUiModel(R.string.brand_detail_info_parent_company_safe, R.drawable.ic_negative)
        )
    )
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun DescriptionTextPreview() {
    DescriptionText(
        description = "Dirk Rossmann’ın %40’ına sahip olan çatı firma A.S. Watson " +
            "şiketlerinden Chi-Med hayvanlar üzerinde test yapıyor"
    )
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun UpdateDateTextPreview() {
    UpdateDateText(date = "01.04.2021")
}

@Preview
@Composable
fun DialogHeaderDenemePreview() {
    ScoreDetailDialogHeader(onClose = {})
}
