package ch.zli.fl.fakecall

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import ch.zli.fl.fakecall.viewmodel.CallViewModel
import ch.zli.fl.fakecall.viewmodel.SettingsViewModel

class MainActivity : ComponentActivity() {
    private val settingsViewModel: SettingsViewModel by viewModels()
    private val callViewModel: CallViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        hasCheckedOverlayPermission = false
        handleIntent(intent)

        setContent {
            FakeCallTheme {
                val navController = rememberNavController()
                val callerName by callViewModel.callerName.observeAsState()

                LaunchedEffect(callerName) {
                    callerName?.let {
                        navController.navigate(IncomingCall(it))
                    }
                }

                NavHost(navController, startDestination = "settings") {
                    composable("settings") { SettingsScreen(navController, settingsViewModel) }
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

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        val uri = intent.data
        val hasIncomingCall = uri?.getQueryParameter("caller") != null
        if (hasIncomingCall) {
            hasCheckedOverlayPermission = false
        }
        handleIntent(intent)
    }

    override fun onResume() {
        super.onResume()
        if (!hasCheckedOverlayPermission) {
            checkOverlayPermission(intent)
        }
    }

    private var hasCheckedOverlayPermission = false

    @SuppressLint("ObsoleteSdkInt")
    private fun checkOverlayPermission(intent: Intent?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                val uri = intent?.data
                val hasIncomingCall = uri?.getQueryParameter("caller") != null

                if (!hasIncomingCall && !hasCheckedOverlayPermission) {
                    hasCheckedOverlayPermission = true

                    val settingsIntent = Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:$packageName"),
                    )
                    startActivity(settingsIntent)

                    Toast.makeText(this, "Please enable 'Display over other apps' for full functionality", Toast.LENGTH_LONG).show()
                } else {
                    Log.d("MainActivity", "Incoming call detected or permission already checked, skipping permission check.")
                }
            } else {
                Log.d("MainActivity", "Overlay permission granted.")
                hasCheckedOverlayPermission = true
            }
        }
    }

    private fun handleIntent(intent: Intent?) {
        if (intent == null) {
            return
        }

        val uri = intent.data
        val callerName: String? = uri?.getQueryParameter("caller") ?: intent.getStringExtra("callerName")

        if (callerName != null) {
            callViewModel.setCallerName(callerName)
        } else {
            Log.e("MainActivity", "Failed to extract caller name")
        }
    }
}
