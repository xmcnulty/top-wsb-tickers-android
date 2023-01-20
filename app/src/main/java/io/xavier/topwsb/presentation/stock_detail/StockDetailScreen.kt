@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package io.xavier.topwsb.presentation.stock_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.xavier.topwsb.R
import io.xavier.topwsb.presentation.common_composables.SectionTitle
import io.xavier.topwsb.presentation.stock_detail.components.SectionInfoItem
import io.xavier.topwsb.presentation.stock_detail.components.chart.ChartSection
import io.xavier.topwsb.presentation.stock_detail.components.comments.CommentListItem
import io.xavier.topwsb.presentation.stock_detail.components.comments.CommentsState
import io.xavier.topwsb.presentation.theme.DarkBackground
import io.xavier.topwsb.presentation.theme.DarkBackgroundTranslucent
import io.xavier.topwsb.presentation.theme.DarkPrimaryText
import io.xavier.topwsb.presentation.theme.DarkSecondaryText
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.NumberFormat
import java.util.*

private val defaultHorizontalPadding: Dp = 16.dp

/**
 * Screen that shows detail of a particular stock.
 *
 * @param viewModel View model for this screen
 * @param onBackPressed callback to NavHostController.popBackStack()
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockDetailScreen(
    viewModel: StockDetailViewModel = hiltViewModel(),
    onBackPressed: () -> Boolean
) {
    val state = viewModel.state.value
    val errorEvents = viewModel.errorEvents
    val listState = rememberLazyListState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = snackbarHostState) {
        errorEvents.collect { errorMessage ->
            scope.launch {
                snackbarHostState.showSnackbar(
                    errorMessage,
                    withDismissAction = true,
                    actionLabel = "Retry"
                )
            }
        }
    }

    Scaffold(
        modifier = Modifier,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = { onBackPressed() }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Go Back",
                            colorFilter = ColorFilter.tint(DarkPrimaryText)
                        )
                    }
                },
                title = {
                    SectionTitle(title = "\$${viewModel.stock.ticker}")
                }
            )
        }
    ) { innerPadding ->

        // Show progress indicator is market overview is loading
        if (viewModel.isLoading()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkBackground),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = DarkPrimaryText)
            }

            return@Scaffold
        }

        // if the market data couldn't be loaded show a message
        /*if (viewModel.isError()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painterResource(id = R.drawable.outline_error),
                    contentDescription = "Error",
                    modifier = Modifier.requiredSize(64.dp),
                    tint = DarkBackgroundTranslucent
                )
            }

            return@Scaffold
        }*/

        LazyColumn(
            state = listState,
            contentPadding = innerPadding
        ) {

            // Chart
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(224.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ChartSection(chartState = state.chartState)
                }
            }

            // Section title "Overview"
            item {
                Spacer(modifier = Modifier.height(16.dp))

                SectionTitle(
                    title = "Overview",
                    modifier = Modifier.padding(
                        start = defaultHorizontalPadding,
                        bottom = defaultHorizontalPadding
                    )
                )
            }

            // Stock overview section items
            item {
                Column(
                    modifier = Modifier
                        .padding(horizontal = defaultHorizontalPadding)
                        .background(
                            color = DarkBackgroundTranslucent,
                            shape = MaterialTheme.shapes.large
                        )
                ) {
                    val stock = viewModel.stock
                    var numFormatter = NumberFormat.getInstance()

                    val detailsMap = mutableMapOf(
                        "Name" to stock.name,
                        "Type" to stock.type.string,
                        "Shares Outstanding" to numFormatter.format(stock.sharesOutstanding)
                    )

                    stock.marketCap?.let { marketCap ->
                        numFormatter = NumberFormat.getCurrencyInstance()
                        numFormatter.maximumFractionDigits = 0
                        detailsMap["Market Cap"] = numFormatter.format(marketCap.toLong())
                    }

                    detailsMap.forEach {
                        SectionInfoItem(
                            name = it.key,
                            value = it.value
                        )
                    }

                    SectionInfoItem(
                        name = "WSB Sentiment",
                        sentiment = viewModel.stock.sentiment
                    )
                }
            }

            // Section title for comments.
            item {
                SectionTitle(
                    title = "Recent Comments",
                    modifier = Modifier.padding(defaultHorizontalPadding)
                )
            }

            if (state.commentsState is CommentsState.Success) {
                // formats timestamps to HH:MM
                val formatter = DateFormat.getTimeInstance(DateFormat.SHORT)

                items(state.commentsState.comments) { comment ->
                    CommentListItem(comment, formatter)
                }
            } else {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(DarkBackground)
                        ,
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No comments",
                            color = DarkSecondaryText
                        )
                    }
                }
            }
        }
    }
}