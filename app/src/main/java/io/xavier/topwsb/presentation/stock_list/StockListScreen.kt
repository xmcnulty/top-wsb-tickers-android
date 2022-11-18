package io.xavier.topwsb.presentation.stock_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.xavier.topwsb.R.drawable
import io.xavier.topwsb.presentation.stock_list.components.StockListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockListScreen(
    navController: NavController,
    viewModel: StockListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val stockListState = rememberLazyListState()

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                title = {
                    TrendingOnWsbText()
                },
                actions = {
                    TODO()
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            state = stockListState,
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                // TODO: SectionTitle
            }

            items(state.stocks) {

            }
        }
    }
}

@Composable
fun TrendingOnWsbText(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "Powered by ",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodySmall
        )

        Image(
            modifier = Modifier
                .requiredHeight(20.dp)
                .padding(top = 2.dp),
            painter = painterResource(id = drawable.ic_wsb_logo),
            contentDescription = null
        )
    }
}