package com.pklein.prototype

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.pklein.prototype.model.Product
import com.pklein.prototype.model.details.ProductDetails
import com.pklein.prototype.model.details.Seller
import com.pklein.prototype.presentation.home.HomeAction
import com.pklein.prototype.presentation.home.HomeInteractor
import com.pklein.prototype.presentation.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val homeInteractor: HomeInteractor = mock()
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
    fun `Action ResetDetails should set detailsToShow to null`() {
        val viewModel = HomeViewModel(homeInteractor = homeInteractor)
        viewModel.handle(HomeAction.ResetDetails)
        assertEquals(null, viewModel.state.value?.detailsToShow)
    }

    @Test
    fun `Action Search should return the correct mock value`() {
        val viewModel = HomeViewModel(homeInteractor = homeInteractor)
        runBlocking {
            Mockito.`when`(
                homeInteractor.search(any())
            ).thenReturn(listProduct)

        }
        viewModel.handle(HomeAction.Search("Samsung"))
        assertEquals(listProduct.size, viewModel.state.value?.productsToShow?.size)
        assertEquals(listProduct[0].id, viewModel.state.value?.productsToShow?.get(0)?.id)
    }

    @Test
    fun `Action getDetails should return the correct mock value`() {
        val viewModel = HomeViewModel(homeInteractor = homeInteractor)
        runBlocking {
            Mockito.`when`(
                homeInteractor.getDetails(any())
            ).thenReturn(details)

        }
        viewModel.handle(HomeAction.GetDetails(12345))
        assertEquals(details.description, viewModel.state.value?.detailsToShow?.description)
        assertEquals(details.headline, viewModel.state.value?.detailsToShow?.headline)
    }
}