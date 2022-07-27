package com.obvious.nasapictures.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.obvious.nasapictures.databinding.ActivityMainBinding
import com.obvious.nasapictures.models.ImageData
import com.obvious.nasapictures.ui.adapters.ImageListAdapter
import com.obvious.nasapictures.utils.SpaceItemDecoration
import com.obvious.nasapictures.utils.Utils
import com.obvious.nasapictures.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val spanCount = 2
        val includeEdge = true
        val spacing = Utils.dpToPx(15f)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(SpaceItemDecoration(spanCount, spacing, includeEdge))
        }
        viewModel.getAllLatestImages()

        viewModel.imagesLiveData.observe(this) {
            binding.recyclerView.adapter = ImageListAdapter(it) { image ->
                navigateToDetails(image)
            }
        }
    }

// Used to navigate to details page
    private fun navigateToDetails(imageData: Int) {
        // val myList = ArrayList<ImageData>()
        // myList.addAll(viewModel.imagesLiveData.value!!)
        startActivity(
            ImageDetailsActivity.intent(
                this, imageData,
                viewModel.imagesLiveData.value as ArrayList<ImageData> /* = java.util.ArrayList<com.obvious.nasapictures.models.ImageData> */
            )
        )
    }
}