package com.deneyehayir.deneysiz.scene.discover.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.domain.model.CategoryDomainModel
import com.deneyehayir.deneysiz.domain.model.CategoryItemDomainModel
import com.deneyehayir.deneysiz.domain.model.CategoryType

data class CategoryUiModel(
    val headerItem: CategoryItemUiModel,
    val listItems: List<CategoryItemUiModel>,
) {

    val windowedListItems = listItems.windowed(2, 2, true)

    companion object {
        val Empty = CategoryUiModel(
            headerItem = CategoryItemUiModel.Empty,
            listItems = emptyList()
        )
    }
}

data class CategoryItemUiModel(
    val type: CategoryType,
    @StringRes val nameResource: Int,
    @DrawableRes val imageResource: Int
) {

    companion object {
        val Empty = CategoryItemUiModel(
            type = CategoryType.INVALID,
            nameResource = R.string.empty,
            imageResource = ResourcesCompat.ID_NULL
        )
    }
}

fun CategoryDomainModel.toUiModel() = CategoryUiModel(
    headerItem = items.getOrNull(0)?.toUiModel() ?: CategoryItemUiModel.Empty,
    listItems = items.takeIf { itemList -> itemList.isNotEmpty() }
        ?.filterIndexed { index, _ -> index > 0 }
        ?.map { domain -> domain.toUiModel() }
        .orEmpty()
)

fun CategoryItemDomainModel.toUiModel() = CategoryItemUiModel(
    type = type,
    nameResource = type.toCategoryStringRes(),
    imageResource = type.toCategoryDrawableRes()
)

@StringRes
private fun CategoryType.toCategoryStringRes(): Int = when (this) {
    CategoryType.ALL_BRANDS -> R.string.discover_category_all_brands
    CategoryType.MAKEUP -> R.string.discover_category_makeup
    CategoryType.HAIR_CARE -> R.string.discover_category_hair_care
    CategoryType.SKIN_CARE -> R.string.discover_category_skin_care
    CategoryType.FRAGRANCE -> R.string.discover_category_fragrance
    CategoryType.PERSONAL_CARE -> R.string.discover_category_personal_care
    CategoryType.DENTAL_CARE -> R.string.discover_category_dental_care
    CategoryType.MOM_BABY_CARE -> R.string.discover_category_mom_baby_care
    CategoryType.HOME_CARE -> R.string.discover_category_home_care
    else -> R.string.empty
}

@DrawableRes
private fun CategoryType.toCategoryDrawableRes(): Int = when (this) {
    CategoryType.ALL_BRANDS -> R.drawable.ic_category_all_brands
    CategoryType.MAKEUP -> R.drawable.ic_category_makeup
    CategoryType.HAIR_CARE -> R.drawable.ic_category_hair_care
    CategoryType.SKIN_CARE -> R.drawable.ic_category_skin_care
    CategoryType.FRAGRANCE -> R.drawable.ic_category_fragrance
    CategoryType.PERSONAL_CARE -> R.drawable.ic_category_personal_care
    CategoryType.DENTAL_CARE -> R.drawable.ic_category_dental_care
    CategoryType.MOM_BABY_CARE -> R.drawable.ic_category_mom_baby_care
    CategoryType.HOME_CARE -> R.drawable.ic_category_home_care
    else -> ResourcesCompat.ID_NULL
}
