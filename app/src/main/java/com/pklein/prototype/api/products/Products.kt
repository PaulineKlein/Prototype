package com.pklein.prototype.api.products

import com.pklein.prototype.model.Product
import com.pklein.prototype.model.details.ProductDetails

/**
 * Interface de récupération de produits
 * Expose toutes les méthodes utilisées en phase de recherche et d'affichage de produit.
 */
interface Products {

    /**
     * Retourne le résultat d’une recherche Rakuten, les 20 premiers résultats sur le keyword samsung
     *
     * @return une liste de Produits : List<[Product]> ou NUll si erreur
     */
    suspend fun search(): List<Product>?

    /**
     * Retourne le résultat de la recherche d’un détail produit
     *
     * @param ID du produit
     * @return le détail du produit [ProductDetails] ou NUll si erreur
     */
    suspend fun getDetails(id: Long): ProductDetails?

}