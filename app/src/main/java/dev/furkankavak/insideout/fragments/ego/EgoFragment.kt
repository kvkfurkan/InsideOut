package dev.furkankavak.insideout.fragments.ego

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
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
    private val bottomNavItems = mutableListOf<Int>()

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
        observeSwitches()

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

    private fun addBottomNavItem(fragmentId: Int, title: String, iconRes: Int) {
        if (bottomNavItems.contains(fragmentId)) {
            return // Eğer item zaten eklenmişse, fonksiyonu sonlandır
        }
        if (bottomNavItems.size < 5) {
            if (bottomNav.menu.findItem(fragmentId) == null) {
                bottomNav.menu.add(Menu.NONE, fragmentId, Menu.NONE, title).setIcon(iconRes)
                bottomNavItems.add(fragmentId)
            }
        } else {
            Toast.makeText(requireContext(), "Maximum 5 items allowed in the Bottom Navigation.", Toast.LENGTH_SHORT).show()
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

    private fun observeSwitches() {

        checkSwitchStates()

        viewModel.isGivingEnabled.observe(viewLifecycleOwner) { isEnabled ->
            handleSwitchStateChange(isEnabled, R.id.givingFragment, "Giving", R.drawable.ic_android_black_24dp)
        }
        viewModel.isHappinessEnabled.observe(viewLifecycleOwner) { isEnabled ->
            handleSwitchStateChange(isEnabled, R.id.happinessFragment, "Happiness", R.drawable.ic_android_black_24dp)
        }
        viewModel.isKindnessEnabled.observe(viewLifecycleOwner) { isEnabled ->
            handleSwitchStateChange(isEnabled, R.id.kindnessFragment, "Kindness", R.drawable.ic_android_black_24dp)
        }
        viewModel.isOptimismEnabled.observe(viewLifecycleOwner) { isEnabled ->
            handleSwitchStateChange(isEnabled, R.id.optimismFragment, "Optimism", R.drawable.ic_android_black_24dp)
        }
        viewModel.isRespectEnabled.observe(viewLifecycleOwner) { isEnabled ->
            handleSwitchStateChange(isEnabled, R.id.respectFragment, "Respect", R.drawable.ic_android_black_24dp)
        }

    }

    private fun checkSwitchStates(){
        binding.switchHappiness.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onHappinessSwitchChanged(isChecked)
        }

        binding.switchKindness.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onKindnessSwitchChanged(isChecked)
        }

        binding.switchOptimism.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onOptimismSwitchChanged(isChecked)
        }

        binding.switchRespect.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onRespectSwitchChanged(isChecked)
        }

        binding.switchGiving.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onGivingSwitchChanged(isChecked)
        }

    }

    private fun handleSwitchStateChange(isEnabled: Boolean, fragmentId: Int, title: String, iconRes: Int) {
            if (isEnabled) {
                addBottomNavItem(fragmentId, title, iconRes)
            } else {
                removeBottomNavItem(fragmentId)
            }
    }

    private fun egoSwitchObservable(){
        viewModel.isEgoEnabled.observe(viewLifecycleOwner) { isEnabled ->
            binding.switchEgo.isChecked = isEnabled
            switchEnabledChanger(isEnabled)
        }

        viewModel.clearBottomNavItems.observe(viewLifecycleOwner) { shouldClear ->
            if (shouldClear) {
                clearBottomNavItems()
            }
        }

        binding.switchEgo.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onEgoSwitchChanged(isChecked)
            viewModel.setBottomNavVisibility(!isChecked)
            updateBottomNavigation()
        }
    }

    private fun clearBottomNavItems() {
        for (itemId in bottomNavItems) {
            if (itemId != R.id.egoFragment){
                bottomNav.menu.removeItem(itemId)
            }
        }
        bottomNavItems.clear()
    }

    private fun removeBottomNavItem(fragmentId: Int) {
        bottomNav.menu.removeItem(fragmentId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}