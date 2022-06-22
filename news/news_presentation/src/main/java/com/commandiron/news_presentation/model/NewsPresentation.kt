package com.commandiron.news_presentation.model


data class NewsPresentation(
    val backgroundImageUrl: String = "",
    val steelPriceContentPresentation: SteelPriceContentPresentation? = null,
)

data class SteelPriceContentPresentation(
    val title: String = "",
    val date: String,
    val Q8Price: String,
    val Q10Price: String,
    val Q1232Price: String,
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
        steelPriceContentPresentation = SteelPriceContentPresentation(
            title = "İstanbul Demir Fiyatı",
            date = "20.06.2022",
            Q8Price = "14,900 TL",
            Q10Price = "14,800 TL",
            Q1232Price = "14,700 TL",
        )
    )
)

