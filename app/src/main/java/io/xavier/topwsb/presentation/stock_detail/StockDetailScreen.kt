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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.xavier.topwsb.R
import io.xavier.topwsb.domain.mapper.toMap
import io.xavier.topwsb.presentation.common_composables.SectionTitle
import io.xavier.topwsb.presentation.stock_detail.components.SectionInfoItem
import io.xavier.topwsb.presentation.stock_detail.components.chart.ChartSection
import io.xavier.topwsb.presentation.stock_detail.components.comments.CommentsState
import io.xavier.topwsb.presentation.stock_detail.components.market_data.MarketDataState
import io.xavier.topwsb.presentation.theme.DarkBackground
import io.xavier.topwsb.presentation.theme.DarkBackgroundTranslucent
import io.xavier.topwsb.presentation.theme.DarkPrimaryText
import io.xavier.topwsb.presentation.theme.DarkSecondaryText

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

    val listState = rememberLazyListState()

    Scaffold(
        modifier = Modifier,
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
                    SectionTitle(title = "\$${viewModel.ticker}")
                }
            )
        }
    ) { innerPadding ->

        // Show progress indicator is market overview is loading
        if (state.marketDataState is MarketDataState.Loading) {
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
        if (state.marketDataState is MarketDataState.Error) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkBackground),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.marketDataState.message,
                    color = DarkSecondaryText
                )
            }

            return@Scaffold
        }

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
                    horizontalAlignment = Alignment.End
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
                    if (state.marketDataState is MarketDataState.Success) {
                        state.marketDataState.data.toMap().forEach {
                            SectionInfoItem(
                                name = it.key,
                                value = it.value,
                                showDivider = it.key != "52 Week Low"
                            )
                        }
                    }
                }
            }

            // Section title for comments.
            item {
                SectionTitle(
                    title = "Today's Comments",
                    modifier = Modifier.padding(defaultHorizontalPadding)
                )
            }

            if (state.commentsState is CommentsState.Success) {
                items(state.commentsState.comments) {
                    // TODO: filler replace
                    SectionInfoItem(name = "Comment", value = it.author, showDivider = true)
                }
            } else {
                item {
                    // TODO: Filler replace
                    Text(text = "Error")
                }
            }
        }
    }
}