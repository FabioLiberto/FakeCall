package ch.zli.fl.fakecall

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ch.zli.fl.fakecall.ui.screen.AcceptedCallScreen
import ch.zli.fl.fakecall.ui.screen.IncomingCallScreen
import ch.zli.fl.fakecall.ui.screen.SettingsScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "settings") {
        composable("settings") { SettingsScreen(navController) }
        composable("incomingCall") { IncomingCallScreen(navController) }
        composable("acceptedCall") { AcceptedCallScreen(navController) }
    }
}
