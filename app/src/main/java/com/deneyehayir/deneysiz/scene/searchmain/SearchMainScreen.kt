package com.deneyehayir.deneysiz.scene.searchmain

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.scene.component.TopAppBarWhoWeAreAction
import com.deneyehayir.deneysiz.ui.theme.SearchTextInputContentColor
import com.deneyehayir.deneysiz.ui.theme.TextInputFieldBgColor
import com.deneyehayir.deneysiz.ui.theme.White1

@Composable
fun SearchMainRoute(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    onInputFieldClick: () -> Unit,
    navigateToWhoWeAre: () -> Unit
) {
    SearchMainScreen(
        modifier = modifier,
        scrollState = scrollState,
        onInputFieldClick = onInputFieldClick,
        navigateToWhoWeAre = navigateToWhoWeAre
    )
}

@Composable
fun SearchMainScreen(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    onInputFieldClick: () -> Unit,
    navigateToWhoWeAre: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.ic_search_bunny),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .align(Alignment.TopCenter),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = stringResource(id = R.string.top_bar_title_search),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = White1
                )

                TopAppBarWhoWeAreAction(
                    color = White1,
                    navigateToWhoWeAre = navigateToWhoWeAre
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 40.dp)
                .clickable {
                    onInputFieldClick.invoke()
                },
            shape = RoundedCornerShape(10.dp),
            backgroundColor = TextInputFieldBgColor
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    modifier = Modifier.weight(0.15f),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = SearchTextInputContentColor
                )
                Text(
                    modifier = Modifier.weight(0.85f),
                    text = stringResource(id = R.string.search_main_input_field_hint),
                    fontWeight = FontWeight.Medium,
                    fontSize = 17.sp,
                    color = SearchTextInputContentColor
                )
            }
        }
    }
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun SearchMainScreenPreview() {
    val scrollState = rememberScrollState()
    SearchMainScreen(scrollState = scrollState, onInputFieldClick = {}, navigateToWhoWeAre = {})
}
