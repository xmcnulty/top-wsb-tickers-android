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
import io.xavier.topwsb.domain.use_case.stock_details.GetIntradayDataUseCase
import io.xavier.topwsb.domain.use_case.stock_details.GetStockOverviewUseCase
import io.xavier.topwsb.domain.use_case.stock_details.GetWsbCommentsUseCase
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
    private lateinit var ticker: String

    private var _state = mutableStateOf(StockDetailState())
    val state: State<StockDetailState>
        get() = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_STOCK_SYMBOL)?.let { symbol ->
            ticker = symbol
        }

        getCompanyOverview()
        getIntradayData()
    }

    /**
     * Asynchronous call to get company overview information from Alpha Advantage API.
     */
    private fun getCompanyOverview() {
        Log.d(tag, "Requesting stock detail for $ticker")
        getOverviewUseCase(ticker).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d(tag, "Loading")
                    _state.value = _state.value.copy(stockOverviewLoading = true)
                }
                is Resource.Success -> {
                    Log.d(tag, "Get Stock Detail Success \n ${result.data!!.companyName}")
                    _state.value = _state.value.copy(stockOverviewLoading = false,
                        stockOverview = result.data)
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(stockOverviewError = result.message)
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
        // TODO: implement afterUtc calculation
        getCommentsUseCase(ticker, 1669507200).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d(tag, "Loading comments")
                }
                is Resource.Error -> {
                    Log.e(tag, result.message!!)
                }
                is Resource.Success -> {
                    Log.d(tag, "Successfully loaded comments.")
                }
            }
        }.launchIn(viewModelScope)
    }

    /**
     * Gets the intraday data for charting.
     */
    private fun getIntradayData() {
        getIntradayUseCase(ticker).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoadingChart = true)
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoadingChart = false,
                        chartError = result.message
                    )
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoadingChart = false,
                        intradayData = result.data
                    )

                    print(state.value.intradayData)
                }
            }
        }.launchIn(viewModelScope)
    }
}