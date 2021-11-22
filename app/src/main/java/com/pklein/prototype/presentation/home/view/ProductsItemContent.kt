package com.pklein.prototype.presentation.home.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import com.pklein.prototype.R
import com.pklein.prototype.model.Product

@ExperimentalCoilApi
@Composable
fun ProductsItemContent(product: Product, navigateToDetail: (Product) -> Unit) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.small_padding),
                vertical = dimensionResource(id = R.dimen.small_padding)
            )
            .fillMaxWidth(),
        elevation = dimensionResource(id = R.dimen.small_elevation),
        shape = RoundedCornerShape(corner = CornerSize(dimensionResource(id = R.dimen.medium_corner)))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { navigateToDetail(product) }
        ) {
            ProductImage(product.imagesUrls[0])
            Column(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.medium_padding))
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = product.headline, style = typography.subtitle1)
                Text(
                    text = stringResource(R.string.amount_euro, product.newBestPrice.toString()),
                    style = typography.caption
                )
            }
        }
    }
}

@ExperimentalCoilApi
@Preview
@Composable
fun PreviewProductItem() {
    val product = Product(
        id = 5504650604,
        headline = "Samsung Galaxy S20 FE 5G 128 Go 6.7 pouces Bleu Double Sim",
        newBestPrice = 475.95,
        usedBestPrice = 415.00,
        imagesUrls = listOf("https://fr.shopping.rakuten.com/photo/1511640339.jpg"),
        reviewsAverageNote = 4.570707070707071,
        nbReviews = 198
    )
    ProductsItemContent(product = product, navigateToDetail = { null })
}