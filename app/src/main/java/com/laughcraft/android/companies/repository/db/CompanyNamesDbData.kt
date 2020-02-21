package com.laughcraft.android.companies.repository.db

import com.laughcraft.android.companies.repository.DataSource
import com.laughcraft.android.companies.repository.entity.CompanyName
import io.reactivex.Completable
import io.reactivex.Observable

class CompanyNamesDbData(val dao: CompanyDao, val tableName: String) : DataSource<CompanyName> {
    
    override fun getAll(): Observable<List<CompanyName>> {
        val names = dao.getAllCompanyNames()
        
        return names.toObservable()
    }
    
    override fun saveAll(list: List<CompanyName>): Observable<List<CompanyName>> {
        return Completable.fromCallable { dao.insert(*list.toTypedArray()) }
            .andThen(Observable.just(list))
    }
    
    override fun removeAll(list: List<CompanyName>): Completable {
        return Completable.fromCallable { dao.deleteAllNames(list) }
    }
    
    override fun removeAll(): Completable {
        return Completable.fromCallable { dao.deleteAllNames(emptyList()) }
    }
    
    override fun getAll(query: DataSource.Query<CompanyName>): Observable<List<CompanyName>> {
        return dao.getCompanyNameByRawQuery(DbData.sqlWhere(tableName, query.params)).toObservable()
    }
}