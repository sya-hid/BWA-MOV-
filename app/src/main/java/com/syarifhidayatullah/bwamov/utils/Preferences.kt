package com.syarifhidayatullah.bwamov.utils

import android.content.Context
import android.content.SharedPreferences

class Preferences (val context: Context){
    companion object{
        const val USER_PREFF="USER_PREFF"
    }
    var sharedPreferences=context.getSharedPreferences(USER_PREFF,0)
    fun setValues(key:String,values:String){
        val editor:SharedPreferences.Editor=sharedPreferences.edit()
        editor.putString(key,values)
        editor.apply()
    }
    fun getValues(key: String):String?{
        return sharedPreferences.getString(key,"")
    }
}