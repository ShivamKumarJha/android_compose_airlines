package com.shivamkumarjha.airlinescompose.ui.components

import androidx.compose.runtime.Composable
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import coil.transform.Transformation
import com.shivamkumarjha.airlinescompose.R

@OptIn(ExperimentalCoilApi::class)
@Composable
fun loadPainter(
    url: String?,
    placeHolder: Int = R.drawable.ic_airline,
    transformation: Transformation = CircleCropTransformation()
): ImagePainter {
    return if (url.isNullOrEmpty()) {
        rememberImagePainter(
            data = placeHolder,
            imageLoader = LocalImageLoader.current,
            builder = {
                placeholder(drawableResId = placeHolder)
                transformations(transformation)
            }
        )
    } else {
        rememberImagePainter(
            data = url,
            imageLoader = LocalImageLoader.current,
            builder = {
                placeholder(drawableResId = placeHolder)
                transformations(transformation)
            }
        )
    }
}