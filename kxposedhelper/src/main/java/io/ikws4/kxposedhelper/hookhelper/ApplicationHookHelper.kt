package io.ikws4.kxposedhelper.hookhelper

import android.app.Application
import android.app.Instrumentation
import android.content.Context
import io.ikws4.kxposedhelper.KXposedHelpers.findAndHookMethod
import io.ikws4.kxposedhelper.MethodHook

object ApplicationHookHelper {

    fun onCreate(block: Application.() -> Unit) {
        findAndHookMethod(
            Instrumentation::class,
            "callApplicationOnCreate",
            parameterTypes = arrayOf(Application::class),
            methodHook = MethodHook({
                block(it.args[0] as Application)
            })
        )
    }

    fun attach(block: Application.() -> Unit) {
        findAndHookMethod(
            Application::class, "attach",
            parameterTypes = arrayOf(Context::class),
            methodHook = MethodHook {
                block(this)
            })
    }
}