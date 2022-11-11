package com.prilepskiy.criptomanagerapp.domain.usecases

import android.util.Log
import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.data.response.convertorResponse.valuteResponse
import com.prilepskiy.criptomanagerapp.domain.interactors.GetValuteListUseCase
import com.prilepskiy.criptomanagerapp.domain.model.coin.CoinInfoModel
import com.prilepskiy.criptomanagerapp.domain.model.valute.ValuteModel
import com.prilepskiy.criptomanagerapp.domain.repository.ConverterRepository
import com.prilepskiy.criptomanagerapp.domain.repository.CriptoRepository

class GetValuteListUseCaseImpl(private val converterRepository: ConverterRepository): GetValuteListUseCase {
    override suspend fun invoke(): ActionResult<List<ValuteModel>> {
        Log.d("tag", "invoke: ")
        return  when(val apiData=converterRepository.getValuteList()){
            is ActionResult.Success -> {

                ActionResult.Success(ValuteModel.fromList(apiData.data))
            }

            is ActionResult.Error -> {
                ActionResult.Error(apiData.errors)
            }

        }
    }
}