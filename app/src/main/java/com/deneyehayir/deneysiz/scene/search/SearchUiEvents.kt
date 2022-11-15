package com.deneyehayir.deneysiz.scene.search


sealed class SearchDetailUiEvents {
    data class QueryTextChange(var query: String) : SearchDetailUiEvents()
    object ErrorRetryClick : SearchDetailUiEvents()
    object ErrorCloseClick : SearchDetailUiEvents()
    object CancelButtonClick : SearchDetailUiEvents()
}
