package com.example.cripty.domain.repository

import com.example.cripty.data.remote.dto.CoinDetailDto
import com.example.cripty.data.remote.dto.CoinDto

interface CoinRepository{

    suspend fun getCoins() : List<CoinDto>

    suspend fun getCoinById(coinId : String) : CoinDetailDto
}