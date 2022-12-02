package io.xavier.topwsb.presentation.stock_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.xavier.topwsb.presentation.theme.DarkPrimaryText
import io.xavier.topwsb.presentation.theme.DarkSecondaryText
import io.xavier.topwsb.presentation.theme.defaultHorizontalPadding

@Composable
fun SectionInfoItem(
    name: String,
    value: String,
    showDivider: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = name,
            color = DarkSecondaryText,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = value,
            fontWeight = FontWeight.SemiBold,
            color = DarkPrimaryText,
            style = MaterialTheme.typography.bodyMedium
        )
    }

    if (showDivider) {
        Divider(
            modifier = Modifier
                .padding(horizontal = defaultHorizontalPadding)
                .alpha(.2f),
            color = DarkSecondaryText
        )
    }
}