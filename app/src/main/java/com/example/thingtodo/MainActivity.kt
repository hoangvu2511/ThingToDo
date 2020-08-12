package com.example.thingtodo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.thingtodo.databinding.ActivityMainBinding
import com.example.thingtodo.ext.setupWithNavController
import com.example.thingtodo.feature.CreateTaskFragment
import com.example.thingtodo.viewmodel.MainActivityViewModel
import com.example.thingtodo.viewmodel.TaskViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private var currentNavController: LiveData<NavController>? = null
    private lateinit var binding: ActivityMainBinding
    private val viewModel : MainActivityViewModel by viewModel()
    private val taskViewModel: TaskViewModel by viewModel()
    private val createTaskFragment = inject<CreateTaskFragment>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            viewModel = this@MainActivity.viewModel
            activity = this@MainActivity
            taskVewModel = this@MainActivity.taskViewModel
        }
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
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.fab_new_task -> {
                createTaskFragment.value.show(
                    supportFragmentManager,
                    CreateTaskFragment::class.simpleName
                )
            }
            R.id.delete_tasks -> {
                taskViewModel.deleteListTask()
            }
            R.id.imgBack -> {
                onBackPressed()
            }
        }
    }
}
