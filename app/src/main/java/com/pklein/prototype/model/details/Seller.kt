package com.pklein.prototype.model.details

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * Format de la réponse du serveur Rakuten pour le detail d'un vendeur
 */
@JsonClass(generateAdapter = true)
data class Seller(
    /**
     * ID du vendeur
     */
    @Json(name = "id")
    val id: Long,

    /**
     * nom du vendeur
     */
    @Json(name = "login")
    val login: String,
) : Serializable // use of Parcelable would be better, for prototype I simplify it with Serializable