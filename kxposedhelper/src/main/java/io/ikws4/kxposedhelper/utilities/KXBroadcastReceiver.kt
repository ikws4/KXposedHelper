package io.ikws4.kxposedhelper.utilities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

abstract class KXBroadcastReceiver : BroadcastReceiver() {

    abstract val intentFilter: IntentFilter

    private var listener: OnReceiveListener? = null

    override fun onReceive(context: Context, intent: Intent) {
        listener?.onReceive(context, intent)
    }

    fun setOnReceiveListener(onReceive: (context: Context, intent: Intent) -> Unit) {
        this.listener = object : OnReceiveListener {
            override fun onReceive(context: Context, intent: Intent) {
                onReceive(context, intent)
            }
        }
    }

    fun register(context: Context) {
        context.registerReceiver(this, intentFilter)
    }

    fun unRegister(context: Context) {
        context.unregisterReceiver(this)
    }

    interface OnReceiveListener {
        fun onReceive(context: Context, intent: Intent)
    }
}