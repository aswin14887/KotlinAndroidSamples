package com.divallion.clientaidl

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import com.divallion.aidlsample.IMyAidlInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mService: IMyAidlInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindService(Intent().setClassName("com.divallion.aidlsample","com.divallion.aidlsample.GreetingService"), serviveConnection, Service.BIND_AUTO_CREATE)

        ping.setOnClickListener{
            Toast.makeText(this,""+mService?.greetings, Toast.LENGTH_LONG).show()
        }

    }

    //Use object to define anonymous class/abstract class/interface
    val serviveConnection = object: ServiceConnection {

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            mService = IMyAidlInterface.Stub.asInterface(p1)
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            mService = null
        }
    }

}
