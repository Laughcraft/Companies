package com.laughcraft.android.companies

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class App : Application() {
    
    companion object {
        
        lateinit var instance: App
        
        fun appContext(): Context = instance.applicationContext
        
        fun isNetworkAvailable(): Boolean {
            val connectivityManager = appContext().getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
    
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities = connectivityManager.activeNetwork ?: return false
                val activeNetwork = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        
                return when {activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                             activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                             activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    else -> false
                }
            }
            else {
                return connectivityManager.activeNetworkInfo != null &&
                        connectivityManager.activeNetworkInfo!!.isConnectedOrConnecting
            }
        }
    }
    
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}