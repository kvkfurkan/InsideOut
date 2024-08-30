package dev.furkankavak.insideout

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.furkankavak.insideout.databinding.ActivityMainBinding
import dev.furkankavak.insideout.fragments.ego.EgoViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navHost : NavHostFragment
    private lateinit var navController: NavController
    private val viewModel: EgoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.isBottomNavVisible.observe(this) { isVisible ->
            changeVisibility(isVisible)
        }

        navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController
        binding.bottomNav.setupWithNavController(navController)
    }

    fun getBottomNavigation() : BottomNavigationView{
        return binding.bottomNav
    }

    fun getNavController() : NavController{
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        return navController
    }

    fun changeVisibility(isVisible : Boolean){
        binding.bottomNav.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}