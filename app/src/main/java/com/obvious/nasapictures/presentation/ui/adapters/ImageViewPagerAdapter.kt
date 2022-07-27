package com.obvious.nasapictures.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.obvious.nasapictures.data.models.ImageData
import com.obvious.nasapictures.presentation.ui.fragment.ImageDetailsFragment

class ImageViewPagerAdapter(
    private val fm: FragmentManager,
    private val lifecycle: Lifecycle,
    private val images: ArrayList<ImageData>
) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int {
        return images.size
    }

    override fun createFragment(position: Int): Fragment {
        return ImageDetailsFragment.newInstance(images[position])
    }
}