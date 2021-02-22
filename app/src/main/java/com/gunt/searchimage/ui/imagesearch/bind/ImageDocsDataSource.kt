package com.gunt.searchimage.ui.imagesearch.bind

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.gunt.searchimage.data.domain.ImageDocument
import com.gunt.searchimage.ui.imagesearch.ImageSearchViewModel

class ImageDocsDataSourceFactory(private val viewModel: ImageSearchViewModel) :
    DataSource.Factory<Int, ImageDocument>() {

    private val liveDataImageDocs = MutableLiveData<ImageDocsDataSource>()
    override fun create() = ImageDocsDataSource(viewModel).apply {
        liveDataImageDocs.postValue(this)
    }
}

class ImageDocsDataSource(private var viewModel: ImageSearchViewModel) :
    PageKeyedDataSource<Int, ImageDocument>() {

    var page = 1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ImageDocument>
    ) {
        viewModel.getImageFromRepository(viewModel.query, page) { result ->
            if (!result.meta.is_end) callback.onResult(result.documents, null, ++page)
            viewModel.isEmpty.postValue(result.meta.total_count == 0)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ImageDocument>) {
        viewModel.getImageFromRepository(viewModel.query, params.key) { result ->
            if (!result.meta.is_end) callback.onResult(result.documents, ++page)
            viewModel.isEmpty.postValue(result.meta.total_count == 0)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ImageDocument>) {
    }


}