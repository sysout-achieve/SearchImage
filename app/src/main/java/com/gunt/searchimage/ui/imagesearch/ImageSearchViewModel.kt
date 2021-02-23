package com.gunt.searchimage.ui.imagesearch

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gunt.searchimage.data.domain.ImageDocument
import com.gunt.searchimage.data.repository.ImageRepository
import com.gunt.searchimage.data.repository.network.REQUEST_IMAGE_LIST_SIZE_DEFAULT
import com.gunt.searchimage.data.repository.network.response.ResponseKakao
import com.gunt.searchimage.ui.imagesearch.bind.ImageDocsDataSource
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ImageSearchViewModel
@ViewModelInject
constructor(
    private val repository: ImageRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    var query = ""
    var page = 1
    var isEmpty: MutableLiveData<Boolean> = MutableLiveData(false)

    private val config = PagedList.Config.Builder()
        .setInitialLoadSizeHint(REQUEST_IMAGE_LIST_SIZE_DEFAULT)
        .setPageSize(REQUEST_IMAGE_LIST_SIZE_DEFAULT)
        .setEnablePlaceholders(false)
        .build()

    val imageDocsList = LivePagedListBuilder(
        object : DataSource.Factory<Int, ImageDocument>() {
            override fun create(): DataSource<Int, ImageDocument> {
                return ImageDocsDataSource(this@ImageSearchViewModel)
            }
        },
        config
    ).build()

    fun fetchImage(searchText: String, page: Int, unit: (ResponseKakao<ImageDocument>) -> Unit) {
        val disposable = getImageFromRepository(searchText, page)
            .subscribeOn(Schedulers.io())
            .subscribe(
                { unit(it) },
                {
                }
            )

        compositeDisposable.add(disposable)
    }

    fun getImageFromRepository(searchText: String, page: Int) = repository.fetchImage(searchText, page)

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
