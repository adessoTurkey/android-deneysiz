package com.deneyehayir.deneysiz.ui.component.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.ui.theme.GradientDark
import com.deneyehayir.deneysiz.ui.theme.SearchTextInputContentColor
import com.deneyehayir.deneysiz.ui.theme.TextInputFieldBgColor

@Composable
fun SearchTextInputItem(
    modifier: Modifier = Modifier,
    textValue: String,
    onValueChange: (String) -> Unit,
    onInputFieldClick: (() -> Unit)? = null
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onInputFieldClick?.invoke()
            },
        value = textValue,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null
            )
        },
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = TextInputFieldBgColor,
            placeholderColor = SearchTextInputContentColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = GradientDark,
            textColor = GradientDark
        ),
        placeholder = {
            Text(
                modifier = Modifier.padding(end = 5.dp),
                text = stringResource(id = R.string.search_input_field_hint),
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp
            )
        }
    )
}
