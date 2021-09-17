package com.deneyehayir.deneysiz.scene.component

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deneyehayir.deneysiz.R

@Composable
fun MainTopAppBar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    titleColor: Color,
    actions: @Composable RowScope.() -> Unit,
    backgroundColor: Color = Color.White,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = 0.dp
) = TopAppBar(
    modifier = modifier,
    title = {
        Text(
            text = stringResource(id = titleRes),
            color = titleColor,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
    },
    actions = actions,
    backgroundColor = backgroundColor,
    contentColor = contentColor,
    elevation = elevation
)

@Composable
fun TopAppBarWhoWeAreAction(
    color: Color,
    navigateToWhoWeAre: () -> Unit
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier.clickable { navigateToWhoWeAre() }
) {
    Text(
        text = stringResource(id = R.string.top_bar_action_who_we_are),
        color = color,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
    Spacer(modifier = Modifier.width(4.dp))
    Icon(
        painter = painterResource(id = R.drawable.ic_info),
        modifier = Modifier
            .align(Alignment.CenterVertically)
            .padding(end = 16.dp),
        contentDescription = null,
        tint = color
    )
}
