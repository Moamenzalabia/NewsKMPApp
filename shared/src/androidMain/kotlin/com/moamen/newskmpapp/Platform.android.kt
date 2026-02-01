package com.moamen.newskmpapp
import android.os.Build
import android.util.Log
import kotlin.math.round
import android.content.res.Resources

actual class Platform() {

    actual val osName: String
        get() = "Android"
    actual val osVersion: String
        get() = "${Build.VERSION.SDK_INT}"
    actual val deviceModel: String
        get() = "${Build.MANUFACTURER} ${Build.MODEL}"
    actual val density: Int
        get() = round(Resources.getSystem().displayMetrics.density).toInt()

    actual fun logSystemInfo() {
        Log.d(
            "KMP Demo",
            "($osName, $osVersion, $deviceModel, $density)"
        )
    }
}