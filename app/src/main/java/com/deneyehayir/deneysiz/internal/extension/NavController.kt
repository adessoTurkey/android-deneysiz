package com.deneyehayir.deneysiz.internal.extension

import androidx.navigation.NavController

fun <T> NavController.getResultOnce(keyResult: String, onResult: (T) -> Unit) {
    val valueScreenResult = currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<T>(keyResult)

    valueScreenResult?.value?.let {
        onResult(it)

        currentBackStackEntry
            ?.savedStateHandle
            ?.remove<T>(keyResult)
    }
}
