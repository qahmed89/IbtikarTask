package com.example.ibtikartask.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.RequestManager
import com.example.ibtikartask.R
import com.example.ibtikartask.adapter.ImageAdapter
import com.example.ibtikartask.ui.MovieViewModel
import com.example.shoppinglisttesting.other.Constants
import com.example.shoppinglisttesting.other.Constants.BASE_IMG_URL
import com.example.shoppinglisttesting.other.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject

@AndroidEntryPoint

class DetailsFragment @Inject constructor( val imageAdapter: ImageAdapter ,  val glide :RequestManager): Fragment(R.layout.fragment_details) {
    val args: DetailsFragmentArgs by navArgs()
    lateinit var viewModels: MovieViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModels = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)

        imageData()

        sutupRecyclerView()

        subscribeToObserver()

        imageAdapter.setOnItemClickListener {
            findNavController().navigate(
                DetailsFragmentDirections.actionDetailsFragmentToMovieImageFragment(
                    BASE_IMG_URL + it
                )
            )
        }
    }

    private fun imageData() {
        glide.load(BASE_IMG_URL + args.result.profile_path).into(ivProfile)
        val url = args.result.known_for.map { image -> image.poster_path }
        imageAdapter.image = url
    }

    private fun sutupRecyclerView() {
        rvDetailsFragment.apply {
            adapter = imageAdapter
            layoutManager =
                GridLayoutManager(requireContext(), Constants.GRID_SPAN_COUNT)

        }
        tvName.text = args.result.name
        tvKnowFor.text = args.result.known_for_department
        tvpopulartity.text = args.result.popularity.toString()

    }

    private fun subscribeToObserver() {
        viewModels.getPersonData(args.result.id)
        viewModels.person.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        pbDeatils.visibility = View.GONE

                        tvBio.text = result.data?.biography
                    }
                    Status.ERROR -> {
                        pbDeatils.visibility = View.GONE

                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        pbDeatils.visibility = View.VISIBLE
                    }
                }

            }
        })
    }

}