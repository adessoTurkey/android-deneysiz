package com.deneyehayir.deneysiz.scene.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.ui.theme.DarkTextColor
import com.deneyehayir.deneysiz.ui.theme.LightTextColor
import com.deneyehayir.deneysiz.ui.theme.Orange
import com.deneyehayir.deneysiz.ui.theme.Transparent
import com.deneyehayir.deneysiz.ui.theme.White1

@Composable
fun ErrorDialog(
    content: ErrorContentUiModel,
    onClose: () -> Unit
) {
    val headerSize = 96
    Dialog(onDismissRequest = { onClose() }) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            ErrorDialogContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = (headerSize / 2).dp
                    ),
                titleResId = content.titleRes,
                descriptionResId = content.descriptionRes,
                buttonTextResId = content.buttonTextRes,
                onButtonClick = onClose
            )
            ErrorDialogHeader(
                modifier = Modifier.size(headerSize.dp),
                iconResId = content.imageIconRes
            )
        }
    }
}

@Composable
fun ErrorDialogHeader(
    modifier: Modifier = Modifier,
    iconResId: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Transparent),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = modifier
                .shadow(elevation = 4.dp, shape = CircleShape)
                .background(color = LightTextColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = null
            )
        }
    }
}

@Composable
fun ErrorDialogContent(
    modifier: Modifier = Modifier,
    titleResId: Int,
    descriptionResId: Int,
    buttonTextResId: Int,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = modifier
            .background(color = LightTextColor, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 24.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 56.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = titleResId),
            color = DarkTextColor,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = descriptionResId),
            color = DarkTextColor,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(48.dp))
        ErrorDialogButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp),
            text = stringResource(id = buttonTextResId),
            onButtonClick = onButtonClick
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun ErrorDialogButton(
    modifier: Modifier = Modifier,
    text: String,
    onButtonClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = { onButtonClick() },
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

data class ErrorContentUiModel(
    @DrawableRes val imageIconRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    @StringRes val buttonTextRes: Int
) {

    companion object {
        val Default = ErrorContentUiModel(
            imageIconRes = R.drawable.ic_dialog_error,
            titleRes = R.string.error_dialog_title_error,
            descriptionRes = R.string.error_dialog_description_error,
            buttonTextRes = R.string.error_dialog_button_text,
        )
    }
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun ErrorDialogPreview() {
    ErrorDialog(
        content = ErrorContentUiModel.Default,
        onClose = {}
    )
}
