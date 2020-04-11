package com.example.thingtodo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator
import com.example.thingtodo.databinding.ActivityMainBinding
import com.example.thingtodo.ext.setupWithNavController
import com.example.thingtodo.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private var currentNavController: LiveData<NavController>? = null
    private lateinit var binding: ActivityMainBinding
    private val viewModel : MainActivityViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        setUpBottomNav()
    }

    override fun onSupportNavigateUp(): Boolean = currentNavController?.value?.navigateUp() ?: false

    private fun setUpBottomNav() {
        val listNav = listOf(
            R.navigation.nav_tasks,
            R.navigation.nav_calendar,
            R.navigation.nav_settings
        )

        currentNavController = binding.bottomNav.setupWithNavController(
            navGraphIds = listNav,
            fragmentManager = supportFragmentManager,
            containerId = R.id.fragmentContainer,
            intent = intent
        )

        currentNavController?.observe(this, Observer {
            viewModel.updateHeader((it.currentDestination as? FragmentNavigator.Destination)?.className ?: return@Observer)
        })

    }
}
