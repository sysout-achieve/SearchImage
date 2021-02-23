package com.gunt.searchimage.ui.imagesearch.bind

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gunt.searchimage.data.domain.ImageDocument
import com.gunt.searchimage.databinding.ImagedocsItemBinding

class ImageDocsViewHolder
private constructor
(private val binding: ImagedocsItemBinding, private val onClick: (ImageDocument) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ImageDocument?) {
        binding.imageDocs = item
        itemView.setOnClickListener {
            item?.let {
                onClick(item)
            }
        }
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup, onClick: (ImageDocument) -> Unit): ImageDocsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ImagedocsItemBinding.inflate(layoutInflater, parent, false)

            return ImageDocsViewHolder(binding, onClick)
        }
    }
}
