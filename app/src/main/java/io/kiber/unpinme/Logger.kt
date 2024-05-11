package io.kiber.unpinme

import android.util.Log

private const val TAG: String = "UnpinMe"

fun logError(vararg o: Any) {
    Log.e(TAG, o.joinToString(" "))
}

fun logInfo(vararg o: Any) {
    Log.i(TAG, o.joinToString(" "))
}