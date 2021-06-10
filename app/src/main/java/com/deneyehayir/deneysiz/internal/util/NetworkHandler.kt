package com.deneyehayir.deneysiz.internal.util

import android.content.Context
import com.deneyehayir.deneysiz.internal.extension.isNetworkAvailable
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHandler @Inject constructor(
    @ApplicationContext private val context: Context
) {
    val isConnected: Boolean
        get() = context.isNetworkAvailable()
}
