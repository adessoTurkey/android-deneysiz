package com.deneyehayir.deneysiz.scene.search

sealed class SearchUiEvents {
    data class QueryTextChange(var query: String) : SearchUiEvents()
    object ErrorRetryClick : SearchUiEvents()
    object ErrorCloseClick : SearchUiEvents()
    object CancelButtonClick : SearchUiEvents()
}
