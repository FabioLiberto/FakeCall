package ch.zli.fl.fakecall.ui.screen

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.Calendar

@SuppressLint("DefaultLocale")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    var nextCallTime by remember {
        mutableStateOf(
            String.format(
                "%02d:%02d",
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
            ),
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "FakeCall", fontSize = 40.sp) },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Next Call", fontSize = 20.sp)
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray)
                        .clickable {
                            val timePicker = TimePickerDialog(
                                context,
                                { _, hour: Int, minute: Int ->
                                    nextCallTime = String.format("%02d:%02d", hour, minute)
                                },
                                calendar.get(Calendar.HOUR_OF_DAY),
                                calendar.get(Calendar.MINUTE),
                                true,
                            )
                            timePicker.show()
                        }
                        .padding(8.dp)
                        .width(80.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = nextCallTime,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column() {
                Text(
                    text = "Calling Person",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 8.dp),
                )

                var mExpanded by remember { mutableStateOf(false) }
                val users = listOf("dad", "mum")
                var selectedUser by remember { mutableStateOf(users.firstOrNull() ?: "") }

                Box {
                    OutlinedTextField(
                        value = selectedUser,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { mExpanded = true },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown Arrow",
                                modifier = Modifier.clickable { mExpanded = true },
                            )
                        },
                    )

                    DropdownMenu(
                        expanded = mExpanded,
                        onDismissRequest = { mExpanded = false },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        users.forEach { user ->
                            DropdownMenuItem(
                                text = { Text(user) },
                                onClick = {
                                    selectedUser = user
                                    mExpanded = false
                                },
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column() {
                Text(
                    text = "Ringtone",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 8.dp),
                )

                var mExpanded by remember { mutableStateOf(false) }
                val ringtones = listOf("Default", "Marimba", "Pixel Sounds", "Chimey Phone")
                var selectedRingtone by remember { mutableStateOf(ringtones.firstOrNull() ?: "") }

                Box {
                    OutlinedTextField(
                        value = selectedRingtone,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { mExpanded = true },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown Arrow",
                                modifier = Modifier.clickable { mExpanded = true },
                            )
                        },
                    )

                    DropdownMenu(
                        expanded = mExpanded,
                        onDismissRequest = { mExpanded = false },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        ringtones.forEach { ringtone ->
                            DropdownMenuItem(
                                text = { Text(ringtone) },
                                onClick = {
                                    selectedRingtone = ringtone
                                    mExpanded = false
                                },
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column() {
                Text(
                    text = "Vibration",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 8.dp),
                )

                var mExpanded by remember { mutableStateOf(false) }
                val vibrations = listOf("Steady Vibration", "Double Pulse", "Heartbeat Pattern")
                var selectedVibration by remember { mutableStateOf(vibrations.firstOrNull() ?: "") }

                Box {
                    OutlinedTextField(
                        value = selectedVibration,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { mExpanded = true },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown Arrow",
                                modifier = Modifier.clickable { mExpanded = true },
                            )
                        },
                    )

                    DropdownMenu(
                        expanded = mExpanded,
                        onDismissRequest = { mExpanded = false },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        vibrations.forEach { vibration ->
                            DropdownMenuItem(
                                text = { Text(vibration) },
                                onClick = {
                                    selectedVibration = vibration
                                    mExpanded = false
                                },
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Preview",
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    navController.navigate("incomingCall")
                },
            )
        }
    }
}
