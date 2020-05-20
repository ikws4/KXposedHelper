package io.ikws4.kxposedhelper

@Suppress("UNCHECKED_CAST")
open class MethodReplacement<T>(private val replaceHookedMethod: T.(param: MethodHookParam) -> Any? = {}) :
    MethodHook<T>(beforeHookedMethod = { param ->
        try {
            val result = replaceHookedMethod.invoke(param.thisObject as T, param)
            param.result = result
        } catch (t: Throwable) {
            param.throwable = t
        }
    })