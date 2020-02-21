package com.laughcraft.android.companies.repository.db

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.laughcraft.android.companies.repository.entity.Company
import com.laughcraft.android.companies.repository.entity.CompanyInfo
import com.laughcraft.android.companies.repository.entity.CompanyName
import io.reactivex.Maybe
import kotlin.reflect.KClass

@Dao
abstract class CompanyDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg names: CompanyName)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg info: CompanyInfo)
    
    @Query("SELECT * FROM company_info WHERE id = :id")
    abstract fun getCompanyInfo(id: Int): Maybe<CompanyInfo>
    
    @Query("SELECT * FROM company_names WHERE id = :id")
    abstract fun getCompanyName(id: Int): Maybe<CompanyName>
    
    @Query("SELECT * FROM company_info")
    abstract fun getAllCompanyInfo(): Maybe<List<CompanyInfo>>
    
    @Query("SELECT * FROM company_names")
    abstract fun getAllCompanyNames(): Maybe<List<CompanyName>>
    
    @Delete
    abstract fun deleteAllInfo(companies: List<CompanyInfo>)
    
    @Delete
    abstract fun deleteAllNames(companies: List<CompanyName>)
    
    @RawQuery
    abstract fun getCompanyNameByRawQuery(query: SupportSQLiteQuery): Maybe<List<CompanyName>>
    
    @RawQuery
    abstract fun getCompanyInfoByRawQuery(query: SupportSQLiteQuery): Maybe<List<CompanyInfo>>

}