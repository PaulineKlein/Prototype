package com.pklein.prototype.presentation.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProvider
import coil.annotation.ExperimentalCoilApi
import com.pklein.prototype.R
import com.pklein.prototype.model.Product
import com.pklein.prototype.model.details.ProductDetails
import com.pklein.prototype.presentation.LoadingScreen
import com.pklein.prototype.presentation.details.DetailActivity
import com.pklein.prototype.presentation.home.view.HomeContent
import com.pklein.prototype.ui.theme.PrototypeTheme
import com.pklein.prototype.utils.bind
import com.pklein.prototype.utils.distinctUntilChanged
import com.pklein.prototype.utils.map

@ExperimentalCoilApi
class HomeActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLoader()
        homeViewModel.handle(HomeAction.Search)
        handleViewModelChanges()
    }

    private fun handleViewModelChanges() {
        homeViewModel.state.map { it.productsToShow }
            .distinctUntilChanged()
            .bind(this, this::showProducts)
        homeViewModel.state.map { it.detailsToShow }
            .bind(this, this::showDetails)
    }

    private fun showProducts(listProduct: List<Product>?) {
        setContent {
            PrototypeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DisplayHome(products = listProduct, isLoading = false) {
                        homeViewModel.handle(HomeAction.GetDetails(it.id))
                    }
                }
            }
        }
    }

    private fun showDetails(productDetails: ProductDetails?) {
        if (productDetails != null) {
            startActivity(this, DetailActivity.newIntent(this, productDetails), null)
        } else {
            // todo show popup
        }
    }

    private fun showLoader() {
        setContent {
            PrototypeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    DisplayHome(isLoading = true) {
                        // do nothing when loading
                    }
                }
            }
        }
    }
}


@ExperimentalCoilApi
@Composable
fun DisplayHome(
    products: List<Product>? = null,
    isLoading: Boolean,
    navigateToDetail: (Product) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.app_name))
                },
                elevation = AppBarDefaults.TopAppBarElevation
            )
        },
        content = {
            LoadingScreen(isLoading = isLoading) {
                HomeContent(products, navigateToDetail)
            }
        }
    )
}


@ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val lisProduct: List<Product> =
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
    PrototypeTheme {
        DisplayHome(lisProduct, false) {
            // do nothing on default preview
        }
    }
}