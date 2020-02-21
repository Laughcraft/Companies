package com.laughcraft.android.companies.repository.db

import androidx.sqlite.db.SimpleSQLiteQuery
import com.laughcraft.android.companies.App
import com.laughcraft.android.companies.repository.AppDatabase
import com.laughcraft.android.companies.repository.DataSource
import com.laughcraft.android.companies.repository.entity.CompanyInfo
import com.laughcraft.android.companies.repository.entity.CompanyName

import kotlin.reflect.KClass

object DbData {

    val db: AppDatabase by lazy { AppDatabase.getInstance(App.appContext()) }

    fun <Entity : Any> of(clazz: KClass<*>): DataSource<Entity> {
        return when (clazz) {
            CompanyName::class -> CompanyNamesDbData(db.getCompanyDao(), "company_names")
            CompanyInfo::class -> CompanyInfoDbData(db.getCompanyDao(), "company_info")
            else -> throw IllegalArgumentException("Unsupported data type")
        } as DataSource<Entity>
    }

    fun clearDb() {
        db.clearAllTables()
    }

    // util method for converting PARAMS MAP to sql QUERY with WHERE keyword
    fun sqlWhere(table: String, params: Map<String, String>): SimpleSQLiteQuery {
        var query = "SELECT * FROM $table"
        params.keys.forEachIndexed { i, s ->
            query += if (i == 0) " WHERE" else " AND"
            query += " $s = ?"
        }

        val args = params.values.toTypedArray()
        return SimpleSQLiteQuery(query, args)
    }
}