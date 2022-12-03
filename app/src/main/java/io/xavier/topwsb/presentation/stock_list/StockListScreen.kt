@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package io.xavier.topwsb.presentation.stock_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.xavier.topwsb.R.drawable
import io.xavier.topwsb.domain.model.TrendingStock
import io.xavier.topwsb.presentation.common_composables.SectionTitle
import io.xavier.topwsb.presentation.stock_list.components.LastUpdateText
import io.xavier.topwsb.presentation.stock_list.components.StockListItem

/**
 * Screen that displays a list of the top 20 stocks mentioned on r/wallstreetbets
 * in the past 24 hours. Uses Material 3 components and specifications.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockListScreen(
    navToStockDetail: (TrendingStock) -> Unit,
    viewModel: StockListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val stockListState = rememberLazyListState()

    Scaffold(
        modifier = Modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Image(
                        modifier = Modifier
                            .requiredHeight(64.dp)
                            .padding(top = 4.dp, start = 4.dp),
                        painter = painterResource(id = drawable.ic_wsb_logo),
                        contentDescription = null
                    )
                },
                actions = {
                    LastUpdateText(
                        lastUpdateTime = state.lastUpdateFormatted,
                        isRefreshing = state.isLoading,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            )
        }
    ) { innerPadding ->

        LazyColumn(
            state = stockListState,
            contentPadding = innerPadding
        ) {
            item {
                SectionTitle(
                    title = "Trending on /r/wallstreetbets",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            items(state.trendingStocks) { stock ->
                StockListItem(
                    trendingStock = stock,
                    onItemClick = navToStockDetail
                )
            }
        }
    }
}