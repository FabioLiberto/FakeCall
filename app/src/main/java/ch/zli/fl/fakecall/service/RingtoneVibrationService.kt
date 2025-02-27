package ch.zli.fl.fakecall.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.os.IBinder
import android.os.VibrationEffect
import android.os.Vibrator

class RingtoneVibrationService : Service() {
    private lateinit var ringtone: Ringtone
    private lateinit var vibrator: Vibrator

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        ringtone = RingtoneManager.getRingtone(applicationContext, ringtoneUri)
        ringtone.play()

        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val pattern = longArrayOf(0, 500, 1000)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createWaveform(pattern, 0))
        } else {
            vibrator.vibrate(pattern, 0)
        }

        return START_STICKY
    }

    override fun onDestroy() {
        ringtone.stop()
        vibrator.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
