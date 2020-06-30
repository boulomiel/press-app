@file:Suppress("DEPRECATION")

package com.moondev.press

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.util.Log
import android.view.View
import java.io.File

val api_key ="aec89f532d7e486c8ea13dc3c87cb4ea"
val base_url = "https://newsapi.org/"



fun isOnline(context: Context) : Boolean{
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }else if (connectivityManager.activeNetwork != null){
            return true
        }
    }
    return false
}


fun formatedDate(publishedAt: String) : String = publishedAt.split("T")[0]


fun animateAndGo(activity: Activity,url : String , view: View){
    val runnable =  Runnable {
        val intent =  Intent()
        intent.data = Uri.parse(url)
        intent.action = Intent.ACTION_VIEW
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        activity.startActivity(intent)
    }

    view.animate().scaleY(-2f).scaleY(-2f).translationX(1200.0f).setDuration(500).withEndAction(runnable)
}


fun deleteCache(context: Context) {
    try {
        val dir: File = context.cacheDir
        deleteDir(dir)
    } catch (e: Exception) {
    }
}


private fun deleteDir(dir: File?): Boolean {
    return if (dir != null && dir.isDirectory) {
        val children = dir.list()
        for (i in children.indices) {
            val success = deleteDir(File(dir, children[i]))
            if (!success) {
                return false
            }
        }
        dir.delete()
    } else if (dir != null && dir.isFile) {
        dir.delete()
    } else {
        false
    }
}
