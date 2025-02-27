package ch.zli.fl.fakecall.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel : ViewModel() {

    private val _nextCallTime = MutableStateFlow("00:05")
    val nextCallTime: StateFlow<String> = _nextCallTime

    private val _callers = MutableStateFlow(listOf("Dad", "Mum"))
    val callers: StateFlow<List<String>> = _callers

    private val _selectedCaller = MutableStateFlow(_callers.value.first())
    val selectedCaller: StateFlow<String> = _selectedCaller

    @SuppressLint("DefaultLocale")
    fun updateNextCallTime(hour: Int, minute: Int) {
        _nextCallTime.value = String.format("%02d:%02d", hour, minute)
    }

    fun setSelectedCaller(caller: String) {
        _selectedCaller.value = caller
    }

}
