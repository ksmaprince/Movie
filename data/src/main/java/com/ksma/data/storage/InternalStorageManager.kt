package com.ksma.data.storage

import android.content.Context
import android.content.SharedPreferences

class InternalStorageManager(private val context: Context) {
    private val KEY_USER_ID = "git_user_id"

    private var mSharedPreferences: SharedPreferences = context.getSharedPreferences("Movie DB", Context.MODE_PRIVATE)

    fun clear(){
        val mEditor = mSharedPreferences.edit()
        mEditor.clear()
        mEditor.apply()
    }

    fun saveUserId(id: String){
        mSharedPreferences.edit().putString(KEY_USER_ID,id).apply()
    }

    fun loadUserId(): String{
        return mSharedPreferences.getString(KEY_USER_ID, "")!!
    }
}