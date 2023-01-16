package io.xavier.topwsb.presentation.stock_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.xavier.topwsb.common.Constants
import io.xavier.topwsb.common.Resource
import io.xavier.topwsb.domain.model.Sentiment
import io.xavier.topwsb.domain.model.chart_data.IntradayInterval
import io.xavier.topwsb.domain.use_case.stock_details.GetIntradayDataUseCase
import io.xavier.topwsb.domain.use_case.stock_details.GetStockOverviewUseCase
import io.xavier.topwsb.domain.use_case.stock_details.GetWsbCommentsUseCase
import io.xavier.topwsb.presentation.stock_detail.components.chart.ChartState
import io.xavier.topwsb.presentation.stock_detail.components.comments.CommentsState
import io.xavier.topwsb.presentation.stock_detail.components.market_data.MarketDataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * View model for the stock detail screen.
 *
 * @property getOverviewUseCase Use case for retrieving company overview data from API.
 * @property getCommentsUseCase Use case for retrieving wallstreetbets comments from API.
 * @param savedStateHandle [SavedStateHandle] used for retrieving ticker of stock to display
 */
@HiltViewModel
class StockDetailViewModel @Inject constructor(
    private val getOverviewUseCase: GetStockOverviewUseCase,
    private val getCommentsUseCase: GetWsbCommentsUseCase,
    private val getIntradayUseCase: GetIntradayDataUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val tag = "STOCK_DETAIL_VIEW_MODEL"
    lateinit var ticker: String
        private set

    private var _state = mutableStateOf(StockDetailState())
    val state: State<StockDetailState>
        get() = _state

    lateinit var sentiment: Sentiment
        private set

    init {
        savedStateHandle.get<String>(Constants.PARAM_STOCK_SYMBOL)?.let { symbol ->
            ticker = symbol
        }

        savedStateHandle.get<String>("sentiment")?.let {
            Log.d(tag, "Sentiment is $it")
            sentiment = Sentiment.fromName(it)
        }

        getMarketData()
        getChartData()
        getWsbComments()
    }

    /**
     * Asynchronous call to get company overview information from Alpha Advantage API.
     */
    private fun getMarketData() {
        Log.d(tag, "Requesting stock detail for $ticker")
        getOverviewUseCase(ticker).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d(tag, "Loading")
                    _state.value = _state.value.copy(
                        marketDataState = MarketDataState.Loading
                    )
                }
                is Resource.Success -> {
                    Log.d(tag, "Get Stock Detail Success \n ${result.data!!.companyName}")
                    _state.value = _state.value.copy(
                        marketDataState = MarketDataState.Success(
                            data = result.data
                        )
                    )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        marketDataState = MarketDataState.Error(
                            message = result.message ?: "Could not load data"
                        )
                    )
                    Log.d(tag, "Get Stock Detail Error: ${result.message}")
                }
            }
        }.launchIn(viewModelScope)
    }

    /**
     * Asynchronous call to get the comments on /r/walltreetbets that mention [ticker] in the
     * past 24-hours.
     */
    private fun getWsbComments() {
        getCommentsUseCase(ticker).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        commentsState = CommentsState.Loading
                    )
                    Log.d(tag, "Loading comments")
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        commentsState = CommentsState.Error(
                            message = result.message ?: "An unexpected error occurred"
                        )
                    )
                    Log.e(tag, result.message!!)
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
        getIntradayUseCase(
            ticker,
            IntradayInterval.ONE_HOUR
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

                    //throw Exception(result.message)
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        chartState = ChartState.Success(
                            data = result.data
                        )
                    )

                    print(state.value.marketDataState)
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
            && stockDetailState.marketDataState is MarketDataState.Loading
    }
}