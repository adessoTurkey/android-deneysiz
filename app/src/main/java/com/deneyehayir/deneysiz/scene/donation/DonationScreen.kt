package com.deneyehayir.deneysiz.scene.donation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.internal.extension.openWebPage
import com.deneyehayir.deneysiz.internal.util.rememberFlowWithLifecycle
import com.deneyehayir.deneysiz.scene.donation.model.BankAccountUiModel
import com.deneyehayir.deneysiz.scene.donation.model.DonationUiModel
import com.deneyehayir.deneysiz.ui.theme.Blue
import com.deneyehayir.deneysiz.ui.theme.DarkBlue
import com.deneyehayir.deneysiz.ui.theme.DarkTextColor
import com.deneyehayir.deneysiz.ui.theme.Orange
import com.deneyehayir.deneysiz.ui.theme.White0
import com.deneyehayir.deneysiz.ui.theme.White1
import kotlinx.coroutines.launch

@Composable
fun DonationScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    val viewModel = hiltViewModel<DonationViewModel>()
    val viewState by rememberFlowWithLifecycle(viewModel.viewState)
        .collectAsState(initial = DonationViewState.Initial)
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    val scaffoldState = rememberScaffoldState()
    val snackBarCoroutineScope = rememberCoroutineScope()
    val snackBarMessage = stringResource(id = R.string.donation_copy_message)

    Scaffold(
        modifier = modifier,
        topBar = { DonationTopBar(onBack = onBack) },
        scaffoldState = scaffoldState
    ) { paddingValues ->
        DonationScreen(
            modifier = Modifier.padding(paddingValues),
            viewState = viewState,
            onDonationClick = { url ->
                context.openWebPage(url)
            },
            onCopyToClipboard = { iban ->
                clipboardManager.setText(
                    annotatedString = AnnotatedString(iban)
                )
                snackBarCoroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = snackBarMessage
                    )
                }
            }
        )
    }
}

@Composable
private fun DonationScreen(
    modifier: Modifier = Modifier,
    viewState: DonationViewState,
    onDonationClick: (String) -> Unit,
    onCopyToClipboard: (String) -> Unit
) {
    when {
        viewState.isLoading -> {}
        viewState.shouldShowError -> {}
        viewState.donationUiModel != null -> {
            DonationScreenContent(
                uiModel = viewState.donationUiModel,
                onDonationClick = onDonationClick,
                onCopyToClipboard = onCopyToClipboard
            )
        }
    }
}

@Composable
private fun DonationScreenContent(
    uiModel: DonationUiModel,
    onDonationClick: (String) -> Unit,
    onCopyToClipboard: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White0)
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        DonationText(
            modifier = Modifier.fillMaxWidth(),
            text = uiModel.description
        )
        Spacer(modifier = Modifier.height(24.dp))
        BankAccountInfoCard(
            modifier = Modifier,
            uiModel = uiModel.bankAccountUiModel,
            onCopyToClipboard = onCopyToClipboard
        )
        Spacer(modifier = Modifier.height(24.dp))
        DonationText(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally),
            text = stringResource(id = R.string.donation_option_or),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        DonationButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp),
            text = stringResource(id = R.string.donation_make_donation),
            onDonationClick = { onDonationClick(uiModel.donationUrl) }
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun DonationTopBar(
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
            text = stringResource(id = R.string.donation_top_bar_title),
            color = DarkTextColor,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp
        )
    }
}

@Composable
fun DonationText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        color = DarkTextColor,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        textAlign = textAlign
    )
}

@Composable
fun BankAccountInfoCard(
    modifier: Modifier = Modifier,
    uiModel: BankAccountUiModel,
    onCopyToClipboard: (String) -> Unit
) {
    Card(
        modifier = modifier,
        backgroundColor = White1,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
    ) {
        Column {
            BankAccountInfoHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 8.dp
                    ),
                title = uiModel.displayTitle,
                address = uiModel.address,
                iconResId = uiModel.iconResId
            )
            BankAccountInfoIban(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 8.dp,
                        bottom = 16.dp
                    ),
                text = uiModel.iban,
                onCopyToClipboard = onCopyToClipboard
            )
        }
    }
}

@Composable
fun BankAccountInfoHeader(
    modifier: Modifier = Modifier,
    title: String,
    address: String,
    @DrawableRes iconResId: Int
) {
    Row(
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = Blue
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = title,
                color = DarkTextColor,
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = address,
                color = Color.Gray,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun BankAccountInfoIban(
    modifier: Modifier = Modifier,
    text: String,
    onCopyToClipboard: (String) -> Unit
) {
    Box(
        modifier = modifier
            .clickable { onCopyToClipboard(text) }
            .background(
                color = Blue.copy(alpha = 0.05f),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(8.dp)
    ) {
        Text(
            modifier = Modifier.align(alignment = Alignment.CenterStart),
            text = text,
            color = Blue,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        )
        Icon(
            modifier = Modifier
                .align(alignment = Alignment.CenterEnd),
            painter = painterResource(id = R.drawable.ic_clipboard),
            contentDescription = null,
            tint = Blue,
        )
    }
}

@Composable
fun DonationButton(
    modifier: Modifier = Modifier,
    text: String,
    onDonationClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = { onDonationClick() },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Orange
        )
    ) {
        Text(
            text = text,
            color = White1,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun BankAccountInfoCardPreview() {
    BankAccountInfoCard(
        modifier = Modifier.padding(16.dp),
        uiModel = BankAccountUiModel(
            name = "Deneye Hayır Derneği",
            currency = "TL",
            address = "Ziraat Bankası Fethiye/Muğla Şubesi",
            iban = "TR71 0001 0002 0391 0906 4650 02",
            iconResId = R.drawable.ic_currency_try
        ),
        onCopyToClipboard = {}
    )
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun DonationButtonPreview() {
    DonationButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        text = "Bağış Yap",
        onDonationClick = {}
    )
}
