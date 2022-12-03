package io.xavier.topwsb.presentation.stock_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.xavier.topwsb.common.Resource
import io.xavier.topwsb.di.TimeOnlyFormatter
import io.xavier.topwsb.domain.use_case.get_stock_list.GetTrendingStocksUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.DateFormat
import java.util.Date
import javax.inject.Inject

/**
 * ViewModel class for stock list screen.
 *
 * @property getTrendingStocksUseCase injected use case class to get trending stocks list
 * @property timeFormatter formats last update time to string. injected
 */
@HiltViewModel
class StockListViewModel @Inject constructor(
    private val getTrendingStocksUseCase: GetTrendingStocksUseCase,
    @TimeOnlyFormatter private val timeFormatter: DateFormat
) : ViewModel() {

    private val _state = mutableStateOf(StockListState())
    val state: State<StockListState>
        get() = _state

    init {
        getStocks()
    }

    /**
     * Fetches list of trending stocks using [GetTrendingStocksUseCase]
     */
    private fun getStocks() {
        getTrendingStocksUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    val lastUpdate = result.data!!.maxOfOrNull {
                        it.lastUpdatedUtc
                    }

                    _state.value = StockListState(
                        trendingStocks = result.data,
                        lastUpdateFormatted = formatTime(lastUpdate),
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _state.value = StockListState(
                        error = result.message ?: "An unexpected error occurred",
                        isLoading = false
                    )
                }
                is Resource.Loading -> {
                    _state.value = StockListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    /**
     * Formats time in milliseconds to string.
     *
     * @param time time in milliseconds
     * @return formatted time. '-' if [time] is null
     */
    private fun formatTime(time: Long?): String {
        time?.let {
            return timeFormatter.format(Date(time))
        }
        return "-"
    }
}