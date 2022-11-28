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
import io.xavier.topwsb.domain.use_case.get_stock_detail.GetCompanyOverviewUseCase
import io.xavier.topwsb.domain.use_case.get_wsb_comments.GetWsbCommentsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * View model for the stock detail screen.
 *
 * @property getCompanyOverviewUseCase Use case for retrieving company overview data from API.
 * @property getWsbCommentsUseCase Use case for retrieving wallstreetbets comments from API.
 * @param savedStateHandle [SavedStateHandle] used for retrieving ticker of stock to display
 */
@HiltViewModel
class StockDetailViewModel @Inject constructor(
    private val getCompanyOverviewUseCase: GetCompanyOverviewUseCase,
    private val getWsbCommentsUseCase: GetWsbCommentsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val tag = "STOCK_DETAIL_VM"
    private lateinit var ticker: String

    private val _state = mutableStateOf(StockDetailState())
    val state: State<StockDetailState> = _state

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
        getCompanyOverviewUseCase(ticker).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d(tag, "Loading")
                    _state.value.mktOverviewState = MktOverviewState(loading = true)
                }
                is Resource.Success -> {
                    Log.d(tag, "Get Stock Detail Success")
                    _state.value.mktOverviewState = MktOverviewState(
                        loading = false,
                        data = result.data
                    )
                }
                is Resource.Error -> {
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
                    _state.value.commentState = CommentState(loading = true)
                }
                is Resource.Error -> {
                    Log.e(tag, result.message!!)
                }
                is Resource.Success -> {
                    _state.value.commentState = CommentState(
                        loading = false,
                        comments = result.data
                    )
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