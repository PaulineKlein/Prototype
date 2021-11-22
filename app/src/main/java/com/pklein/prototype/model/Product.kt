package com.pklein.prototype.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Format de la réponse du serveur Rakuten pour les produits d'une recherche sur un keyword
 */
@JsonClass(generateAdapter = true)
data class Product(
    /**
     * ID du produit à utiliser dans le ws de détails
     */
    @Json(name = "id")
    val id: Long,

    /**
     * titre du produit
     */
    @Json(name = "headline")
    val headline: String,

    /**
     * meilleur prix neuf pour le produit
     */
    @Json(name = "newBestPrice")
    val newBestPrice: Double,

    /**
     * meilleur prix d’occasion pour le produit
     */
    @Json(name = "usedBestPrice")
    val usedBestPrice: Double,

    /**
     * tableau d’images présentant le produit
     */
    @Json(name = "imagesUrls")
    val imagesUrls: List<String>,

    /**
     * note moyenne des utilisateurs sur le produit
     */
    @Json(name = "reviewsAverageNote")
    val reviewsAverageNote: Double,

    /**
     * nombre de notes des utilisateurs sur le produit
     */
    @Json(name = "nbReviews")
    val nbReviews: Int,
)