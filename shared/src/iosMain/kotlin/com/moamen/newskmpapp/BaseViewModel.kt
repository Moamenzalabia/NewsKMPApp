package com.moamen.newskmpapp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.cancel

actual open class BaseViewModel {

    actual val  scope = CoroutineScope(Dispatchers.IO)

    // Remember that when we use the actual expect mechanism,
    // our only obligation is to create actual implementations for all the expected ones,
    // but it is not forbidden to add more functions and more values than the expected
    fun clear() {
        scope.cancel()
    }
}