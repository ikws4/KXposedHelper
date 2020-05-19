package io.ikws4.kxposedhelper

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
/**
 * 对 XposedHelpers 的一个封装，使其具有 Kotlin 语言的一些特性
 */
object KXposedHelpers {

    private fun getParameterJavaTypes(parametersTypes: Array<out KClass<out Any>>): Array<Class<out Any>> {
        val types = parametersTypes.map { it.java }
        return types.toTypedArray()
    }

    fun findClass(className: String, classLoader: ClassLoader): KClass<out Any> {
        return XposedHelpers.findClass(className, classLoader).kotlin
    }

    fun <T : Any> findAndHookConstructor(
        clazz: KClass<T>,
        parameterTypes: Array<out KClass<out Any>> = arrayOf(),
        methodHook: MethodHook<T> = MethodHook()
    ): XC_MethodHook.Unhook {
        return XposedHelpers.findAndHookConstructor(
            clazz.java, *getParameterJavaTypes(parameterTypes), methodHook
        )
    }

    fun findAndHookConstructor(
        className: String,
        classLoader: ClassLoader,
        parameterTypes: Array<out KClass<out Any>> = arrayOf(),
        methodHook: MethodHook<out Any> = MethodHook()
    ): XC_MethodHook.Unhook {
        return XposedHelpers.findAndHookConstructor(
            className, classLoader, *getParameterJavaTypes(parameterTypes), methodHook
        )
    }

    fun <T : Any> findAndHookMethod(
        clazz: KClass<T>,
        methodName: String,
        parameterTypes: Array<out KClass<out Any>> = arrayOf(),
        methodHook: MethodHook<T> = MethodHook()
    ): XC_MethodHook.Unhook {
        return XposedHelpers.findAndHookMethod(
            clazz.java, methodName, *getParameterJavaTypes(parameterTypes), methodHook
        )
    }

    fun findAndHookMethod(
        className: String, classLoader: ClassLoader, methodName: String,
        parameterTypes: Array<out KClass<out Any>> = arrayOf(),
        methodHook: MethodHook<out Any> = MethodHook()
    ): XC_MethodHook.Unhook {
        return XposedHelpers.findAndHookMethod(
            className, classLoader, methodName,
            *getParameterJavaTypes(parameterTypes), methodHook
        )
    }

    fun callStaticMethod(clazz: KClass<out Any>, methodName: String, vararg args: Any): Any? {
        return XposedHelpers.callStaticMethod(clazz.java, methodName, *args)
    }

    fun callStaticMethod(
        clazz: KClass<out Any>, methodName: String, parameterTypes: Array<Class<*>>,
        vararg args: Any
    ): Any? {
        return XposedHelpers.callStaticMethod(clazz.java, methodName, parameterTypes, *args)
    }

    fun callMethod(obj: Any, methodName: String, vararg args: Any?): Any? {
        return XposedHelpers.callMethod(obj, methodName, *args)
    }

    fun callMethod(
        clazz: KClass<out Any>, obj: Any, methodName: String,
        vararg args: Any?
    ): Any? {
        return XposedHelpers.findMethodBestMatch(clazz.java, methodName, *args)
            .invoke(obj, *args)
    }

    fun callMethod(
        obj: Any, methodName: String, parameterTypes: Array<KClass<out Any>>, vararg args: Any?
    ): Any? {
        return XposedHelpers.callMethod(
            obj, methodName, getParameterJavaTypes(parameterTypes), *args
        )
    }

    fun callMethod(
        clazz: KClass<out Any>, obj: Any, methodName: String,
        parameterTypes: Array<KClass<out Any>>, vararg args: Any?
    ): Any? {
        return XposedHelpers.findMethodBestMatch(clazz.java, methodName, parameterTypes, *args)
            .invoke(obj, *args)
    }

    fun newInstance(clazz: KClass<out Any>, vararg args: Any?): Any {
        return XposedHelpers.newInstance(clazz.java, *args)
    }

