package ch.zli.fl.fakecall.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SettingsViewModel : ViewModel() {

    private val _nextCallTime = MutableStateFlow("00:05")
    val nextCallTime: StateFlow<String> = _nextCallTime

    private val _callers = MutableStateFlow(listOf("Dad", "Mum"))
    val callers: StateFlow<List<String>> = _callers

    private val _selectedCaller = MutableStateFlow(_callers.value.first())
    val selectedCaller: StateFlow<String> = _selectedCaller

    private val _ringtones = MutableStateFlow(listOf(""))
    val ringtones: StateFlow<List<String>> = _ringtones

    private val _selectedRingtone = MutableStateFlow(_ringtones.value.first())
    val selectedRingtone: StateFlow<String> = _selectedRingtone

    private val _vibrations = MutableStateFlow<Map<String, Int>>(emptyMap())
    val vibrations: StateFlow<List<String>> = _vibrations.map { it.keys.toList() }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _selectedVibration = MutableStateFlow("")
    val selectedVibration: StateFlow<String> = _selectedVibration

    @SuppressLint("DefaultLocale")
    fun updateNextCallTime(hour: Int, minute: Int) {
        _nextCallTime.value = String.format("%02d:%02d", hour, minute)
    }

    fun setSelectedCaller(caller: String) {
        _selectedCaller.value = caller
    }

    fun setSelectedRingtone(ringtone: String) {
        _selectedRingtone.value = ringtone
    }

    fun setSelectedVibration(vibration: String) {
        _selectedVibration.value = vibration
    }

    fun loadRingtones(context: Context) {
        _ringtones.value = RingtoneManagerViewModel().getAvailableRingtones(context)
    }

    fun loadVibrations() {
        _vibrations.value = VibrationManagerViewModel().loadVibrationPatterns()
    }
}
