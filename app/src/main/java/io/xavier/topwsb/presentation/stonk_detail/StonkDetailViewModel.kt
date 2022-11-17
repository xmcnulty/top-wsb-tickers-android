package io.xavier.topwsb.presentation.stonk_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.xavier.topwsb.common.Constants
import io.xavier.topwsb.domain.use_case.get_stock_detail.GetStockDetailUseCase
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StonkDetailViewModel @Inject constructor(
    private val getStockDetailUseCase: GetStockDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(StockDetailState())
    val state: State<StockDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_STOCK_SYMBOL)?.let { symbol ->
            getStockDetail(symbol)
        }
    }

    private fun getStockDetail(symbol: String) {
        getStockDetailUseCase(symbol).onEach {

        }
    }
}