package ch.zli.fl.fakecall

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import ch.zli.fl.fakecall.data.DataStoreManager
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

class Widget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val dataStore = DataStoreManager(context)
        val caller = runBlocking {
            dataStore.selectedCaller.firstOrNull() ?: "Dad"
        }

        provideContent {
            WidgetUI(context, caller)
        }
    }
}

@Composable
fun WidgetUI(context: Context, caller: String) {
    val intent = Intent(context, MainActivity::class.java).apply {
        data = Uri.parse("app://fakecall?caller=$caller")
    }

    Box(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(ColorProvider(Color.White))
            .cornerRadius(16.dp)
            .clickable(actionStartActivity(intent)),
    ) {
        Row(
            modifier = GlanceModifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(modifier = GlanceModifier.defaultWeight()) {
                Text(
                    text = caller,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = ColorProvider(Color.Black),
                    ),
                )
            }

            Image(
                provider = ImageProvider(R.drawable.ic_phone),
                contentDescription = "Call",
                modifier = GlanceModifier.size(40.dp),
            )
        }
    }
}
