package io.xavier.topwsb.presentation.stock_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.xavier.topwsb.R
import io.xavier.topwsb.domain.model.toMap
import io.xavier.topwsb.presentation.common_composables.SectionTitle
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
    val state = viewModel.state

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
                    state.value.stockDetail?.let { stock ->
                        SectionTitle(title = "\$${stock.ticker}")
                    }
                }
            )
        }
    ) { innerPadding ->

        LazyColumn(
            state = listState,
            contentPadding = innerPadding
        ) {

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
                    state.value.stockDetail?.let { stockDetail ->
                        stockDetail.toMap().forEach {
                            SectionInfoItem(
                                name = it.key,
                                value = it.value,
                                showDivider = it.key != "200 day MA"
                            )
                        }
                    }
                }
            }
        }
    }
}