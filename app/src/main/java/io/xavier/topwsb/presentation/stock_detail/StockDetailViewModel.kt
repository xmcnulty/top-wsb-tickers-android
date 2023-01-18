package io.xavier.topwsb.presentation.stock_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import io.xavier.topwsb.common.Resource
import io.xavier.topwsb.domain.model.trending_stock.TrendingStock
import io.xavier.topwsb.domain.use_case.stock_details.GetChartDataUseCase
import io.xavier.topwsb.domain.use_case.stock_details.GetWsbCommentsUseCase
import io.xavier.topwsb.presentation.stock_detail.components.chart.ChartState
import io.xavier.topwsb.presentation.stock_detail.components.comments.CommentsState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

/**
 * View model for the stock detail screen.
 *
 * @property getCommentsUseCase Use case for retrieving wallstreetbets comments from API.
 * @property getChartDataUseCase Use case for getting chart data
 * @param savedStateHandle [SavedStateHandle] used for retrieving ticker of stock to display
 */
@HiltViewModel
class StockDetailViewModel @Inject constructor(
    private val getCommentsUseCase: GetWsbCommentsUseCase,
    private val getChartDataUseCase: GetChartDataUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val tag = "STOCK_DETAIL_VIEW_MODEL"

    lateinit var stock: TrendingStock
        private set

    private var _state = mutableStateOf(StockDetailState())
    val state: State<StockDetailState>
        get() = _state

    private val errorEventsChannel = Channel<String>()
    val errorEvents = errorEventsChannel.receiveAsFlow()

    init {
        savedStateHandle.get<String>("stock")?.let { stockJson ->
            stock = Gson().fromJson(stockJson, TrendingStock::class.java)

            getChartData()
            getWsbComments()
        }
    }

    /**
     * Asynchronous call to get the comments on /r/walltreetbets that mention the stock ticker in the
     * past 24-hours.
     */
    private fun getWsbComments() {
        getCommentsUseCase(stock.ticker).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        commentsState = CommentsState.Loading
                    )
                }
                is Resource.Error -> {
                    val errorMessage = result.message ?: "An unexpected error occurred"

                    _state.value = _state.value.copy(
                        commentsState = CommentsState.Error(
                            message = errorMessage
                        )
                    )
                    errorEventsChannel.send(errorMessage)
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        commentsState = CommentsState.Success(result.data ?: emptyList())
                    )
                    Log.d(tag, "Successfully loaded comments.")
                }
            }
        }.launchIn(viewModelScope)
    }

    /**
     * Gets the intraday data for charting.
     */
    private fun getChartData() {
        getChartDataUseCase(
            stock.ticker
        ).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        chartState = ChartState.Loading
                    )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        chartState = ChartState.Error(
                            message = result.message ?: "Error loading data"
                        )
                    )
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        chartState = ChartState.Success(
                            data = result.data
                        )
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    /**
     * The screen will show a loading state when either the market overview or the comments are
     * loading. Chart loading will be displayed separately in the chart area.
     */
    fun isLoading(): Boolean {
        val stockDetailState = _state.value

        return stockDetailState.commentsState is CommentsState.Loading
    }
}