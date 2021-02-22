package com.gunt.searchimage.util.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.gunt.searchimage.R

object ImageViewExt {
    @JvmStatic
    @BindingAdapter("loadImage")
    fun ImageView.loadImage(imgUrl: String?) {
        Glide.with(this)
            .load(imgUrl)
            .error(R.drawable.ic_launcher_foreground) // Error Image 있을 경우 변경
            .into(this)

    }

    @JvmStatic
    @BindingAdapter("loadImageWithThumbnail")
    fun ImageView.loadImageWithThumbnail(imgUrl: String?) {
        Glide.with(this)
            .load(imgUrl)
            .thumbnail(0.2f)
            .error(R.drawable.ic_launcher_foreground) // Error Image 있을 경우 변경
            .into(this)
    }
}