    fun newInstance(
        clazz: KClass<out Any>, parameterTypes: Array<KClass<out Any>>, vararg args: Any?
    ): Any {
        return XposedHelpers.newInstance(
            clazz.java, getParameterJavaTypes(parameterTypes), *args
        )
    }


    fun getCharField(obj: Any, fieldName: String): Char {
        return XposedHelpers.getCharField(obj, fieldName)
    }

    fun setCharField(obj: Any, fieldName: String, value: Char) {
        XposedHelpers.setCharField(obj, fieldName, value)
    }

    fun getStaticCharField(clazz: KClass<out Any>, fieldName: String): Char {
        return XposedHelpers.getStaticCharField(clazz.java, fieldName)
    }

    fun setStaticCharField(clazz: KClass<out Any>, fieldName: String, value: Char) {
        XposedHelpers.setStaticCharField(clazz.java, fieldName, value)
    }


    fun getBooleanField(obj: Any, fieldName: String): Boolean {
        return XposedHelpers.getBooleanField(obj, fieldName)
    }

    fun setBooleanField(obj: Any, fieldName: String, value: Boolean) {
        XposedHelpers.setBooleanField(obj, fieldName, value)
    }

    fun getStaticBooleanField(clazz: KClass<out Any>, fieldName: String): Boolean {
        return XposedHelpers.getBooleanField(clazz, fieldName)
    }

    fun setStaticBooleanField(clazz: KClass<out Any>, fieldName: String, value: Boolean) {
        XposedHelpers.setBooleanField(clazz, fieldName, value)
    }


    fun getByteField(obj: Any, fieldName: String): Byte {
        return XposedHelpers.getByteField(obj, fieldName)
    }

    fun setByteField(obj: Any, fieldName: String, value: Byte) {
        XposedHelpers.setByteField(obj, fieldName, value)
    }

    fun getStaticByteField(clazz: KClass<out Any>, fieldName: String): Byte {
        return XposedHelpers.getStaticByteField(clazz.java, fieldName)
    }

    fun setStaticByteField(clazz: KClass<out Any>, fieldName: String, value: Byte) {
        XposedHelpers.setStaticByteField(clazz.java, fieldName, value)
    }

    fun getDoubleField(obj: Any, fieldName: String): Double {
        return XposedHelpers.getDoubleField(obj, fieldName)
    }

    fun setDoubleField(obj: Any, fieldName: String, value: Double) {
        return XposedHelpers.setDoubleField(obj, fieldName, value)
    }

    fun getStaticDoubleField(clazz: KClass<out Any>, fieldName: String): Double {
        return XposedHelpers.getStaticDoubleField(clazz.java, fieldName)
    }

    fun setStaticDoubleField(clazz: KClass<out Any>, fieldName: String, value: Double) {
        XposedHelpers.setDoubleField(clazz.java, fieldName, value)
    }


    fun getFloatField(obj: Any, fieldName: String): Float {
        return XposedHelpers.getFloatField(obj, fieldName)
    }

    fun setFloatField(obj: Any, fieldName: String, value: Float) {
        XposedHelpers.setFloatField(obj, fieldName, value)
    }

    fun getStaticFloatField(clazz: KClass<out Any>, fieldName: String): Float {
        return XposedHelpers.getStaticFloatField(clazz.java, fieldName)
    }

    fun setStaticFloatField(clazz: KClass<out Any>, fieldName: String, value: Float) {
        XposedHelpers.setStaticFloatField(clazz.java, fieldName, value)
    }

    fun getIntField(obj: Any, fieldName: String): Int {
        return XposedHelpers.getIntField(obj, fieldName)
    }

    fun setIntField(obj: Any, fieldName: String, value: Int) {
        XposedHelpers.setIntField(obj, fieldName, value)
    }

