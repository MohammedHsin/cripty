package com.example.cripty.domain.use_case.get_coin

import android.util.Log
import com.example.cripty.common.Resource
import com.example.cripty.data.remote.dto.CoinDetailDto
import com.example.cripty.data.remote.dto.CoinDto
import com.example.cripty.data.remote.dto.toCoin
import com.example.cripty.data.remote.dto.toCoinDetail
import com.example.cripty.domain.model.Coin
import com.example.cripty.domain.model.CoinDetail
import com.example.cripty.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinByIdUseCase @Inject constructor(
    private val coinRepository: CoinRepository
){
    operator fun invoke(coinId : String) : Flow<Resource<CoinDetail>> {
        return flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = coinRepository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        }catch (e : HttpException){
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occurred"))
        }catch (e : IOException){
            emit(Resource.Error<CoinDetail>("Couldn't reach server. Check your internet connection"))
    }
    }
}
}