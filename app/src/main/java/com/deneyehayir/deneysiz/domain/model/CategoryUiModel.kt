package com.deneyehayir.deneysiz.domain.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.deneyehayir.deneysiz.data.remote.model.CategoryType
import kotlinx.parcelize.Parcelize

@JvmInline
value class CategoryUiModel(
    val items: List<CategoryItemUiModel>
) : UiModel

@Parcelize
data class CategoryItemUiModel(
    val type: CategoryType,
    @StringRes val nameResource: Int,
    @DrawableRes val imageResource: Int
) : UiModel, Parcelable
