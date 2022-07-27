package com.obvious.nasapictures.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.obvious.nasapictures.databinding.FragmentImageDetailsBinding
import com.obvious.nasapictures.data.models.ImageData
import com.obvious.nasapictures.utils.Utils

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ImageDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImageDetailsFragment : Fragment() {
    private lateinit var allImages: ImageData
    private lateinit var binding: FragmentImageDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            allImages =
                it.getSerializable(ARG_PARAM2) as ImageData/* = java.util.ArrayList<com.obvious.nasapictures.data.models.ImageData> */
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentImageDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageData = allImages
        Utils.loadImage(allImages.hdurl, binding.image)
    }

    companion object {
        @JvmStatic
        fun newInstance(images: ImageData) =
            ImageDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM2, images)
                }
            }
    }
}