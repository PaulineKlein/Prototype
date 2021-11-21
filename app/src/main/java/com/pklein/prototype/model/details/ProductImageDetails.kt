package com.pklein.prototype.model.details

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * Format de la réponse du serveur Rakuten pour la liste des images d'un produit
 */
@JsonClass(generateAdapter = true)
data class ProductImageDetails(
    /**
     * la liste des images avec leur taille et leur URL associée
     */
    @Json(name = "imagesUrls")
    val imagesUrls: ImagesUrlsList,

    /**
     * ID des images
     */
    @Json(name = "id")
    val id: Long,
) : Serializable // use of Parcelable would be better, for prototype I simplify it with Serializable
