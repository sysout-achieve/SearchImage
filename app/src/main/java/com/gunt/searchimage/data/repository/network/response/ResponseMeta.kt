package com.gunt.searchimage.data.repository.network.response

data class ResponseMeta(
    val total_count: Int,
    val pageableCount: Int,
    val is_end: Boolean
)
