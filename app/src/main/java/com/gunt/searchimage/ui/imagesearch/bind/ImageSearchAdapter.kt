package com.gunt.searchimage.ui.imagesearch.bind

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gunt.searchimage.data.domain.ImageDocument
import com.gunt.searchimage.databinding.ImagedocsItemBinding

class ImageSearchAdapter(private val onClick: (ImageDocument) -> Unit) : PagedListAdapter<ImageDocument, ImageDocsViewHolder>(diffUtil) {
    companion object  {
        private val diffUtil = object : DiffUtil.ItemCallback<ImageDocument>() {
            override fun areItemsTheSame(oldItem: ImageDocument, newItem: ImageDocument) = oldItem.image_url == newItem.image_url
            override fun areContentsTheSame(oldItem: ImageDocument, newItem: ImageDocument) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDocsViewHolder {
        return ImageDocsViewHolder.from(parent, onClick)
    }

    override fun onBindViewHolder(holder: ImageDocsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

