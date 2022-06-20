package com.commandiron.sefim.presentation.model

import androidx.compose.ui.graphics.Color
import com.commandiron.sefim.R

data class NewsPresentation(
    val backgroundImageUrl: String = "",
    val steelPriceContentPresentation: SteelPriceContentPresentation? = null,
    val newIconArrivedPresentation: NewIconArrivedPresentation? = null
)

data class SteelPriceContentPresentation(
    val title: String = "",
    val date: String,
    val Q8Price: String,
    val Q10Price: String,
    val Q1232Price: String,
)

data class NewIconArrivedPresentation(
    val title: String = "",
    val toolPresentation: ToolPresentation
)

val defaultNews = listOf(
    NewsPresentation(
        backgroundImageUrl = "https://media.istockphoto.com/photos/experienced-engineer-explaining-the-problems-in-construction-works-picture-id1267010934?k=20&m=1267010934&s=170667a&w=0&h=K0t50arXZu8ph8dettFBMMEN5vjbvlXovcxHvO7svxg=",
        steelPriceContentPresentation = SteelPriceContentPresentation(
            title = "İstanbul Demir Fiyatı",
            date = "20.06.2022",
            Q8Price = "14,900 TL",
            Q10Price = "14,800 TL",
            Q1232Price = "14,700 TL",
        )
    ),
    NewsPresentation(
        backgroundImageUrl = "https://media.istockphoto.com/photos/experienced-engineer-explaining-the-problems-in-construction-works-picture-id1267010934?k=20&m=1267010934&s=170667a&w=0&h=K0t50arXZu8ph8dettFBMMEN5vjbvlXovcxHvO7svxg=",
        newIconArrivedPresentation = NewIconArrivedPresentation(
            title = "Yeni Hesaplama Aracımız Çıktı !",
            toolPresentation = ToolPresentation(
                title = "Kare Alan Hesaplayıcı",
                resources = R.drawable.duvar_metraj_hesaplayici,
                iconBackground = Color(0xFFfdffb6),
                toolTags = listOf(ToolTag.CAM)
            )
        )
    )
)

