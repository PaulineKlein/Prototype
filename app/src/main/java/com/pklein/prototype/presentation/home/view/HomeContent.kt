package com.pklein.prototype.presentation.home.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                items = productsDisplay,
                itemContent = {
                    ProductsItemContent(product = it, navigateToDetail = navigateToDetail)
                })
        }
    } else {
        Text(text = stringResource(R.string.unknown_error), style = MaterialTheme.typography.h6)
    }

}

