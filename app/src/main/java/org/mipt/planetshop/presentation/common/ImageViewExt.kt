package org.mipt.planetshop.presentation.common

import org.mipt.planetshop.R
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide


fun ImageView.setImageUrl(url: String) {
    val drawable = CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
    Glide.with(context)
        .load(url)
        .error(R.drawable.ic_baseline_image_24)
        .placeholder(drawable)
        .into(this)
}