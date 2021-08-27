package com.deneyehayir.deneysiz.domain.mapper.category

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.data.remote.model.CategoryItemResponse
import com.deneyehayir.deneysiz.data.remote.model.CategoryType
import com.deneyehayir.deneysiz.domain.mapper.UiModelMapper
import com.deneyehayir.deneysiz.domain.model.CategoryItemUiModel
import javax.inject.Inject

class CategoryItemUiModelMapper @Inject constructor() :
    UiModelMapper<CategoryItemUiModel, CategoryItemResponse> {

    override fun mapToUiModel(response: CategoryItemResponse) = with(response) {
        CategoryItemUiModel(
            type = id,
            nameResource = getCategoryNameResource(id),
            imageResource = getCategoryImageResource(id)
        )
    }

    @StringRes
    private fun getCategoryNameResource(type: CategoryType): Int = when (type) {
        CategoryType.ALL_BRANDS -> R.string.discover_category_all_brands
        CategoryType.MAKEUP -> R.string.discover_category_makeup
        CategoryType.HAIR_CARE -> R.string.discover_category_hair_care
        CategoryType.SKIN_CARE -> R.string.discover_category_skin_care
        CategoryType.FRAGRANCE -> R.string.discover_category_fragrance
        CategoryType.PERSONAL_CARE -> R.string.discover_category_personal_care
        CategoryType.DENTAL_CARE -> R.string.discover_category_dental_care
        CategoryType.MOM_BABY_CARE -> R.string.discover_category_mom_baby_care
        CategoryType.HOME_CARE -> R.string.discover_category_home_care
    }

    @DrawableRes
    private fun getCategoryImageResource(type: CategoryType): Int = when (type) {
        CategoryType.ALL_BRANDS -> R.drawable.ic_category_all_brands
        CategoryType.MAKEUP -> R.drawable.ic_category_makeup
        CategoryType.HAIR_CARE -> R.drawable.ic_category_hair_care
        CategoryType.SKIN_CARE -> R.drawable.ic_category_skin_care
        CategoryType.FRAGRANCE -> R.drawable.ic_category_fragrance
        CategoryType.PERSONAL_CARE -> R.drawable.ic_category_personal_care
        CategoryType.DENTAL_CARE -> R.drawable.ic_category_dental_care
        CategoryType.MOM_BABY_CARE -> R.drawable.ic_category_mom_baby_care
        CategoryType.HOME_CARE -> R.drawable.ic_category_home_care
    }
}
