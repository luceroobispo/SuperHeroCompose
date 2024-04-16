package com.hero.superherocompose.model.data

import com.google.gson.annotations.SerializedName

data class HeroWrapper (
    @SerializedName("results")  // This is the key from the JSON response
    val heroes: List<Hero>
)
data class Hero(
    val id: String,
    val name: String,
    val biography: Biography,
    val image: Image,
    @SerializedName("powerstats") // This is the key from the JSON response
    val powerStats: PowerStats
)

data class PowerStats(
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
    val combat: String
)

data class Biography (
    @SerializedName("full-name") // This is the key from the JSON response
    val fullName: String,

    @SerializedName("place-of-birth") // This is the key from the JSON response
    val placeOfBirth: String,

    val publisher: String
)

data class Image (
    val url: String
)