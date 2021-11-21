package com.pklein.prototype.api.products

import com.pklein.prototype.api.ApiClient
import com.pklein.prototype.api.BaseManager
import com.pklein.prototype.model.Product
import com.pklein.prototype.model.details.ProductDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductsManager : Products, BaseManager() {

    private val productsApi = ApiClient.apiService

    override suspend fun search(): List<Product>? {
        return try {
            val apiResponse = withContext(Dispatchers.IO) {
                productsApi.search()
            }
            if (apiResponse.isSuccessful) {
                val result = apiResponse.body()
                result?.products
            } else {
                null // todo manage errors
            }
        } catch (th: Throwable) {
            null // todo manage errors
        }
    }

    override suspend fun getDetails(id: Long): ProductDetails? {
        return try {
            val apiResponse = withContext(Dispatchers.IO) {
                productsApi.getDetails(id)
            }
            if (apiResponse.isSuccessful) {
                apiResponse.body()
            } else {
                null // todo manage errors
            }
        } catch (th: Throwable) {
            null // todo manage errors
        }
    }
}
