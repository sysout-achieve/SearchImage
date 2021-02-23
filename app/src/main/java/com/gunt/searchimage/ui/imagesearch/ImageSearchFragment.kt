package com.gunt.searchimage.ui.imagesearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.gunt.searchimage.BR
import com.gunt.searchimage.R
import com.gunt.searchimage.data.domain.ImageDocument
import com.gunt.searchimage.databinding.FragmentImageSearchBinding
import com.gunt.searchimage.ui.imagesearch.bind.ImageSearchAdapter
import com.jakewharton.rxbinding4.widget.textChanges
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

const val GRID_SPAN_COUNT = 3

@AndroidEntryPoint
class ImageSearchFragment : Fragment() {

    private lateinit var binding: FragmentImageSearchBinding
    private val viewModel: ImageSearchViewModel by viewModels()

    private val compositeDisposable = CompositeDisposable()

    private val imageSearchAdapter = ImageSearchAdapter {
        val action = ImageSearchFragmentDirections.detailImage(it)
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_image_search, container, false)
        binding.setVariable(BR.imageSearchViewModel, viewModel)
        binding.lifecycleOwner = this

        setupListAdapter()
        setupSearchEditTextChangedListener()
        setupPagedListObserver()
        setupIsEmptyObserver()

        binding.executePendingBindings()
        return binding.root
    }

    // 자동 검색 기능
    private fun setupSearchEditTextChangedListener() {
        val subscription: Disposable =
            binding.editSearch.textChanges().debounce(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onNext = {
                        if (viewModel.query != it.toString() && it != "") {
                            viewModel.query = it.toString()
                            (binding.recyclerImage.adapter as ImageSearchAdapter).currentList?.dataSource?.invalidate()
                        }
                    }
                )
        compositeDisposable.add(subscription)
    }

    private fun setupListAdapter() {
        binding.recyclerImage.layoutManager = GridLayoutManager(binding.root.context, GRID_SPAN_COUNT)
        binding.recyclerImage.adapter = imageSearchAdapter
    }

    private fun setupPagedListObserver() {
        viewModel.imageDocsList.observe(
            this.viewLifecycleOwner,
            Observer<PagedList<ImageDocument>> {
                (binding.recyclerImage.adapter as ImageSearchAdapter).submitList(it)
            }
        )
    }

    private fun setupIsEmptyObserver() {
        viewModel.isEmpty.observe(
            this.viewLifecycleOwner,
            Observer {
                binding.recyclerImage.visibility = if (it) View.GONE else View.VISIBLE
            }
        )
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
