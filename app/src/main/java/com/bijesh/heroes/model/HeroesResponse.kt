package com.bijesh.heroes.model

import com.google.gson.annotations.SerializedName

data class HeroesResponse(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("slug") val slug: String = "",
    @SerializedName("powerstats") val powerstats: Powerstats? = null,
    @SerializedName("appearance") val appearance: Appearance? = null,
    @SerializedName("biography") val biography: Biography? = null,
    @SerializedName("work") val work: Work? = null,
    @SerializedName("connections") val connections: Connections? = null,
    @SerializedName("images") val images: Images? = null
)

data class Powerstats (
    @SerializedName("intelligence") val intelligence : Int,
    @SerializedName("strength") val strength : Int,
    @SerializedName("speed") val speed : Int,
    @SerializedName("durability") val durability : Int,
    @SerializedName("power") val power : Int,
    @SerializedName("combat") val combat : Int
)

data class Appearance (
    @SerializedName("gender") val gender : String,
    @SerializedName("race") val race : String,
    @SerializedName("height") val height : List<String>,
    @SerializedName("weight") val weight : List<String>,
    @SerializedName("eyeColor") val eyeColor : String,
    @SerializedName("hairColor") val hairColor : String
)

data class Biography (
    @SerializedName("fullName") val fullName : String,
    @SerializedName("alterEgos") val alterEgos : String,
    @SerializedName("aliases") val aliases : List<String>,
    @SerializedName("placeOfBirth") val placeOfBirth : String,
    @SerializedName("firstAppearance") val firstAppearance : String,
    @SerializedName("publisher") val publisher : String,
    @SerializedName("alignment") val alignment : String
)

data class Work (
    @SerializedName("occupation") val occupation : String,
    @SerializedName("base") val base : String
)

data class Connections (
    @SerializedName("groupAffiliation") val groupAffiliation : String,
    @SerializedName("relatives") val relatives : String
)

data class Images (
    @SerializedName("xs") val xs : String,
    @SerializedName("sm") val sm : String,
    @SerializedName("md") val md : String,
    @SerializedName("lg") val lg : String
)