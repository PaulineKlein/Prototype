package com.pklein.prototype.api

import com.pklein.prototype.api.products.ProductsApi
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
    private const val BASE_URL: String =
        "https://4206121f-64a1-4256-a73d-2ac541b3efe4.mock.pstmn.io/"

    private val moshiConverterFactory: Converter.Factory by lazy {
        MoshiConverterFactory.create(buildMoshi())
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    val apiService: ProductsApi by lazy {
        retrofit.create(ProductsApi::class.java)
    }
}