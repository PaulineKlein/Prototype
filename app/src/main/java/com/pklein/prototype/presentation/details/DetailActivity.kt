package com.pklein.prototype.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.res.stringResource
import coil.annotation.ExperimentalCoilApi
import com.pklein.prototype.R
import com.pklein.prototype.model.details.ProductDetails
import com.pklein.prototype.presentation.details.view.DetailContent
import com.pklein.prototype.ui.theme.PrototypeTheme

class DetailActivity : ComponentActivity() {
    private val productDetails: ProductDetails by lazy {
        intent?.getSerializableExtra(PRODUCT_ID) as ProductDetails
    }

    @ExperimentalCoilApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrototypeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = stringResource(
                                            R.string.product_detail_title,
                                            productDetails.seller.login
                                        )
                                    )
                                },
                                navigationIcon = {
                                    IconButton(
                                        onClick = {
                                            this.finish()
                                        }
                                    ) {
                                        Icon(Icons.Filled.ArrowBack, contentDescription = "")
                                    }
                                },
                                elevation = AppBarDefaults.TopAppBarElevation
                            )
                        },
                        content = {
                            DetailContent(productDetails)
                        }
                    )
                }
            }
        }
    }

    companion object {
        private const val PRODUCT_ID = "product_id"
        fun newIntent(context: Context, productDetails: ProductDetails) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(PRODUCT_ID, productDetails)
            }
    }
}