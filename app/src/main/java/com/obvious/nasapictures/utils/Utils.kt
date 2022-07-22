package com.obvious.nasapictures.utils

import android.content.res.Resources
import android.widget.ImageView
import com.obvious.nasapictures.R
import com.squareup.picasso.Picasso

object Utils {
    fun dpToPx(dp: Float): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun loadImage(url: String?, imageView: ImageView?) {
        if (!url.isNullOrEmpty() && imageView != null)
            Picasso.get().load(url).placeholder(R.drawable.ic_no_preview)
                .error(R.drawable.ic_no_preview).into(imageView)
    }
}
