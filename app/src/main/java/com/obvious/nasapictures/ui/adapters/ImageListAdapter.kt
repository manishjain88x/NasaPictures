package com.obvious.nasapictures.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.obvious.nasapictures.databinding.ItemImageBinding
import com.obvious.nasapictures.models.ImageData
import com.obvious.nasapictures.utils.Utils

class ImageListAdapter(
    private val list: List<ImageData>,
    private val onImageClick: (Int) -> Unit
) :
    RecyclerView.Adapter<ImageListAdapter.ImageHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ImageHolder(ItemImageBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ImageHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageData: ImageData) {
            Utils.loadImage(imageData.hdurl, binding.ivImage)
            binding.ivImage.setOnClickListener {
                onImageClick.invoke(adapterPosition)
            }
        }
    }

}