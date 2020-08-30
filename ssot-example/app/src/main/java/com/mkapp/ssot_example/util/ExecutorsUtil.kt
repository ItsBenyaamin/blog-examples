package com.mkapp.ssot_example.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors

object ExecutorsUtil {
    private val IOThread = Executors.newFixedThreadPool(2)
    private val Main: Handler = Handler(Looper.getMainLooper())

    fun runIO(func: () -> Unit) {
        IOThread.execute(func)
    }

    fun runMain(func: () -> Unit) {
        Main.post(func)
    }

}