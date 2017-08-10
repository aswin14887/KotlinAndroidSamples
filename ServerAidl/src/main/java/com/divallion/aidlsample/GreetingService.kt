package com.divallion.aidlsample

import android.app.Service
import android.content.Intent
import android.os.IBinder

class GreetingService: Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(p0: Intent?): IBinder {
        return mBinder
    }

    //Use object to define anonymous class/abstract class/interface
    val mBinder = object : IMyAidlInterface.Stub() {
        override fun getGreetings(): String {
            return "Hello!!! From Server"
        }
    }

}
