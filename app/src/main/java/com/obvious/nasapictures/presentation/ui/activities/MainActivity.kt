package com.obvious.nasapictures.presentation.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.obvious.nasapictures.data.models.ImageData
import com.obvious.nasapictures.databinding.ActivityMainBinding
import com.obvious.nasapictures.presentation.ui.adapters.ImageListAdapter
import com.obvious.nasapictures.presentation.viewmodel.MainActivityViewModel
import com.obvious.nasapictures.utils.Resource
import com.obvious.nasapictures.utils.SpaceItemDecoration
import com.obvious.nasapictures.utils.Utils
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

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                when (it) {
                    is Resource.Success -> {
                        binding.loader.visibility = View.GONE
                        binding.recyclerView.adapter = ImageListAdapter(it.data!!) { image ->
                            navigateToDetails(image)
                        }
                    }
                    is Resource.Error -> {
                        binding.loader.visibility = View.GONE
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> {
                        binding.loader.visibility = View.VISIBLE
                    }
                }
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
                viewModel.uiState.value.data as ArrayList<ImageData> /* = java.util.ArrayList<com.obvious.nasapictures.data.models.ImageData> */
            )
        )
    }
}