package com.pklein.prototype.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Format de la réponse du serveur Rakuten pour une recherche sur un keyword
 */
@JsonClass(generateAdapter = true)
data class SearchResult(
    /**
     * intitulé de la recherche
     */
    @Json(name = "title")
    val title: String,

    /**
     * produits de la recherche
     */
    @Json(name = "products")
    val products: List<Product>
)
