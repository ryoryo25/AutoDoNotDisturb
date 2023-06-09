package com.ryoryo.autodonotdisturb.background

import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.Timer
import java.util.TimerTask

class DoNotDisturbService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        val timer = Timer()
        val task = object : TimerTask() {
            private var count = 0
            override fun run() {
                if (count > 20) {
                    stopSelf()
                    timer.cancel()
                }
                for (appInfo in activityManager.runningAppProcesses) {
                    if (appInfo.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                        Log.i("Background", "Running: " + appInfo.processName)
                    }
                }
                Log.i("Background", "-----$count")
                count ++
            }

        }
        timer.scheduleAtFixedRate(task, 0, 1000)

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}