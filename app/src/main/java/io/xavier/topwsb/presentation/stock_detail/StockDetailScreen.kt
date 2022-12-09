@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package io.xavier.topwsb.presentation.stock_detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.xavier.topwsb.R
import io.xavier.topwsb.domain.mapper.toMap
import io.xavier.topwsb.presentation.common_composables.SectionTitle
import io.xavier.topwsb.presentation.custom_composables.LineChart
import io.xavier.topwsb.presentation.stock_detail.components.SectionInfoItem
import io.xavier.topwsb.presentation.stock_detail.components.SectionInfoItemSentiment
import io.xavier.topwsb.presentation.theme.*


/**
 * Screen that shows detail of a particular stock.
 *
 * @param viewModel View model for this screen
 * @param onBackPressed callback to NavHostController.popBackStack()
 */
@Suppress("OPT_IN_IS_NOT_ENABLED")
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
                    SectionTitle(title = "\$${state.stockOverview?.ticker}")
                }
            )
        }
    ) { innerPadding ->

        LazyColumn(
            state = listState,
            contentPadding = innerPadding
        ) {

            // Price percentage and chart
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(190.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    state.intradayData?.let {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(defaultHorizontalPadding),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Column {
                                // TODO: GET START PRICE AND CURRENT PRICE
                                Text(
                                    text = "Price: ",
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Start,
                                    color = DarkSecondaryText,
                                    maxLines = 1
                                )

                                Text(
                                    text = "**start price**",
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Start,
                                    color = DarkPrimaryText,
                                    fontWeight = FontWeight.Medium,
                                    maxLines = 1
                                )
                            }

                            Card(
                                modifier = Modifier
                                    .sizeIn(minWidth = 72.dp),
                                shape = MaterialTheme.shapes.small,
                                colors = CardDefaults.cardColors(
                                    // TODO: SET TREND COLOR
                                    containerColor = PositiveTrend,
                                    contentColor = Color.White
                                )
                            ) {
                                // TODO: PRICE CHANGE PERCENTAGE
                                Text(
                                    text = "**price change %**",
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp, vertical = 1.dp)
                                        .align(Alignment.End),
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.End,
                                    maxLines = 1
                                )
                            }
                        }

                        Spacer(modifier = Modifier.size(16.dp))

                        AnimatedVisibility(
                            // TODO: IS LINE CHART VISIBLE
                            visible = true,
                            exit = ExitTransition.None
                        ) {
                            LineChart(
                                data = state.intradayData, graphColor = , showDashedLine = )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))

                SectionTitle(
                    title = "Overview",
                    modifier = Modifier.padding(start = 12.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

            item {

                Column(
                    modifier = Modifier
                        .padding(horizontal = defaultHorizontalPadding)
                        .background(
                            color = DarkBackgroundTranslucent,
                            shape = MaterialTheme.shapes.large
                        )
                ) {
                    when (state.stockOverviewLoading) {
                        true -> Text(text = "Loading data")
                        false -> {
                            state.stockOverview?.let { stockDetail ->
                                stockDetail.toMap().forEach {
                                    SectionInfoItem(
                                        name = it.key,
                                        value = it.value,
                                        showDivider = true
                                    )
                                }

                                // Show WSB sentiment.
                                SectionInfoItemSentiment(
                                    sentiment = viewModel.sentiment.value,
                                    showDivider = false
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}