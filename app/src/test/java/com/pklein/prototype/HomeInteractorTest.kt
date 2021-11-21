package com.pklein.prototype

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.pklein.prototype.api.products.ProductsManager
import com.pklein.prototype.model.Product
import com.pklein.prototype.model.details.ProductDetails
import com.pklein.prototype.model.details.Seller
import com.pklein.prototype.presentation.home.HomeInteractor
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class HomeInteractorTest {

    private val productsManager: ProductsManager = mock()
    private val interactor = HomeInteractor(productsManager)
    private val listProduct: List<Product> =
        listOf(
            Product(
                id = 5504650604,
                headline = "Samsung Galaxy S20 FE 5G 128 Go 6.7 pouces Bleu Double Sim",
                newBestPrice = 475.95,
                usedBestPrice = 415.00,
                imagesUrls = listOf("https://fr.shopping.rakuten.com/photo/1511640339.jpg"),
                reviewsAverageNote = 4.570707070707071,
                nbReviews = 198
            )
        )
    private val details: ProductDetails = ProductDetails(
        productId = 12345,
        headline = "Samsung Galaxy S20 FE 5G 128 Go 6.7 pouces Bleu Double Sim",
        newBestPrice = 475.95,
        usedBestPrice = 415.00,
        seller = Seller(123, "Pixel-Tech"),
        images = listOf(),
        description = "une description"
    )

    @Test
    fun `Search should return the correct mock value`() {
        runBlocking {
            Mockito.`when`(
                productsManager.search(any())
            ).thenReturn(listProduct)

            val result = interactor.search("Samsung")
            verify(productsManager, times(1))
                .search(any())
            Assert.assertEquals(listProduct.size, result?.size)
            Assert.assertEquals(listProduct[0].id, result?.get(0)?.id)
        }
    }

    @Test
    fun `GetDetails should return the correct mock value`() {
        runBlocking {
            Mockito.`when`(
                productsManager.getDetails(any())
            ).thenReturn(details)

            val result = interactor.getDetails(12345)
            verify(productsManager, times(1))
                .getDetails(any())
            Assert.assertEquals(details.description, result?.description)
            Assert.assertEquals(details.headline, result?.headline)
        }
    }
}