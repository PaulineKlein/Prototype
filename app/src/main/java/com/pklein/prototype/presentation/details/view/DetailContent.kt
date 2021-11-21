package com.pklein.prototype.presentation.details.view

import android.widget.TextView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import coil.annotation.ExperimentalCoilApi
import com.pklein.prototype.R
import com.pklein.prototype.model.details.ProductDetails
import com.pklein.prototype.presentation.home.view.ProductImage

@ExperimentalCoilApi
@Composable
fun DetailContent(productDetails: ProductDetails) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DetailHeader(productDetails)
                    DetailBody(productDetails)
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun DetailHeader(
    productDetails: ProductDetails,
) {
    val imageUrl = productDetails.images[0].imagesUrls.entry[0].url
    ProductImage(imageUrl, dimensionResource(id = R.dimen.big_image))
    // could be improve by transforming image into carousel of images
}

@Composable
private fun DetailBody(productDetails: ProductDetails) {
    Column {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_8)))

        ProductName(productDetails.headline)
        ProductDescription(productDetails.description)

        ProductProperty(
            stringResource(R.string.product_detail_new_price),
            stringResource(R.string.amount_euro, productDetails.newBestPrice.toString())
        )
        ProductProperty(
            stringResource(R.string.product_detail_used_price),
            stringResource(R.string.amount_euro, productDetails.usedBestPrice.toString())
        )
    }
}

@Composable
private fun ProductName(
    name: String
) {
    val padding = dimensionResource(id = R.dimen.padding_16)
    Column(modifier = Modifier.padding(start = padding, end = padding, bottom = padding)) {
        Text(
            text = name,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ProductDescription(
    description: String
) {
    val padding = dimensionResource(id = R.dimen.padding_16)
    Column(modifier = Modifier.padding(start = padding, end = padding, bottom = padding)) {
        AndroidView(
            factory = { context -> TextView(context) },
            update = {
                it.text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
            }
        )
    }
}

@Composable
private fun ProductProperty(label: String, value: String) {
    val padding = dimensionResource(id = R.dimen.padding_16)
    Column(modifier = Modifier.padding(start = padding, end = padding, bottom = padding)) {
        Divider()
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = label,
                style = MaterialTheme.typography.caption,
            )
        }
        Text(
            text = value,
            style = MaterialTheme.typography.body1
        )
    }
}