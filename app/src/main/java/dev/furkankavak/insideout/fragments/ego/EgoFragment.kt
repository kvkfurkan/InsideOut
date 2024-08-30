package dev.furkankavak.insideout.fragments.ego

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.furkankavak.insideout.MainActivity
import dev.furkankavak.insideout.R
import dev.furkankavak.insideout.databinding.FragmentEgoBinding

class EgoFragment : Fragment() {

    private var _binding: FragmentEgoBinding? = null
    private val binding get() = _binding!!
    private lateinit var bottomNav : BottomNavigationView
    private lateinit var navController : NavController
    private val viewModel: EgoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEgoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateSwitchStates(viewModel.isEgoEnabled.value ?: true)
        egoSwitchObservable()
        initializeBottomNav()
    }

    private fun egoSwitchObservable(){
        viewModel.isEgoEnabled.observe(viewLifecycleOwner) { isEnabled ->
            binding.switchEgo.isChecked = isEnabled
            switchEnabledChanger(isEnabled)
        }

        binding.switchEgo.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onEgoSwitchChanged(isChecked)
            viewModel.setBottomNavVisibility(!isChecked)
            updateBottomNavigation()
        }
    }

    private fun updateBottomNavigation() {
        val mainActivity = activity as? MainActivity
        mainActivity?.changeVisibility(viewModel.isBottomNavVisible.value ?: false)
    }

    private fun initializeBottomNav(){
        bottomNav = (activity as MainActivity).getBottomNavigation()
        navController = (activity as MainActivity).getNavController()
        addBottomNavItem(R.id.egoFragment, "Ego", R.drawable.ic_android_black_24dp)
    }

    fun addBottomNavItem(fragmentId: Int, title: String, iconRes: Int) {
        if (bottomNav.menu.findItem(fragmentId) == null) {
            bottomNav.menu.add(Menu.NONE, fragmentId, Menu.NONE, title).setIcon(iconRes)
        }
    }

    private fun switchEnabledChanger(isEgoEnabled: Boolean) {
        if (isEgoEnabled) {
            binding.switchGiving.isChecked = false
            binding.switchHappiness.isChecked = false
            binding.switchKindness.isChecked = false
            binding.switchOptimism.isChecked = false
            binding.switchRespect.isChecked = false

            binding.switchGiving.isClickable = false
            binding.switchHappiness.isClickable = false
            binding.switchKindness.isClickable = false
            binding.switchOptimism.isClickable = false
            binding.switchRespect.isClickable = false
        } else {
            binding.switchGiving.isClickable = true
            binding.switchHappiness.isClickable = true
            binding.switchKindness.isClickable = true
            binding.switchOptimism.isClickable = true
            binding.switchRespect.isClickable = true
        }
    }

    private fun updateSwitchStates(isEgoEnabled: Boolean) {
        binding.switchEgo.isChecked = isEgoEnabled
        switchEnabledChanger(isEgoEnabled)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}