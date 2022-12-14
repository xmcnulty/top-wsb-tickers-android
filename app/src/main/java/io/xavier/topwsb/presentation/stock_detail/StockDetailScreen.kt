@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package io.xavier.topwsb.presentation.stock_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import io.xavier.topwsb.presentation.stock_detail.chart.ChartBody
import io.xavier.topwsb.presentation.stock_detail.components.SectionInfoItem
import io.xavier.topwsb.presentation.theme.DarkBackgroundTranslucent
import io.xavier.topwsb.presentation.theme.DarkPrimaryText

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
                        .requiredHeight(224.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    state.intradayData?.let {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(defaultHorizontalPadding),
                            contentAlignment = Alignment.Center
                        ) {
//                            Column {
//                                Text(
//                                    text = "Price: ",
//                                    style = MaterialTheme.typography.bodyMedium,
//                                    textAlign = TextAlign.Start,
//                                    color = DarkSecondaryText,
//                                    maxLines = 1
//                                )
//
//                                Text(
//                                    text = state.intradayData.dataPoints.last().close.toString(),
//                                    style = MaterialTheme.typography.bodyMedium,
//                                    textAlign = TextAlign.Start,
//                                    color = DarkPrimaryText,
//                                    fontWeight = FontWeight.Medium,
//                                    maxLines = 1
//                                )
//                            }

//                            Card(
//                                modifier = Modifier
//                                    .sizeIn(minWidth = 72.dp),
//                                shape = MaterialTheme.shapes.small,
//                                colors = CardDefaults.cardColors(
//                                    containerColor = PositiveTrend, // TODO: Set trend color
//                                    contentColor = Color.White
//                                )
//                            ) {
//                                // TODO: PRICE CHANGE PERCENTAGE
//                                Text(
//                                    text = "chng %",
//                                    style = MaterialTheme.typography.titleMedium,
//                                    modifier = Modifier
//                                        .padding(horizontal = 8.dp)
//                                        .align(Alignment.End),
//                                    fontWeight = FontWeight.Bold,
//                                    textAlign = TextAlign.End,
//                                    maxLines = 1
//                                )
//                            }
                            ChartBody(
                                modifier = Modifier.fillMaxSize(),
                                data = state.intradayData
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))

                SectionTitle(
                    title = "Overview",
                    modifier = Modifier.padding(start = defaultHorizontalPadding)
                )
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
                                        showDivider = it.key != "52 Week Low"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}