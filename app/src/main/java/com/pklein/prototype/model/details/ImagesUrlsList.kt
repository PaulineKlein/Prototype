package com.pklein.prototype.model.details

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * Format de la réponse du serveur Rakuten pour récupérer la list des entrées d'ImagesUrls
 */
@JsonClass(generateAdapter = true)
data class ImagesUrlsList(
    /**
     * liste des ImagesUrls
     */
    @Json(name = "entry")
    val entry: List<ImagesUrls>,
) : Serializable // use of Parcelable would be better, for prototype I simplify it with Serializable
