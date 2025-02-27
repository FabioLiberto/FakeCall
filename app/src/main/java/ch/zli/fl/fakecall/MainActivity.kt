package ch.zli.fl.fakecall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ch.zli.fl.fakecall.data.AcceptedCall
import ch.zli.fl.fakecall.data.IncomingCall
import ch.zli.fl.fakecall.ui.screen.AcceptedCallScreen
import ch.zli.fl.fakecall.ui.screen.IncomingCallScreen
import ch.zli.fl.fakecall.ui.screen.SettingsScreen
import ch.zli.fl.fakecall.ui.theme.FakeCallTheme
import ch.zli.fl.fakecall.viewmodel.SettingsViewModel
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        settingsViewModel.loadRingtones(this)
        settingsViewModel.loadVibrations()
        setContent {
            FakeCallTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = Settings) {
                    composable<Settings> {
                        SettingsScreen(navController, settingsViewModel)
                    }
                    composable<IncomingCall> { backStackEntry ->
                        val incomingCall: IncomingCall = backStackEntry.toRoute()
                        IncomingCallScreen(navController, incomingCall)
                    }
                    composable<AcceptedCall> { backStackEntry ->
                        val acceptedCall: AcceptedCall = backStackEntry.toRoute()
                        AcceptedCallScreen(navController, acceptedCall)
                    }
                }
            }
        }
    }
}

@Serializable
object Settings