    fun getStaticIntField(clazz: KClass<out Any>, fieldName: String): Int {
        return XposedHelpers.getStaticIntField(clazz.java, fieldName)
    }

    fun setStaticIntField(clazz: KClass<out Any>, fieldName: String, value: Int) {
        XposedHelpers.setStaticIntField(clazz.java, fieldName, value)
    }

    fun getLongField(obj: Any, fieldName: String): Long {
        return XposedHelpers.getLongField(obj, fieldName)
    }

    fun setLongField(obj: Any, fieldName: String, value: Long) {
        XposedHelpers.setLongField(obj, fieldName, value)
    }

    fun getStaticLongField(clazz: KClass<out Any>, fieldName: String): Long {
        return XposedHelpers.getStaticLongField(clazz.java, fieldName)
    }

    fun setStaticLongField(clazz: KClass<out Any>, fieldName: String, value: Long) {
        XposedHelpers.setStaticLongField(clazz.java, fieldName, value)
    }

    fun getObjectField(obj: Any, fieldName: String): Any? {
        return XposedHelpers.getObjectField(obj, fieldName)
    }

    fun setObjectField(obj: Any, fieldName: String, value: Any?) {
        XposedHelpers.setObjectField(obj, fieldName, value)
    }

    fun getStaticObjectField(clazz: KClass<out Any>, fieldName: String): Any {
        return XposedHelpers.getStaticObjectField(clazz.java, fieldName)
    }

    fun setStaticObjectField(clazz: KClass<out Any>, fieldName: String, value: Any) {
        XposedHelpers.setStaticObjectField(clazz.java, fieldName, value)
    }

    fun getShortField(obj: Any, fieldName: String): Short {
        return XposedHelpers.getShortField(obj, fieldName)
    }

    fun setShortField(obj: Any, fieldName: String, value: Short) {
        XposedHelpers.setShortField(obj, fieldName, value)
    }

    fun getStaticShortField(clazz: KClass<out Any>, fieldName: String): Short {
        return XposedHelpers.getStaticShortField(clazz.java, fieldName)
    }

    fun setStaticShortField(clazz: KClass<out Any>, fieldName: String, value: Short) {
        XposedHelpers.setStaticShortField(clazz.java, fieldName, value)
    }

    fun setAdditionalInstanceField(obj: Any, key: String, value: Any?): Any? {
        return XposedHelpers.setAdditionalInstanceField(obj, key, value)
    }

    fun setAdditionalStaticField(obj: Any, key: String, value: Any?): Any? {
        return XposedHelpers.setAdditionalStaticField(obj, key, value)
    }

    fun setAdditionalStaticField(clazz: KClass<out Any>, key: String, value: Any?): Any? {
        return XposedHelpers.setAdditionalStaticField(clazz.java, key, value)
    }

    fun getAdditionalInstanceField(obj: Any, key: String): Any? {
        return XposedHelpers.getAdditionalInstanceField(obj, key)
    }

    fun getAdditionalStaticField(obj: Any, key: String): Any? {
        return XposedHelpers.getAdditionalStaticField(obj, key)
    }

    fun getAdditionalStaticField(clazz: KClass<out Any>, key: String): Any? {
        return XposedHelpers.getAdditionalStaticField(clazz.java, key)
    }

    fun removeAdditionalInstanceField(obj: Any, key: String): Any? {
        return XposedHelpers.removeAdditionalInstanceField(obj, key)
    }

    fun removeAdditionalStaticField(obj: Any, key: String): Any? {
        return XposedHelpers.removeAdditionalStaticField(obj, key)
    }

    fun removeAdditionalStaticField(clazz: KClass<out Any>, key: String): Any? {
        return XposedHelpers.removeAdditionalStaticField(clazz.java, key)
    }

    fun incrementMethodDepth(method: String) {
        XposedHelpers.incrementMethodDepth(method)
    }

    fun decrementMethodDepth(method: String) {
        XposedHelpers.decrementMethodDepth(method)
    }
}