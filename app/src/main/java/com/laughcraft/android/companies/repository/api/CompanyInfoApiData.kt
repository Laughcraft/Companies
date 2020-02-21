package com.laughcraft.android.companies.repository.api

import com.laughcraft.android.companies.repository.ApiService
import com.laughcraft.android.companies.repository.DataSource
import com.laughcraft.android.companies.repository.Repository
import com.laughcraft.android.companies.repository.entity.CompanyInfo

import io.reactivex.Completable
import io.reactivex.Observable

class CompanyInfoApiData : DataSource<CompanyInfo> {
    
    private val api: Api = ApiService.create(Api::class.java)
    
    override fun getAll(): Observable<List<CompanyInfo>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    override fun getAll(query: DataSource.Query<CompanyInfo>): Observable<List<CompanyInfo>> {
        when {
            query.has(Repository.ID) -> query.get(Repository.ID)?.let { id ->
                return api.getCompanyInfo(id.toInt())
            } ?: throw IllegalArgumentException("Unsupported query $query for UserEntity")
        
            else -> throw IllegalArgumentException("Unsupported query $query for UserEntity")
        }
    }
    
    override fun saveAll(list: List<CompanyInfo>): Observable<List<CompanyInfo>> {
        TODO("not implemented")
    }
    
    override fun removeAll(list: List<CompanyInfo>): Completable {
        TODO("not implemented")
    }
    
    override fun removeAll(): Completable {
        TODO("not implemented")
    }
}