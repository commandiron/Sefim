package com.commandiron.tools_domain.use_cases

import com.commandiron.tools_domain.model.RebarPrice
import java.text.SimpleDateFormat
import java.util.*

class GetRebarPrices {
    operator fun invoke() : List<RebarPrice> =
        listOf(
            RebarPrice(
                date = SimpleDateFormat("dd.MM.yyyy EEEE", Locale.getDefault()).format(Date()),
                city = "İstanbul",
                q8mmPrice = "13.420",
                q10mmPrice = "13.320",
                q1232mmPrice = "13.220"
            ),
            RebarPrice(
                date = SimpleDateFormat("dd.MM.yyyy EEEE", Locale.getDefault()).format(Date()),
                city = "Ankara",
                q8mmPrice = "13.600",
                q10mmPrice = "13.600",
                q1232mmPrice = "13.200"
            ),
            RebarPrice(
                date = SimpleDateFormat("dd.MM.yyyy EEEE", Locale.getDefault()).format(Date()),
                city = "İzmir",
                q8mmPrice = "13.050",
                q10mmPrice = "12.850",
                q1232mmPrice = "12.700"
            ),
        )
}