package com.deneyehayir.deneysiz.internal.extension

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build

val Context.connectivityManager
    get() = (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)

fun Context.isNetworkAvailable(): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        connectivityManager.activeNetwork
        connectivityManager.apply {
            return getNetworkCapabilities(activeNetwork)?.run {
                when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } ?: false
        }
    } else {
        @Suppress("DEPRECATION")
        return connectivityManager.activeNetworkInfo?.isConnected ?: false
    }
}

fun Context.navigateToEmailApp(
    mailAddressRes: Int,
    subjectRes: Int
) {
    val selectorIntent = Intent(Intent.ACTION_SENDTO)
    selectorIntent.data = Uri.parse("mailto:")
    val emailIntent = Intent(Intent.ACTION_SEND)
    emailIntent.putExtra(
        Intent.EXTRA_EMAIL,
        arrayOf(getString(mailAddressRes))
    )
    emailIntent.putExtra(
        Intent.EXTRA_SUBJECT,
        getString(subjectRes)
    )
    emailIntent.selector = selectorIntent

    startActivity(
        Intent.createChooser(
            emailIntent,
            getString(subjectRes)
        )
    )
}

fun Context.openWebPage(url: String) {
    val uri: Uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    startActivity(intent)
}
