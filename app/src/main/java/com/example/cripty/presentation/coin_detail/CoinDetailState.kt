package com.example.cripty.presentation.coin_detail

import com.example.cripty.domain.model.Coin
import com.example.cripty.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading : Boolean = false,
    val coin : CoinDetail? = null,
    val error : String = ""
)
