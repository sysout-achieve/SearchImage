package com.gunt.searchimage.ui.imagedocs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.gunt.searchimage.BR
import com.gunt.searchimage.R
import com.gunt.searchimage.databinding.FragmentImageDocsBinding

class ImageDocsFragment : Fragment() {

    lateinit var binding :FragmentImageDocsBinding
    private val viewModel : ImageDocsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_image_docs, container, false)
        binding.lifecycleOwner = this

        val args : ImageDocsFragmentArgs by navArgs()
        binding.setVariable(BR.imageDocsViewModel, viewModel)
        viewModel.document = args.imageDoc

        binding.btnBack.setOnClickListener { this.activity?.onBackPressed() }

        return binding.root
    }
}