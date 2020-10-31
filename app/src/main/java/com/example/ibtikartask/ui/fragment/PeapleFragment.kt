package com.example.ibtikartask.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ibtikartask.R
import com.example.ibtikartask.adapter.PeapleAdapter
import com.example.ibtikartask.adapter.PeapleLoadStateAdapter
import com.example.ibtikartask.ui.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_peaple.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PeapleFragment @Inject constructor(val peapleAdapter: PeapleAdapter) : Fragment(R.layout.fragment_peaple) {
    lateinit var viewModels: MovieViewModel
    private var job: Job? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModels = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)


        setupRecyclerView()
        PagingNetwork()
        adapterLoadStateListener()

        peapleAdapter.setOnItemClickListener {

            findNavController().navigate(
                PeapleFragmentDirections.actionPeapleFragmentToDetailsFragment(
                    it
                )
            )


        }

    }

    private fun adapterLoadStateListener() {
        peapleAdapter.addLoadStateListener { loadState ->
            progress_bar.isVisible = loadState.source.refresh is LoadState.Loading
            rvPeapleFragment.isVisible = loadState.source.refresh is LoadState.NotLoading
            button_retry.isVisible = loadState.source.refresh is LoadState.Error
            text_view_error.isVisible = loadState.source.refresh is LoadState.Error

            // empty view
            if (loadState.source.refresh is LoadState.NotLoading &&
                loadState.append.endOfPaginationReached &&
                peapleAdapter.itemCount < 1
            ) {
                rvPeapleFragment.isVisible = false
                text_view_empty.isVisible = true
            } else {
                text_view_empty.isVisible = false

            }

        }
    }

    private fun PagingNetwork() {
        job?.cancel()
        job = lifecycleScope.launch {
            viewModels.pagingPeaple().observe(viewLifecycleOwner) {
                peapleAdapter.submitData(viewLifecycleOwner.lifecycle, it)

            }
        }
    }

    private fun setupRecyclerView() {
        rvPeapleFragment.apply {
            itemAnimator = null
            setHasFixedSize(true)
            adapter = peapleAdapter.withLoadStateHeaderAndFooter(
                header = PeapleLoadStateAdapter { peapleAdapter.retry() },
                footer = PeapleLoadStateAdapter { peapleAdapter.retry() }
            )
            layoutManager = LinearLayoutManager(requireContext())

            button_retry.setOnClickListener { peapleAdapter.retry() }
        }
    }


}



