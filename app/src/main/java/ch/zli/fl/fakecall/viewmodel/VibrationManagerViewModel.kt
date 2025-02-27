package ch.zli.fl.fakecall.viewmodel

import android.os.Build
import android.os.VibrationEffect

class VibrationManagerViewModel {

    fun loadVibrationPatterns(): Map<String, Int> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            mapOf(
                "Tick" to VibrationEffect.EFFECT_TICK,
                "Click" to VibrationEffect.EFFECT_CLICK,
                "Double-Click" to VibrationEffect.EFFECT_DOUBLE_CLICK,
                "Heavy-Click" to VibrationEffect.EFFECT_HEAVY_CLICK,
            )
        } else {
            mapOf(
                "Custom Short Vibration" to -1,
                "Custom Long Vibration" to -2,
            )
        }
    }
}
