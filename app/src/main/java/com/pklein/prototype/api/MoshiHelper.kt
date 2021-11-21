package com.pklein.prototype.api

import com.squareup.moshi.Moshi

fun buildMoshi(): Moshi {
    return Moshi.Builder()
        .build()
}