package com.deneyehayir.deneysiz.ui.component.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deneyehayir.deneysiz.ui.theme.Orange
import com.deneyehayir.deneysiz.ui.theme.White1

@Composable
fun DeneysizButton(
    modifier: Modifier = Modifier,
    text: String,
    onDonationClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(42.dp)
            .padding(horizontal = 24.dp),
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
fun DeneysizButtonPreview() {
    DeneysizButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        text = "Bağış Yap",
        onDonationClick = {}
    )
}
