package dev.furkankavak.insideout.fragments.ego

import android.widget.CompoundButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EgoViewModel : ViewModel() {

    private val _isEgoEnabled = MutableLiveData<Boolean>()
    val isEgoEnabled: LiveData<Boolean> = _isEgoEnabled

    private val _isGivingEnabled = MutableLiveData<Boolean>()
    val isGivingEnabled: LiveData<Boolean> = _isGivingEnabled

    private val _isHappinessEnabled = MutableLiveData<Boolean>()
    val isHappinessEnabled: LiveData<Boolean> = _isHappinessEnabled

    private val _isKindnessEnabled = MutableLiveData<Boolean>()
    val isKindnessEnabled: LiveData<Boolean> = _isKindnessEnabled

    private val _isOptimismEnabled = MutableLiveData<Boolean>()
    val isOptimismEnabled: LiveData<Boolean> = _isOptimismEnabled

    private val _isRespectEnabled = MutableLiveData<Boolean>()
    val isRespectEnabled: LiveData<Boolean> = _isRespectEnabled

    private val _isBottomNavVisible = MutableLiveData(false)
    val isBottomNavVisible: LiveData<Boolean> = _isBottomNavVisible

    private val _clearBottomNavItems = MutableLiveData<Boolean>()
    val clearBottomNavItems: LiveData<Boolean> = _clearBottomNavItems

    private val _showToast = MutableLiveData(false)
    val showToast: LiveData<Boolean> get() = _showToast

    fun setShowToast(value: Boolean) {
        _showToast.value = value
    }

    fun resetShowToast() {
        _showToast.value = false
    }

    private val _isPlaying = MutableLiveData(false)
    val isPlaying: LiveData<Boolean> get() = _isPlaying

    fun setPlaying(value: Boolean) {
        _isPlaying.value = value
    }

    fun setBottomNavVisibility(isVisible: Boolean) {
        _isBottomNavVisible.value = isVisible
    }

    fun onEgoSwitchChanged(isEnabled: Boolean) {
        _isEgoEnabled.value = isEnabled
        if (isEnabled) {
            _isGivingEnabled.value = false
            _isHappinessEnabled.value = false
            _isKindnessEnabled.value = false
            _isOptimismEnabled.value = false
            _isRespectEnabled.value = false
            _clearBottomNavItems.value = true
        }
    }

    fun onHappinessSwitchChanged(isEnabled: Boolean, switchId: CompoundButton) {
        _isHappinessEnabled.value = isEnabled
    }

    fun onKindnessSwitchChanged(isEnabled: Boolean, switchId: CompoundButton) {
        _isKindnessEnabled.value = isEnabled
    }

    fun onGivingSwitchChanged(isEnabled: Boolean, switchId: CompoundButton) {
        _isGivingEnabled.value = isEnabled
    }

    fun onOptimismSwitchChanged(isEnabled: Boolean, switchId: CompoundButton) {
        _isOptimismEnabled.value = isEnabled
    }

    fun onRespectSwitchChanged(isEnabled: Boolean, switchId: CompoundButton) {
        _isRespectEnabled.value = isEnabled
    }

}