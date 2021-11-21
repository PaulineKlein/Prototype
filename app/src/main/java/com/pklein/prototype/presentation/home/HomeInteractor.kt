package com.pklein.prototype.presentation.home

import com.pklein.prototype.api.products.ProductsManager
import com.pklein.prototype.model.Product
import com.pklein.prototype.model.details.ProductDetails

class HomeInteractor(private val productsManager: ProductsManager = ProductsManager()) {

    suspend fun search(keyword: String): List<Product>? {
        return productsManager.search(keyword)
    }

    suspend fun getDetails(id: Long): ProductDetails? {
        return productsManager.getDetails(id)
    }
}