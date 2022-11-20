package io.xavier.topwsb.presentation.stock_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.xavier.topwsb.common.Resource
import io.xavier.topwsb.domain.use_case.get_stock_list.GetStockListUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StockListViewModel @Inject constructor(
    private val getStockListUseCase: GetStockListUseCase
) : ViewModel() {

    private val _state = mutableStateOf(StockListState())
    val state: State<StockListState> = _state

    init {
        getStocks()
    }

    private fun getStocks() {
        getStockListUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = StockListState(
                        stocks = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = StockListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = StockListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}