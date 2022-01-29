package com.deneyehayir.deneysiz.internal.extension

const val PREFIX_HTTPS = "https://"
const val PREFIX_HTTP = "http://"
const val PREFIX_WWW = "www."

fun String.getDisplayUrl() = this
    .removePrefix(PREFIX_HTTPS)
    .removePrefix(PREFIX_HTTP)
    .removePrefix(PREFIX_WWW)
