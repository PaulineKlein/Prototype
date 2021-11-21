package com.pklein.prototype.presentation.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
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
import com.pklein.prototype.presentation.home.view.SearchBar
import com.pklein.prototype.ui.theme.PrototypeTheme
import com.pklein.prototype.utils.bind
import com.pklein.prototype.utils.map

class IsLoadingState(isLoading: Boolean) {
    var isLoading by mutableStateOf(isLoading)
}

// global variable to let compose known when it should display loadable view
val isLoadingState = IsLoadingState(false)

@ExperimentalCoilApi
@ExperimentalComposeUiApi
class HomeActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showProducts(null)
        handleViewModelChanges()
    }

    private fun handleViewModelChanges() {
        homeViewModel.state.map { it.productsToShow }
            .bind(this, this::showProducts)
        homeViewModel.state.map { it.detailsToShow }
            .bind(this, this::showDetails)
    }

    private fun showProducts(listProduct: List<Product>?) {
        setContent {
            PrototypeTheme {
                // A surface container using the 'background' color from the theme
                isLoadingState.isLoading = false
                Surface(color = MaterialTheme.colors.background) {
                    DisplayHome(
                        products = listProduct,
                        homeViewModel = homeViewModel
                    ) {
                        homeViewModel.handle(HomeAction.GetDetails(it.id))
                    }
                }
            }
        }
    }

    private fun showDetails(productDetails: ProductDetails?) {
        if (productDetails != null) {
            homeViewModel.handle(HomeAction.ResetDetails)
            startActivity(this, DetailActivity.newIntent(this, productDetails), null)
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@Composable
fun DisplayHome(
    products: List<Product>? = null,
    homeViewModel: HomeViewModel,
    navigateToDetail: (Product) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name)) },
                elevation = AppBarDefaults.TopAppBarElevation
            )
        },
        content = {
            Column(modifier = Modifier.fillMaxSize()) {
                SearchBar(hint = stringResource(R.string.home_search_hint)) {
                    isLoadingState.isLoading = true
                    homeViewModel.handle(HomeAction.Search(it))
                }
                LoadingScreen(isLoading = isLoadingState.isLoading) {
                    HomeContent(products, navigateToDetail)
                }
            }
        }
    )
}

@ExperimentalComposeUiApi
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
        DisplayHome(lisProduct, homeViewModel = HomeViewModel()) {
            // do nothing on default preview
        }
    }
}