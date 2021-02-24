package com.gunt.searchimage.data.domain

import java.io.Serializable

data class ImageDocument(
    val collection: String = "",
    val thumbnail_url: String = "",
    val image_url: String = "",
    val width: Int = 0,
    val height: Int = 0,
    val display_sitename: String = "",
    val doc_url: String = "",
    val datetime: String = ""
) : Serializable {

    fun getThumbnailImageRes(): String {
        return thumbnail_url
    }

    fun getDetailImageRes(): String {
        return image_url
    }

    fun getSiteName(): String {
        return display_sitename
    }

    fun getDateTime(): String {
        return datetime
    }
}
