package com.bijesh.heroes.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "heroes")
data class HeroesResponse(
    @PrimaryKey(autoGenerate = false) @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String? = "",
    @SerializedName("slug") val slug: String? = "",
    @Embedded @SerializedName("powerstats") val powerstats: Powerstats? = Powerstats(),
    @Embedded @SerializedName("appearance") val appearance: Appearance? = Appearance(),
    @Embedded @SerializedName("biography") val biography: Biography? = Biography(),
    @Embedded @SerializedName("work") val work: Work? = Work(),
    @Embedded @SerializedName("connections") val connections: Connections? = Connections(),
    @Embedded @SerializedName("images") val images: Images? = Images(),
    var favorite: Boolean = false
)

data class Powerstats(
    @SerializedName("intelligence") val intelligence: Int = 0,
    @SerializedName("strength") val strength: Int = 0,
    @SerializedName("speed") val speed: Int = 0,
    @SerializedName("durability") val durability: Int = 0,
    @SerializedName("power") val power: Int = 0,
    @SerializedName("combat") val combat: Int = 0
)

data class Appearance(
    @SerializedName("gender") val gender: String? = "",
    @SerializedName("race") val race: String? = "",
    @SerializedName("height") val height: List<String>? = emptyList(),
    @SerializedName("weight") val weight: List<String>? = emptyList(),
    @SerializedName("eyeColor") val eyeColor: String? = "",
    @SerializedName("hairColor") val hairColor: String? = ""
)

data class Biography(
    @SerializedName("fullName") val fullName: String? = "",
    @SerializedName("alterEgos") val alterEgos: String? = "",
    @SerializedName("aliases") val aliases: List<String>? = emptyList(),
    @SerializedName("placeOfBirth") val placeOfBirth: String? = "",
    @SerializedName("firstAppearance") val firstAppearance: String? = "",
    @SerializedName("publisher") val publisher: String? = "",
    @SerializedName("alignment") val alignment: String? = ""
)

data class Work(
    @SerializedName("occupation") val occupation: String? = "",
    @SerializedName("base") val base: String? = ""
)

data class Connections(
    @SerializedName("groupAffiliation") val groupAffiliation: String? = "",
    @SerializedName("relatives") val relatives: String? = ""
)

data class Images(
    @SerializedName("xs") val xs: String? = "",
    @SerializedName("sm") val sm: String? = "",
    @SerializedName("md") val md: String? = "",
    @SerializedName("lg") val lg: String? = ""
)