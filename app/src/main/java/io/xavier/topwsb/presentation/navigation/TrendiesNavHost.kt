package io.xavier.topwsb.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
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
                val stockJson = Gson().toJson(stock)

                navController.navigate(
                    route = Screen.StockDetailScreen.route
                            + "?stock={stock}"
                        .replace(
                            oldValue = "{stock}",
                            newValue = stockJson
                        )
                )
            })
        }

        composable(
            route = Screen.StockDetailScreen.route + "?stock={stock}",
            arguments = listOf(navArgument("stock") {
                nullable = true
                type = NavType.StringType
            })
        ) {
            StockDetailScreen { navController.popBackStack() }
        }
    }
}