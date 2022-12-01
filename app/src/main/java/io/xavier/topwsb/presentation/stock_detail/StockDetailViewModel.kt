package io.xavier.topwsb.presentation.stock_detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.xavier.topwsb.common.Constants
import io.xavier.topwsb.common.Resource
import io.xavier.topwsb.domain.use_case.get_stock_detail.GetStockOverviewUseCase
import io.xavier.topwsb.domain.use_case.get_wsb_comments.GetWsbCommentsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * View model for the stock detail screen.
 *
 * @property getStockOverviewUseCase Use case for retrieving company overview data from API.
 * @property getWsbCommentsUseCase Use case for retrieving wallstreetbets comments from API.
 * @param savedStateHandle [SavedStateHandle] used for retrieving ticker of stock to display
 */
@HiltViewModel
class StockDetailViewModel @Inject constructor(
    private val getStockOverviewUseCase: GetStockOverviewUseCase,
    private val getWsbCommentsUseCase: GetWsbCommentsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val tag = "STOCK_DETAIL_VIEW_MODEL"
    private lateinit var ticker: String

    var state by mutableStateOf(StockDetailState())

    init {
        savedStateHandle.get<String>(Constants.PARAM_STOCK_SYMBOL)?.let { symbol ->
            ticker = symbol
            getCompanyOverview(ticker)
        }

        getWsbComments(ticker)
    }

    /**
     * Asynchronous call to get company overview information from Alpha Advantage API.
     *
     * @param ticker ticker of the stock/company to get overiew of
     */
    private fun getCompanyOverview(ticker: String) {
        Log.d(tag, "Requesting stock detail for $ticker")
        getStockOverviewUseCase(ticker).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d(tag, "Loading")
                    state = state.copy(stockOverviewLoading = true)
                }
                is Resource.Success -> {
                    Log.d(tag, "Get Stock Detail Success \n ${result.data!!.companyName}")
                    state = state.copy(stockOverviewLoading = false,
                        stockOverview = result.data)
                }
                is Resource.Error -> {
                    state = state.copy(stockOverviewError = result.message)
                    Log.d(tag, "Get Stock Detail Error: ${result.message}")
                }
            }
        }.launchIn(viewModelScope)
    }

    /**
     * Asynchronous call to get the comments on /r/walltreetbets that mention [ticker] in the
     * past 24-hours.
     *
     * @param ticker stock ticker to query
     */
    private fun getWsbComments(ticker: String) {
        // TODO: implement afterUtc calculation
        getWsbCommentsUseCase(ticker, 1669507200).onEach { result ->
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
     * Loads the /r/wallstreetbets sentiment for [ticker] from the cache.
     *
     * @param ticker ticker of the stock to query sentiment
     */
    private fun getWsbSentiment(ticker: String) {

    }
}