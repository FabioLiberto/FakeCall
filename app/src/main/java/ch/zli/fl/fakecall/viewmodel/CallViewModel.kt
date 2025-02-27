package ch.zli.fl.fakecall.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CallViewModel : ViewModel() {
    private val _callerName = MutableLiveData<String?>()
    val callerName: LiveData<String?> get() = _callerName

    fun setCallerName(name: String) {
        _callerName.value = name
    }
}
