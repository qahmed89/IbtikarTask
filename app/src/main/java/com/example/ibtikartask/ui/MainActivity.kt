package com.example.ibtikartask.ui

import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.ibtikartask.R
import com.example.ibtikartask.ui.fragment.MovieFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var navController : NavController ?= null
    @Inject
    lateinit var fragmentFactory: MovieFragmentFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory

        setContentView(R.layout.activity_main)

      navController = findNavController(R.id.fragment)
       setupActionBarWithNavController(this, navController!!);
    }
    override fun onSupportNavigateUp(): Boolean {
        navController?.navigateUp();

        return super.onSupportNavigateUp();
    }
}