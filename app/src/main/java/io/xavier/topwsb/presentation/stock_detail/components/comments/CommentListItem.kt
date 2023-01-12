package io.xavier.topwsb.presentation.stock_detail.components.comments

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import io.xavier.topwsb.domain.model.WsbComment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentListItem(
    comment: WsbComment
) {
    ListItem(
        headlineText = {
            Text(
                text = comment.author,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1
            )
        },
        supportingText = {
            Text(
                text = comment.text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Medium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    )

    Divider()
}