package ch.zli.fl.fakecall.ui.screen

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ch.zli.fl.fakecall.Settings
import ch.zli.fl.fakecall.data.AcceptedCall
import ch.zli.fl.fakecall.data.IncomingCall

@Composable
fun IncomingCallScreen(navController: NavController, incomingCall: IncomingCall) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 180.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Call from",
                fontSize = 18.sp,
                color = Color.DarkGray,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = incomingCall.caller,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 64.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE53935)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.CallEnd,
                        contentDescription = "Decline Call",
                        tint = Color.White,
                        modifier = Modifier
                            .size(28.dp)
                            .clickable {
                                navController.navigate(Settings)
                            },
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Decline",
                    fontSize = 14.sp,
                    color = Color.Black,
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF43A047)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Answer Call",
                        tint = Color.White,
                        modifier = Modifier
                            .size(28.dp)
                            .clickable {
                                navController.navigate(AcceptedCall(incomingCall.caller))
                            },
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Answer",
                    fontSize = 14.sp,
                    color = Color.Black,
                )
            }
        }
    }
}
