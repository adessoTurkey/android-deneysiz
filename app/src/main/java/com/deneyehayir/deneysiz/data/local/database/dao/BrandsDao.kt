package com.deneyehayir.deneysiz.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.deneyehayir.deneysiz.data.local.database.entity.BrandEntity

@Dao
interface BrandsDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBrandToFavorite(brandEntity: BrandEntity): Long

    @Transaction
    @Query("SELECT * FROM brands")
    suspend fun fetchFavoriteBrands() : List<BrandEntity>

    @Transaction
    @Query("DELETE FROM brands WHERE brand_id = :brandId")
    suspend fun deleteBrandFromFavorite(brandId: Int)

}
