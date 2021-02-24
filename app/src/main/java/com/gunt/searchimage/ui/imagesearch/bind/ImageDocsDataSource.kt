package com.gunt.searchimage.ui.imagesearch.bind

import androidx.paging.PageKeyedDataSource
import com.gunt.searchimage.data.domain.ImageDocument
import com.gunt.searchimage.ui.imagesearch.ImageSearchViewModel

class ImageDocsDataSource(private var viewModel: ImageSearchViewModel) :
    PageKeyedDataSource<Int, ImageDocument>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ImageDocument>) {
        viewModel.page = 1
        viewModel.fetchImage(viewModel.query, viewModel.page) { result ->
            if (!result.meta.is_end) callback.onResult(result.documents, null, ++viewModel.page)
            viewModel.isEmpty.postValue(result.meta.total_count == 0)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ImageDocument>) {
        viewModel.fetchImage(viewModel.query, params.key) { result ->
            if (!result.meta.is_end) callback.onResult(result.documents, ++viewModel.page)
            viewModel.isEmpty.postValue(result.meta.total_count == 0)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ImageDocument>) {
    }
}
