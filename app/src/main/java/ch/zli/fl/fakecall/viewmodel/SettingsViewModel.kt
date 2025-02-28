package ch.zli.fl.fakecall.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.zli.fl.fakecall.data.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SettingsViewModel(context: Context) : ViewModel() {

    private val dataStore = DataStoreManager(context)

    private val _nextCallTime = MutableStateFlow("00:05")
    val nextCallTime: StateFlow<String> = _nextCallTime

    private val _callers = MutableStateFlow(listOf("Dad", "Mum"))
    val callers: StateFlow<List<String>> = _callers

    private val _selectedCaller = MutableStateFlow("Dad")
    val selectedCaller: StateFlow<String> = _selectedCaller

    init {
        viewModelScope.launch {
            dataStore.selectedCaller.collectLatest { caller ->
                _selectedCaller.value = caller
            }
        }
    }

    @SuppressLint("DefaultLocale")
    fun updateNextCallTime(hour: Int, minute: Int) {
        _nextCallTime.value = String.format("%02d:%02d", hour, minute)
    }

    fun setSelectedCaller(caller: String, context: Context) {
        viewModelScope.launch {
            _selectedCaller.value = caller
            dataStore.setSelectedCaller(caller)

            launch(Dispatchers.Main) {
                val intent = Intent("ch.zli.fl.fakecall.UPDATE_WIDGET")
                context.sendBroadcast(intent)
            }
        }
    }
}
