package com.laughcraft.android.companies.repository

import android.content.Context
import androidx.room.*
import com.laughcraft.android.companies.repository.db.CompanyDao
import com.laughcraft.android.companies.repository.entity.CompanyInfo
import com.laughcraft.android.companies.repository.entity.CompanyName

@Database(
        entities = [
            CompanyInfo::class,
            CompanyName::class
        ],
        version = 1,
        exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCompanyDao(): CompanyDao

    companion object {

        private const val DB_NAME = "companies1.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context.applicationContext).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
    }
}