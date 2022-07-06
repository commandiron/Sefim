package com.commandiron.rebarpricestool_data.repository

import com.commandiron.core.BuildConfig
import com.commandiron.core.util.Response
import com.commandiron.core.util.Strings.ExceptionMessages.EMPTY_LIST
import com.commandiron.core.util.Strings.ExceptionMessages.AN_ERROR_OCCURRED
import com.commandiron.rebarpricestool_data.local.RebarPricesDao
import com.commandiron.rebarpricestool_data.local.entity.RebarPriceEntity
import com.commandiron.rebarpricestool_data.mapper.toRebarPrice
import com.commandiron.rebarpricestool_domain.model.RebarPrice
import com.commandiron.rebarpricestool_domain.repository.RebarPricesToolRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.time.Duration
import java.time.LocalDateTime

class RebarPricesToolRepositoryImpl(
    private val dao: RebarPricesDao,
): RebarPricesToolRepository {

    override suspend fun getRebarPrices(): Flow<Response<List<RebarPrice>>> {
        if(dao.getAllRebarPrices().isEmpty()){
            return getRebarPricesFromJsoupAndSaveToLocalDb()
        }
        val localDateTimeNow = LocalDateTime.now()
        val dbSaveTime = LocalDateTime.parse(dao.getAllRebarPrices()[0].date)
        val durationBetween = Duration.between(dbSaveTime, localDateTimeNow)
        if(durationBetween.toHours() > 12){
            return getRebarPricesFromJsoupAndSaveToLocalDb()
        }
        return flow {
            emit(Response.Loading)
            val rebarPrices = dao.getAllRebarPrices().map { it.toRebarPrice() }
            emit(Response.Success(rebarPrices))
        }
    }

    private fun getRebarPricesFromJsoupAndSaveToLocalDb(): Flow<Response<List<RebarPrice>>> = callbackFlow{
        withContext(Dispatchers.IO) {
            runCatching {
                try {
                    send(Response.Loading)
                    val rebarPriceEntities = mutableListOf<RebarPriceEntity>()
                    val dataForLoop: Elements?

                    val dataUrl = BuildConfig.JSOUP_DATA_URL

                    val doc = Jsoup.connect(dataUrl).get()
                    val cssQuery = "body > div.body > div > div:nth-child(1) > div > div.col-md-8.mb-5.mb-lg-0.order-first.order-md-2 > div.card.analiztablocard > div.card-body > div > table > tbody"
                    val fullData = doc.select(cssQuery)
                    dataForLoop = fullData.first()?.children()?.select("tr")
                    dataForLoop?.let { elements ->
                        for(i in 0 until elements.size){
                            val dataChildren = elements[i].children()
                            val rebarPriceEntity =
                                RebarPriceEntity(
                                    id = i,
                                    date = LocalDateTime.now().toString(),
                                    city = dataChildren.select("th").text(),
                                    q8mmPrice = dataChildren.select("td:nth-child(2)").text(),
                                    q10mmPrice = dataChildren.select("td:nth-child(3)").text(),
                                    q1232mmPrice = dataChildren.select("td:nth-child(4)").text()
                                )
                            rebarPriceEntities += rebarPriceEntity
                        }
                    }
                    if(rebarPriceEntities.isNotEmpty()){
                        dao.insertAllRebarPrices(rebarPriceEntities)
                        send(Response.Success(rebarPriceEntities.map { it.toRebarPrice() }))
                    }else{
                        send(Response.Error(EMPTY_LIST))
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                    send(Response.Error(AN_ERROR_OCCURRED))
                }
            }
        }
        awaitClose {
            channel.close()
        }
    }
}