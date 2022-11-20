package io.xavier.topwsb.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.xavier.topwsb.presentation.stock_list.StockListScreen
import io.xavier.topwsb.presentation.theme.TopWSBTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            TopWSBTheme {
                // A surface container using the 'background' color from the theme
                StockListScreen(navController = navController)
            }
        }
    }
}