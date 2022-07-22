package com.obvious.nasapictures.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.obvious.nasapictures.databinding.ActivityImageDetailsBinding
import com.obvious.nasapictures.models.ImageData
import com.obvious.nasapictures.ui.adapters.ImageViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDetailsBinding.inflate(layoutInflater)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        val position = intent.getIntExtra(KEY_POSITION, 0)
        val data = intent.getSerializableExtra(KEY_ALL_IMAGE) as ArrayList<ImageData>

        title = data[position].title
        setContentView(binding.root)
        binding.viewPager.adapter = ImageViewPagerAdapter(supportFragmentManager, lifecycle, data)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                title = data[position].title
            }

        })
        //  binding.imageData = data
        //Utils.loadImage(data.hdurl, binding.image)
    }

    companion object {
        private const val KEY_POSITION = "index"
        private const val KEY_ALL_IMAGE = "imageAll"
        fun intent(
            context: Context,
            selectedImagePosition: Int,
            allImages: ArrayList<ImageData>
        ): Intent {
            val intent = Intent(context, ImageDetailsActivity::class.java)
            intent.putExtra(KEY_POSITION, selectedImagePosition)
            intent.putExtra(KEY_ALL_IMAGE, allImages)
            return intent
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
