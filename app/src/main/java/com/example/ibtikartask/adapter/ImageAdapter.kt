package com.example.ibtikartask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.ibtikartask.R
import com.example.shoppinglisttesting.other.Constants.BASE_IMG_URL
import kotlinx.android.synthetic.main.item_image_details.view.*
import javax.inject.Inject

class ImageAdapter@Inject constructor(
        private val glide: RequestManager
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    private val diffCallBack = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem

        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }
    private val diff = AsyncListDiffer(this, diffCallBack)
    var image: List<String>
        get() = diff.currentList
        set(value) = diff.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_image_details, parent, false)
        )
    }

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val url = image[position]
        holder.itemView.apply {
            glide.load(BASE_IMG_URL+url).into(ivMovieImage)
            setOnClickListener {
                onItemClickListener?.let { click ->
                click(url)
            }

            }
        }

    }


    override fun getItemCount(): Int {
        return image.size
    }
}