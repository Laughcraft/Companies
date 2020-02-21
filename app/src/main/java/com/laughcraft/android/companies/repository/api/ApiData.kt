package com.laughcraft.android.companies.repository.api

import com.laughcraft.android.companies.repository.DataSource
import com.laughcraft.android.companies.repository.entity.CompanyInfo
import com.laughcraft.android.companies.repository.entity.CompanyName
import kotlin.reflect.KClass

object ApiData {
    fun <Entity : Any> of(clazz: KClass<*>): DataSource<Entity> {
        return when (clazz) {
            CompanyInfo::class -> CompanyInfoApiData()
            CompanyName::class -> CompanyNameApiData()
            else -> throw IllegalArgumentException("Unsupported data type")
        } as DataSource<Entity>
    }
}