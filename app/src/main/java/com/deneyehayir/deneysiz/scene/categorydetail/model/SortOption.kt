package com.deneyehayir.deneysiz.scene.categorydetail.model

import com.deneyehayir.deneysiz.R

enum class SortOption(val nameRes: Int, val sortLabelRes: Int) {
    ALPHABETICAL_ASC(R.string.alphabetical_ascending, R.string.sort_label_alphabetical_ascending),
    ALPHABETICAL_DESC(
        R.string.alphabetical_descending,
        R.string.sort_label_alphabetical_descending
    ),
    SCORE_ASC(R.string.score_ascending, R.string.sort_label_score_ascending),
    SCORE_DESC(R.string.score_descending, R.string.sort_label_score_descending)
}
