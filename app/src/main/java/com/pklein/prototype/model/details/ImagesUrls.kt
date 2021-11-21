package com.pklein.prototype.model.details

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * Format de la réponse du serveur Rakuten pour récupérer la taille et l'URL d'une image
 */
@JsonClass(generateAdapter = true)
data class ImagesUrls(
    /**
     * la taille de l'image récupérée
     */
    @Json(name = "size")
    val size: String, // Could be an enum, but not use in the Prototype

    /**
     * l'url de l'image récupérée
     */
    @Json(name = "url")
    val url: String,
) : Serializable // use of Parcelable would be better, for prototype I simplify it with Serializable
