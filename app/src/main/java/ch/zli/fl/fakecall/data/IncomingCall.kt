package ch.zli.fl.fakecall.data

import kotlinx.serialization.Serializable

@Serializable
data class IncomingCall(val caller: String, val ringtone: String, val vibration: String)
