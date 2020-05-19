package io.ikws4.kxposedhelper

import de.robv.android.xposed.XC_MethodHook

@Suppress("UNCHECKED_CAST")
open class MethodHook<T>(
    private val beforeHookedMethod: T.(param: MethodHookParam) -> Unit = {},
    private val afterHookedMethod: T.(param: MethodHookParam) -> Unit = {}
) : XC_MethodHook() {

    override fun beforeHookedMethod(param: MethodHookParam) {
        super.beforeHookedMethod(param)
        beforeHookedMethod.invoke(param.thisObject as T, param)
    }

    override fun afterHookedMethod(param: MethodHookParam) {
        super.afterHookedMethod(param)
        afterHookedMethod.invoke(param.thisObject as T, param)
    }
}