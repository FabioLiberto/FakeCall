package ch.zli.fl.fakecall.service

import android.content.Context
import android.content.Intent
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.updateAll
import ch.zli.fl.fakecall.Widget
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = Widget()

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        if (intent.action == "ch.zli.fl.fakecall.UPDATE_WIDGET") {
            CoroutineScope(Dispatchers.IO).launch {
                glanceAppWidget.updateAll(context)
            }
        }
    }
}
