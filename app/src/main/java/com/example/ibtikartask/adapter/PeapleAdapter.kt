package com.example.ibtikartask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.ibtikartask.R
import com.example.ibtikartask.remote.Model.listpeaple.Result
import com.example.shoppinglisttesting.other.Constants.BASE_IMG_URL
import kotlinx.android.synthetic.main.item_peaple_list.view.*
import java.lang.System.load
import javax.inject.Inject

class PeapleAdapter @Inject constructor(
        private val glide: RequestManager): PagingDataAdapter<Result, PeapleAdapter.PeapleViewHolder>(MovieDiffCallback()) {
    class PeapleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(result: Result) {
            itemView.tvName.text = result.name
            itemView.tvKnownFor.text = result.known_for_department

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeapleViewHolder {
        return PeapleViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_peaple_list, parent, false)

        )
    }

    override fun onBindViewHolder(holder: PeapleViewHolder, position: Int) {

        getItem(position)?.let {result->
            holder.bind(result)
            glide.load(BASE_IMG_URL+result.profile_path).into(holder.itemView.imageView2)
           holder.itemView.setOnClickListener   {
                onItemClickListener?.let { click ->
                    click(result)
                }
            }
        }


    }


    private var onItemClickListener: ((Result) -> Unit)? = null

    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val diff = AsyncListDiffer(this, MovieDiffCallback())


}