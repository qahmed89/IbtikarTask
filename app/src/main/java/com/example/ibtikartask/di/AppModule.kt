package com.example.shoppinglisttesting.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ibtikartask.R

import com.example.shoppinglisttesting.other.Constants.BASE_URL
import com.example.shoppinglisttesting.remote.MovieAPI
import com.example.shoppinglisttesting.repositories.DefaultMovieRepository
import com.example.shoppinglisttesting.repositories.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun proviedGlide(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)

    )

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        api: MovieAPI
    ) = DefaultMovieRepository(api) as MovieRepository

    @Singleton
    @Provides
    fun proviedMovieAPI(): MovieAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(MovieAPI::class.java)
    }
}
