package com.pklein.prototype.presentation.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.pklein.prototype.R
import com.pklein.prototype.model.Product

@ExperimentalCoilApi
@Composable
fun HomeContent(products: List<Product>?, navigateToDetail: (Product) -> Unit) {
    if (products != null) {
        val productsDisplay = remember { products } // stores the current state of the variable
        // LazyColumn is equivalent of RecyclerView
        LazyColumn(
            contentPadding = PaddingValues(
                horizontal = dimensionResource(id = R.dimen.padding_16),
                vertical = dimensionResource(id = R.dimen.padding_8)
            )
        ) {
            items(
                items = productsDisplay,
                itemContent = {
                    ProductsItemContent(product = it, navigateToDetail = navigateToDetail)
                })
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // set a default image when there is no results :
            Image(
                painter = painterResource(id = R.drawable.empty_result),
                contentDescription = stringResource(R.string.product_image_error),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_8))
                    .size(dimensionResource(id = R.dimen.big_image))
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
            )
        }
    }

}

