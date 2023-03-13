package com.example.cripty.presentation.coin_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cripty.common.Resource
import com.example.cripty.domain.use_case.get_coin.GetCoinByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinByIdUseCase: GetCoinByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>("coinId")?.let { coinId ->
            getCoinById(coinId)

        }
    }

    private fun getCoinById(coinId: String) {
        getCoinByIdUseCase(coinId).onEach { result ->

            when (result) {
                is Resource.Error -> {
                    _state.value = CoinDetailState(error = result.message ?: "Error")

                }

                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)


                }

                is Resource.Success -> {
                    _state.value = CoinDetailState(coin = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

}