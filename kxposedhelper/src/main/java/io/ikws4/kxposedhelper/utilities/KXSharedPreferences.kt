package io.ikws4.kxposedhelper.utilities

import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle

class KXSharedPreferences(
    private val contentResolver: ContentResolver, private val prefName: String
) {
    private val uri =
        Uri.parse("content://io.ikws4.kxposedhelper.utilities.KXSharedPreferencesProvider")

    fun contains(key: String?): Boolean {
        val params = Bundle().apply {
            putString("key", key)
        }
        val returnValue = contentResolver.call(uri, "contains", prefName, params)!!
        return returnValue.getBoolean("returnValue")
    }

    fun getInt(key: String?, defValue: Int): Int {
        val params = Bundle().apply {
            putString("key", key)
            putInt("defValue", defValue)
        }
        val returnValue = contentResolver.call(uri, "getInt", prefName, params)!!
        return returnValue.getInt("returnValue")
    }

    fun getLong(key: String?, defValue: Long): Long {
        val params = Bundle().apply {
            putString("key", key)
            putLong("defValue", defValue)
        }
        val returnValue = contentResolver.call(uri, "getLong", prefName, params)!!
        return returnValue.getLong("returnValue")
    }

    fun getFloat(key: String?, defValue: Float): Float {
        val params = Bundle().apply {
            putString("key", key)
            putFloat("defValue", defValue)
        }
        val returnValue = contentResolver.call(uri, "getFloat", prefName, params)!!
        return returnValue.getFloat("returnValue")
    }

    fun getString(key: String?, defValue: String?): String? {
        val params = Bundle().apply {
            putString("key", key)
            putString("defValue", defValue)
        }
        val returnValue = contentResolver.call(uri, "getString", prefName, params)!!
        return returnValue.getString("returnValue")
    }

    fun getBoolean(key: String?, defValue: Boolean): Boolean {
        val params = Bundle().apply {
            putString("key", key)
            putBoolean("defValue", defValue)
        }
        val returnValue = contentResolver.call(uri, "getBoolean", prefName, params)!!
        return returnValue.getBoolean("returnValue")
    }
}