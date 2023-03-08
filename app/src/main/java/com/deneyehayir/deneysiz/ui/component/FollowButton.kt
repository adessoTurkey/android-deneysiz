package com.deneyehayir.deneysiz.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.ui.theme.Blue
import com.deneyehayir.deneysiz.ui.theme.DarkTextColor
import com.deneyehayir.deneysiz.ui.theme.DeneysizTheme

@Composable
fun FollowButtonItem(
    favoriteStatus: Boolean,
    onFollowClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clickable {
                onFollowClick()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(
                    id = if (favoriteStatus) R.drawable.ic_bookmark_favorite else R.drawable.ic_bookmark // ktlint-disable max-line-length
                ),
                contentDescription = null,
                tint = Blue
            )
            Text(
                modifier = Modifier.padding(start = 21.dp),
                text = stringResource(id = R.string.follow_button_item_text),
                color = DarkTextColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FollowButtonItemPreview() {
    DeneysizTheme {
        FollowButtonItem(favoriteStatus = true, onFollowClick = {})
    }
}
