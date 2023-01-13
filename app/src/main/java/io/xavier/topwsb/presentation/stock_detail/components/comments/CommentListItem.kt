package io.xavier.topwsb.presentation.stock_detail.components.comments

import android.text.Html
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import io.xavier.topwsb.domain.model.WsbComment
import java.text.DateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentListItem(
    comment: WsbComment,
    formatter: DateFormat
) {
    ListItem(
        headlineText = {
            Text(
                text = formatter.format(
                    Date(comment.createdUtc)
                ),
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1
            )
        },
        supportingText = {
            Text(
                text = Html.fromHtml(comment.text, 0).toString(),
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