package com.prilepskiy.criptomanagerapp.domain.model.coin

import android.os.Parcelable
import com.prilepskiy.criptomanagerapp.data.response.criptoResponse.DataResponse
import kotlinx.parcelize.Parcelize
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
@Parcelize
data class CoinInfoModel(

    val algorithm: String,
    val assetLaunchDate: String,
    val blockNumber: Int,
    val blockReward: Double,
    val blockTime: String,
    val price:String?,
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
    val url: String,
    val fromSymbol:String,
    val toSymbol:String,
    val lastUpdate:String,
    val favorite:Boolean,

): Parcelable {

    companion object {

        private fun convertTimestampToTime(timestamp: Long?): String {
            if (timestamp == null) return ""
            val stamp = Timestamp(timestamp * 1000)
            val date = Date(stamp.time)
            val pattern = "HH:mm:ss"
            val sdf = SimpleDateFormat(pattern, Locale.getDefault())
            sdf.timeZone = TimeZone.getDefault()
            return sdf.format(date)
        }

       fun from(data: DataResponse):CoinInfoModel=CoinInfoModel(
           algorithm = data.CoinInfo?.Algorithm?:"",
           assetLaunchDate = data.CoinInfo?.AssetLaunchDate?:"",
           blockNumber = data.CoinInfo?.BlockNumber?:0,
           blockReward = data.CoinInfo?.BlockReward?:0.0,
           blockTime = data.CoinInfo?.BlockTime?:"",
           price= data.RAW?.USD?.PRICE,
           documentType = data.CoinInfo?.DocumentType?:"",
           fullName = data.CoinInfo?.FullName?:"",
           idCoin = data.CoinInfo?.Id?:"",
           imageUrl = data.CoinInfo?.ImageUrl?:"",
           internal = data.CoinInfo?.Internal?:"",
           maxSupply = data.CoinInfo?.MaxSupply?:0.0,
           name = data.CoinInfo?.Name?:"",
           netHashesPerSecond = data.CoinInfo?.NetHashesPerSecond?:"",
           proofType = data.CoinInfo?.ProofType?:"",
           marketPerformanceRating=data.CoinInfo?.Rating?.Weiss?.MarketPerformanceRating?:"",
           rating = data.CoinInfo?.Rating?.Weiss?.Rating?:"",
           technologyAdoptionRating=data.CoinInfo?.Rating?.Weiss?.TechnologyAdoptionRating?:"",
           type = data.CoinInfo?.Type?:0,
           url=data.CoinInfo?.Url?:"",
            fromSymbol=data.RAW?.USD?.FROMSYMBOL?:"",
            toSymbol=data.RAW?.USD?.TOSYMBOL?:"",
           lastUpdate =convertTimestampToTime( data.RAW?.USD?.LASTUPDATE?:0),
           favorite = false
       )
    }


}