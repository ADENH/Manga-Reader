package com.example.enhaacomiclabs.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.enhaacomiclabs.model.Comic
import com.example.enhaacomiclabs.retrofit.IComicAPI
import com.example.enhaacomiclabs.retrofit.RetrofitClient

object Common {

    var selected_comic: Comic?=null

    fun isConnectedToInternet(baseContext: Context?): Boolean {
        var result = false
        val cm = baseContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(cm!=null){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                val networkCapabilities = cm.activeNetwork ?: return false
                val actNw = cm.getNetworkCapabilities(networkCapabilities) ?: return false
                result = when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }else{
                cm.run {
                    cm.activeNetworkInfo?.run {
                        result = when (type) {
                            ConnectivityManager.TYPE_WIFI -> true
                            ConnectivityManager.TYPE_MOBILE -> true
                            ConnectivityManager.TYPE_ETHERNET -> true
                            else -> false
                        }

                    }
                }
            }
        }
        return result
    }

    val api:IComicAPI
        get() {
            val retrofit = RetrofitClient.instance
            return retrofit.create(IComicAPI::class.java)
        }
}