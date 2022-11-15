package com.deneyehayir.deneysiz.ui.component.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.ui.component.button.DeneysizButton
import com.deneyehayir.deneysiz.ui.theme.DarkTextColor
import com.deneyehayir.deneysiz.ui.theme.DeneysizTheme


@Composable
fun SearchErrorItem(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.ic_search_error), contentDescription = null)
        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = stringResource(id = R.string.search_error_title),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = DarkTextColor
        )
        Text(
            text = stringResource(id = R.string.search_error_description),
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            textAlign = TextAlign.Center,
            color = DarkTextColor
        )
    }

}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun SearchErrorItemPreview() {
    DeneysizTheme {
        SearchErrorItem()
    }
}
