package io.ikws4.kxposedhelper.hookhelper

import android.app.Activity
import android.os.Bundle
import io.ikws4.kxposedhelper.KXposedHelpers.findAndHookMethod
import io.ikws4.kxposedhelper.MethodHook


/**
 * 初始化 MainScope，通过对 [Activity] 进行拓展使其具有 MainScope 的特性
 * 让协程运行在 [Activity] 的生命周期内，防止内存泄露
 * 通过 [Activity] 的 launch 方法运行协程即可
 */
object ActivityHookHelper {

    fun onCreate(block: Activity.(Bundle?) -> Unit) {
        findAndHookMethod(
            Activity::class, "onCreate",
            parameterTypes = arrayOf(Bundle::class),
            methodHook = MethodHook { param ->
                val savedInstanceState = param.args[0] as? Bundle

                block(this, savedInstanceState)
            })
    }

    fun onStart(block: Activity.() -> Unit) {
        findAndHookMethod(Activity::class, "onStart", methodHook = MethodHook {
            block(this)
        })
    }

    fun onResume(block: Activity.() -> Unit) {
        findAndHookMethod(Activity::class, "onResume", methodHook = MethodHook {
            block(this)
        })
    }

    fun onPause(block: Activity.() -> Unit) {
        findAndHookMethod(Activity::class, "onPause", methodHook = MethodHook {
            block(this)
        })
    }

    fun onStop(block: Activity.() -> Unit) {
        findAndHookMethod(Activity::class, "onStop", methodHook = MethodHook {
            block(this)
        })
    }

    fun onRestart(block: Activity.() -> Unit) {
        findAndHookMethod(Activity::class, "onRestart", methodHook = MethodHook {
            block(this)
        })
    }

    fun onDestroy(block: Activity.() -> Unit) {
        findAndHookMethod(Activity::class, "onDestroy", methodHook = MethodHook {
            block(this)
        })
    }
}