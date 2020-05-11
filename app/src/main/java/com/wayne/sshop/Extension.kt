package com.wayne.sshop

import android.app.Activity
import android.content.Context

fun Activity.setNickcname(nick: String){
    getSharedPreferences("shop",Context.MODE_PRIVATE)
        .edit()
        .putString("Nickname",nick)
        .apply()
}
fun Activity.getNickname():String?{
    return getSharedPreferences("shop",Context.MODE_PRIVATE)
        .getString("Nickname"," ")
}