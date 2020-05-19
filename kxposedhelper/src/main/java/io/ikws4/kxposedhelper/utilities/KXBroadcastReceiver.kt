package io.ikws4.kxposedhelper.utilities

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

abstract class KXBroadcastReceiver {

    abstract val intentFilter: IntentFilter

    private var listener: OnReceiveListener? = null

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            listener?.onReceive(context, intent)
        }
    }

    fun setOnReceiveListener(onReceive: () -> Unit) {
        this.listener = object : OnReceiveListener {
            override fun onReceive(context: Context, intent: Intent) {
                onReceive(context, intent)
            }
        }
    }

    fun register(context: Activity) {
        context.registerReceiver(broadcastReceiver, intentFilter)
    }

    fun unRegister(context: Activity) {
        context.unregisterReceiver(broadcastReceiver)
    }

    interface OnReceiveListener {

        fun onReceive(context: Context, intent: Intent)
    }
}