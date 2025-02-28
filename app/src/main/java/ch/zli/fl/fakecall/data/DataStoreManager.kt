package ch.zli.fl.fakecall.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

class DataStoreManager(private val context: Context) {

    companion object {
        private val CALLER_KEY = stringPreferencesKey("selectedCaller")
    }

    val selectedCaller: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[CALLER_KEY] ?: "Dad"
    }

    suspend fun setSelectedCaller(caller: String) {
        context.dataStore.edit { preferences ->
            preferences[CALLER_KEY] = caller
        }
    }
}
