package com.nicklasslagbrand.tvshow.core.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImageWithCenterCropTransform(
    url: String,
    vararg transformations: RequestOptions
) =
    Glide.with(context)
        .load(url)
        .apply {
            for (transformation in transformations) {
                apply(transformation)
            }
        }
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
