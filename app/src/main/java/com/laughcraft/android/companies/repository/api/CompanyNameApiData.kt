package com.laughcraft.android.companies.repository.api

import com.laughcraft.android.companies.repository.ApiService
import com.laughcraft.android.companies.repository.DataSource
import com.laughcraft.android.companies.repository.Repository
import com.laughcraft.android.companies.repository.entity.CompanyName

import io.reactivex.Completable
import io.reactivex.Observable

class CompanyNameApiData : DataSource<CompanyName> {

    private val api: Api = ApiService.create(Api::class.java)

    override fun getAll(): Observable<List<CompanyName>> {
        return api.getCompanies()
    }

    override fun getAll(query: DataSource.Query<CompanyName>): Observable<List<CompanyName>> {
        when {
            query.has(Repository.ID) -> query.get(Repository.ID)?.let {id ->
                return api.getCompanyInfo(id.toInt())
                    .flatMap { Observable.fromIterable(it) }
                    .map { item -> CompanyName(item.id,
                                               item.name?: "",
                                               item.img?: "") }
                    .toList()
                    .toObservable()
                
            } ?: throw IllegalArgumentException("Unsupported query $query for UserEntity")

            else -> throw IllegalArgumentException("Unsupported query $query for UserEntity")
        }
    }

    override fun saveAll(list: List<CompanyName>): Observable<List<CompanyName>> {
        TODO("not implemented")
    }

    override fun removeAll(list: List<CompanyName>): Completable {
        TODO("not implemented")
    }

    override fun removeAll(): Completable {
        TODO("not implemented")
    }
}