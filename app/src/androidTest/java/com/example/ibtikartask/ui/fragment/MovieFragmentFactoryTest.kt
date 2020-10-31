package com.example.ibtikartask.ui.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.example.ibtikartask.adapter.ImageAdapter
import com.example.ibtikartask.adapter.PeapleAdapter

import javax.inject.Inject


class MovieFragmentFactoryTest @Inject constructor(
        private val imageAdapter: ImageAdapter,
        private  val peapleAdapter:PeapleAdapter,
        private val glide: RequestManager,

) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            PeapleFragment::class.java.name -> PeapleFragment(peapleAdapter)
            DetailsFragment::class.java.name -> DetailsFragment(imageAdapter,glide)
            MovieImageFragment::class.java.name -> MovieImageFragment(glide)

            else -> super.instantiate(classLoader, className)
        }
    }
}