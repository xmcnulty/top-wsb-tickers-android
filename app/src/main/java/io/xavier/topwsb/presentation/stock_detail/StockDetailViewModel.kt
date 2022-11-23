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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StockDetailViewModel @Inject constructor(
    private val getCompanyOverviewUseCase: GetCompanyOverviewUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val tag = "STOCK_DETAIL_VM"

    private val _state = mutableStateOf(StockDetailState())
    val state: State<StockDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_STOCK_SYMBOL)?.let { symbol ->
            Log.d(tag, "Init request for $symbol")
            getCompanyOverview(symbol)
        }
    }

    private fun getCompanyOverview(symbol: String) {
        Log.d(tag, "Requesting stock detail for $symbol")
        getCompanyOverviewUseCase(symbol).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d(tag, "Loading")
                    _state.value = StockDetailState(isLoading = true)
                }
                is Resource.Success -> {
                    Log.d(tag, "Get Stock Detail Success")
                    _state.value = StockDetailState(companyOverview = result.data)
                }
                is Resource.Error -> {
                    Log.d(tag, "Get Stock Detail Error: ${result.message}")
                }
            }
        }.launchIn(viewModelScope)
    }
}