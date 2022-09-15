package com.deneyehayir.deneysiz.internal.extension

import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.data.remote.NoConnectivityError
import com.deneyehayir.deneysiz.scene.component.ErrorContentUiModel

fun Throwable.toErrorContentUiModel() = ErrorContentUiModel(
    imageIconRes = if (this is NoConnectivityError) {
        R.drawable.ic_dialog_no_internet
    } else {
        R.drawable.ic_dialog_error
    },
    titleRes = if (this is NoConnectivityError) {
        R.string.error_dialog_title_no_internet
    } else {
        R.string.error_dialog_title_error
    },
    descriptionRes = if (this is NoConnectivityError) {
        R.string.error_dialog_description_no_internet
    } else {
        R.string.error_dialog_description_error
    },
    buttonTextRes = R.string.error_dialog_button_text
)
