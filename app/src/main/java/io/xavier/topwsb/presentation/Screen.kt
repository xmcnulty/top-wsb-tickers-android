package io.xavier.topwsb.presentation

/**
 * All possible navigation routes for this application contained in objects.
 *
 * @property StockListScreen.route route to screen with list of stocks
 * @property StockDetailScreen.route root route to stock detail screen
 */
sealed class Screen(val route: String) {
    object StockListScreen: Screen("stock_list_screen")
    object StockDetailScreen: Screen("stock_detail_screen")
}
