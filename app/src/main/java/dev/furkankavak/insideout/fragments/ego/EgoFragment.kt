package dev.furkankavak.insideout.fragments.ego

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
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
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaPlayer2: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEgoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeBottomNav()
        updateSwitchStates(viewModel.isEgoEnabled.value ?: true)
        egoSwitchObservable()
        observeSwitches()

        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.slow_down_dude)
        mediaPlayer2 = MediaPlayer.create(requireContext(), R.raw.background_music)

        binding.playButton.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer2.start()
            }
        }

        binding.pauseButton.setOnClickListener {
                mediaPlayer2.pause()
        }

    }

    private fun updateBottomNavigation() {
        val mainActivity = activity as? MainActivity
        mainActivity?.changeVisibility(viewModel.isBottomNavVisible.value ?: false)
    }

    private fun initializeBottomNav(){
        bottomNav = (activity as MainActivity).getBottomNavigation()
        navController = (activity as MainActivity).getNavController()
        addBottomNavItem(R.id.egoFragment, "Sleep", R.drawable.ic_sleep, null)
    }

    private fun addBottomNavItem(fragmentId: Int, title: String, iconRes: Int, switchId : CompoundButton?) {
        if (bottomNavItems.contains(fragmentId)) {
            return
        }
        if (bottomNavItems.size < 5) {
            if (bottomNav.menu.findItem(fragmentId) == null) {
                bottomNav.menu.add(Menu.NONE, fragmentId, Menu.NONE, title).setIcon(iconRes)
                bottomNavItems.add(fragmentId)
            }
        } else {
            switchId?.isChecked = false
            Toast.makeText(requireContext(), "Maximum 5 items allowed in the Bottom Navigation.", Toast.LENGTH_SHORT).show()
            mediaPlayer.start()
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
            clearBottomNavItems()

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
            handleSwitchStateChange(isEnabled, R.id.givingFragment, "Embarrassment", R.drawable.ic_embarrassment, binding.switchGiving)
        }
        viewModel.isHappinessEnabled.observe(viewLifecycleOwner) { isEnabled ->
            handleSwitchStateChange(isEnabled, R.id.happinessFragment, "Sadness", R.drawable.ic_sadness, binding.switchHappiness)
        }
        viewModel.isKindnessEnabled.observe(viewLifecycleOwner) { isEnabled ->
            handleSwitchStateChange(isEnabled, R.id.kindnessFragment, "Anxiety", R.drawable.ic_anxiety, binding.switchKindness)
        }
        viewModel.isOptimismEnabled.observe(viewLifecycleOwner) { isEnabled ->
            handleSwitchStateChange(isEnabled, R.id.optimismFragment, "Anger", R.drawable.ic_anger, binding.switchOptimism)
        }
        viewModel.isRespectEnabled.observe(viewLifecycleOwner) { isEnabled ->
            handleSwitchStateChange(isEnabled, R.id.respectFragment, "Fear", R.drawable.ic_fear, binding.switchRespect)
        }

    }

    private fun checkSwitchStates(){
        binding.switchHappiness.setOnCheckedChangeListener { switchId, isChecked ->
            viewModel.onHappinessSwitchChanged(isChecked,switchId)
        }

        binding.switchKindness.setOnCheckedChangeListener { switchId, isChecked ->
            viewModel.onKindnessSwitchChanged(isChecked,switchId)
        }

        binding.switchOptimism.setOnCheckedChangeListener { switchId, isChecked ->
            viewModel.onOptimismSwitchChanged(isChecked,switchId)
        }

        binding.switchRespect.setOnCheckedChangeListener { switchId, isChecked ->
            viewModel.onRespectSwitchChanged(isChecked,switchId)
        }

        binding.switchGiving.setOnCheckedChangeListener { switchId, isChecked ->
            viewModel.onGivingSwitchChanged(isChecked,switchId)
        }

    }

    private fun handleSwitchStateChange(isEnabled: Boolean, fragmentId: Int, title: String, iconRes: Int, switchId : CompoundButton) {
            if (isEnabled) {
                addBottomNavItem(fragmentId, title, iconRes,switchId)
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
        if (!bottomNavItems.contains(R.id.egoFragment)){
            bottomNav.menu.add(R.id.egoFragment, R.id.egoFragment, Menu.NONE, "Sleep").setIcon(R.drawable.ic_android_black_24dp)
        }

        bottomNavItems.clear()
        bottomNavItems.add(R.id.egoFragment)
    }

    private fun removeBottomNavItem(fragmentId: Int) {
        bottomNav.menu.removeItem(fragmentId)
        bottomNavItems.remove(fragmentId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}