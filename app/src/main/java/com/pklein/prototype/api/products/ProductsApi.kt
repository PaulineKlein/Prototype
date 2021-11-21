package com.pklein.prototype.api.products

import com.pklein.prototype.model.SearchResult
import com.pklein.prototype.model.details.ProductDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApi {
    @GET("products/search")
    suspend fun search(@Query("keyword") keyword: String): Response<SearchResult>

    @GET("products/details")
    suspend fun getDetails(@Query("id") id: Long): Response<ProductDetails>
}