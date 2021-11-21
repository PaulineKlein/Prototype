package com.pklein.prototype

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import coil.annotation.ExperimentalCoilApi
import com.pklein.prototype.model.Product
import com.pklein.prototype.presentation.home.view.HomeContent
import org.junit.Rule
import org.junit.Test

@ExperimentalCoilApi
@ExperimentalComposeUiApi
class HomeComposeTest {

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

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeContentShouldDisplayProductTest() {
        composeTestRule.setContent {
            HomeContent(listProduct) {
                //do nothing
            }
        }
        composeTestRule
            .onNodeWithText(listProduct[0].headline)
            .assertExists()
    }

    @Test
    fun homeContentShouldDisplayEmptyImageTest() {
        composeTestRule.setContent {
            HomeContent(null) {
                //do nothing
            }
        }
        composeTestRule
            .onNodeWithContentDescription("Image not loaded")
            .assertExists()
    }
}