package com.hero.superherocompose.model.data

data class HeroWrapper(
    val heroes: List<Hero>
)

data class Hero(
    val name: String,
    val biography: Biography,
    val image: Image,
    val isFavorite: Boolean = false
)

data class Biography(
    val fullName: String
)

data class Image(
    val url: String
)