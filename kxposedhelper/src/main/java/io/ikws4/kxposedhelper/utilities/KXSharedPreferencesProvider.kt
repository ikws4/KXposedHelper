package io.ikws4.kxposedhelper.utilities

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.database.Cursor
import android.net.Uri
import android.os.Bundle

open class KXSharedPreferencesProvider : ContentProvider() {

    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(): Boolean = true

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = 0

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? = null

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int = 0

    override fun call(method: String, prefName: String?, params: Bundle?): Bundle? {
        if (prefName == null || params == null) return super.call(method, prefName, params)

        sharedPreferences = context?.getSharedPreferences(prefName, Context.MODE_PRIVATE)

        val key = params.getString(KEY)
        val defValue = params.get(DEF_VALUE)

        val returnValue = Bundle()

        if (sharedPreferences != null && key != null && defValue != null) {
            when (method) {
                CONTAINS -> {
                    returnValue.putBoolean(RETURN_VALUE, contains(key))
                }
                GET_INT -> {
                    returnValue.putInt(RETURN_VALUE, getInt(key, defValue as Int))
                }
                GET_LONG -> {
                    returnValue.putLong(RETURN_VALUE, getLong(key, defValue as Long))
                }
                GET_FLOAT -> {
                    returnValue.putFloat(RETURN_VALUE, getFloat(key, defValue as Float))
                }
                GET_STRING -> {
                    returnValue.putString(RETURN_VALUE, getString(key, defValue as String))
                }
                GET_BOOLEAN -> {
                    returnValue.putBoolean(RETURN_VALUE, getBoolean(key, defValue as Boolean))
                }
            }
        }

        return returnValue
    }

    private fun contains(key: String?): Boolean {
        return sharedPreferences!!.contains(key)
    }

    private fun getInt(key: String?, defValue: Int): Int {
        return sharedPreferences!!.getInt(key, defValue)
    }

    private fun getLong(key: String?, defValue: Long): Long {
        return sharedPreferences!!.getLong(key, defValue)
    }

    private fun getFloat(key: String?, defValue: Float): Float {
        return sharedPreferences!!.getFloat(key, defValue)
    }


    private fun getString(key: String?, defValue: String?): String? {
        return sharedPreferences!!.getString(key, defValue)
    }


    private fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return sharedPreferences!!.getBoolean(key, defValue)
    }


    companion object {
        const val KEY = "key"
        const val DEF_VALUE = "defValue"
        const val RETURN_VALUE = "returnValue"

        const val CONTAINS = "contains"

        const val GET_INT = "getInt"
        const val GET_LONG = "getLong"
        const val GET_FLOAT = "getFloat"
        const val GET_STRING = "getString"
        const val GET_BOOLEAN = "getBoolean"
    }
}
