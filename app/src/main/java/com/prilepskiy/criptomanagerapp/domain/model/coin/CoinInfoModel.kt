package com.prilepskiy.criptomanagerapp.domain.model.coin

import android.provider.ContactsContract
import com.prilepskiy.criptomanagerapp.data.response.criptoResponse.CoinInfoResponse
import com.prilepskiy.criptomanagerapp.ui.base.BaseAdapterTypes
import java.util.concurrent.ThreadLocalRandom

data class CoinInfoModel(
    override val id: Int,
    val algorithm: String,
    val assetLaunchDate: String,
    val blockNumber: Int,
    val blockReward: Double,
    val blockTime: String,
    val documentType: String,
    val fullName: String,
    val idCoin: String,
    val imageUrl: String,
    val internal: String,
    val maxSupply: Double,
    val name: String,
    val netHashesPerSecond: String,
    val proofType: String,
    val marketPerformanceRating: String,
    val rating: String,
    val technologyAdoptionRating: String,
    val type: Int,
    val url: String
) : BaseAdapterTypes(){
    companion object {
       fun from(data: CoinInfoResponse):CoinInfoModel=CoinInfoModel(
           id=CoinInfoModel::class.java.hashCode()+ ThreadLocalRandom.current().nextInt(),
           algorithm = data.Algorithm,
           assetLaunchDate = data.AssetLaunchDate,
           blockNumber = data.BlockNumber,
           blockReward = data.BlockReward,
           blockTime = data.BlockTime,
           documentType = data.DocumentType,
           fullName = data.FullName,
           idCoin = data.Id,
           imageUrl = data.ImageUrl,
           internal = data.Internal,
           maxSupply = data.MaxSupply,
           name = data.Name,
           netHashesPerSecond = data.NetHashesPerSecond,
           proofType = data.ProofType,
           marketPerformanceRating=data.Rating.Weiss.MarketPerformanceRating,
           rating = data.Rating.Weiss.Rating,
           technologyAdoptionRating=data.Rating.Weiss.TechnologyAdoptionRating,
           type = data.Type,
           url=data.Url
       )
    }


}