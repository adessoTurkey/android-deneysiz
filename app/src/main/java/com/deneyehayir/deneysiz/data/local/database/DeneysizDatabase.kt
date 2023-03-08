package com.deneyehayir.deneysiz.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.deneyehayir.deneysiz.data.local.database.dao.BrandsDao
import com.deneyehayir.deneysiz.data.local.database.entity.BrandEntity

@Database(
    entities = [BrandEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DeneysizDatabase : RoomDatabase() {
    abstract fun brandsDao(): BrandsDao
}
