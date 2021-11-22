package com.pklein.prototype.model.details

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * Format de la réponse du serveur Rakuten pour une recherche d’un détail produit
 */
@JsonClass(generateAdapter = true)
data class ProductDetails(
    /**
     * ID du produit
     */
    @Json(name = "productId")
    val productId: Long,

    /**
     * Titre du produit
     */
    @Json(name = "headline")
    val headline: String,

    /**
     * Meilleur prix neuf pour le produit
     */
    @Json(name = "newBestPrice")
    val newBestPrice: Double,

    /**
     * Meilleur prix d’occasion pour le produit
     */
    @Json(name = "usedBestPrice")
    val usedBestPrice: Double,

    /**
     * Vendeur
     */
    @Json(name = "seller")
    val seller: Seller,

    /**
     * Description du produit
     */
    @Json(name = "description")
    val description: String,

    /**
     * Images du produit
     */
    @Json(name = "images")
    val images: List<ProductImageDetails>,
) : Serializable // use of Parcelable would be better, for prototype I simplify it with Serializable