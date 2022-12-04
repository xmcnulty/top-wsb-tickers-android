package io.xavier.topwsb.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.xavier.topwsb.presentation.Screen
import io.xavier.topwsb.presentation.stock_detail.StockDetailScreen
import io.xavier.topwsb.presentation.stock_list.StockListScreen

/**
 * Custom NavHost for this application.
 *
 * @param navController the [NavHostController] for this host
 */
@Composable
fun TrendiesNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.StockListScreen.route
    ) {
        composable(
            route = Screen.StockListScreen.route
        ) {
            StockListScreen(navToStockDetail = { stock ->
                navController.navigate(
                    route = Screen.StockDetailScreen.route
                            + "/${stock.ticker}/${stock.sentiment.text}"
                )
            })
        }

        composable(
            route = Screen.StockDetailScreen.route + "/{ticker}/{sentiment}"
        ) {
            StockDetailScreen { navController.popBackStack() }
        }
    }
}