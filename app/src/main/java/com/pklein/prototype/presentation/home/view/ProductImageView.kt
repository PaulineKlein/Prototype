package com.pklein.prototype.presentation.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.pklein.prototype.R

@ExperimentalCoilApi
@Composable
fun ProductImage(data: String?, size: Dp = dimensionResource(id = R.dimen.small_image)) {
    if (!data.isNullOrEmpty()) {
        Image(
            painter = rememberImagePainter(
                data = data,
                builder = {
                    crossfade(true)
                    placeholder(R.drawable.placeholder)
                }
            ),
            contentDescription = stringResource(R.string.product_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_8))
                .size(size)
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.placeholder),
            contentDescription = stringResource(R.string.product_image_error),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_8))
                .size(size)
                .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
        )
    }
}