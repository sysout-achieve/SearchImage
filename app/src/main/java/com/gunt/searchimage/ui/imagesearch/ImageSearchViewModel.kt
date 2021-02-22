package com.gunt.searchimage.ui.imagesearch

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gunt.searchimage.data.domain.ImageDocument
import com.gunt.searchimage.data.repository.ImageRepository
import com.gunt.searchimage.data.repository.network.REQUEST_IMAGE_LIST_SIZE_DEFAULT
import com.gunt.searchimage.data.repository.network.response.ResponseKakao
import com.gunt.searchimage.ui.imagesearch.bind.ImageDocsDataSourceFactory
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ImageSearchViewModel
@ViewModelInject
constructor(
    private val repository: ImageRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    var query = ""
    var isEmpty : MutableLiveData<Boolean> = MutableLiveData(false)

    val imageDocsList =
        PagedList.Config.Builder()
        .setPageSize(REQUEST_IMAGE_LIST_SIZE_DEFAULT)
        .setInitialLoadSizeHint(REQUEST_IMAGE_LIST_SIZE_DEFAULT)
        .setEnablePlaceholders(true)
        .build()
        .run {
            LivePagedListBuilder(ImageDocsDataSourceFactory(this@ImageSearchViewModel), this).build()
        }


    fun getImageFromRepository(searchText: String, page: Int, unit: (ResponseKakao<ImageDocument>) -> Unit) {
        val disposable = repository.fetchImage(searchText, page)
            .subscribe({
                unit(it)
            }, {
            })

        compositeDisposable.add(disposable)
    }

    fun fetchImage() {
        imageDocsList.value?.dataSource?.invalidate()
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }


}

