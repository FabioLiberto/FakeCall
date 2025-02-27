package ch.zli.fl.fakecall.viewmodel

import android.content.Context
import android.media.RingtoneManager

class RingtoneManagerViewModel {

    fun getAvailableRingtones(context: Context): List<String> {
        val ringtoneManager = RingtoneManager(context)
        ringtoneManager.setType(RingtoneManager.TYPE_RINGTONE)
        val cursor = ringtoneManager.cursor
        val ringtoneTitles = mutableListOf<String>()
        while (cursor.moveToNext()) {
            val title = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX)
            ringtoneTitles.add(title)
        }
        cursor.close()
        println("Ringtones: $ringtoneTitles")
        return ringtoneTitles
    }
}
