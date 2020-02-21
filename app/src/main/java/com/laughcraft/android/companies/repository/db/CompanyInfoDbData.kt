package com.laughcraft.android.companies.repository.db

import com.laughcraft.android.companies.repository.DataSource
import com.laughcraft.android.companies.repository.entity.CompanyInfo
import io.reactivex.Completable
import io.reactivex.Observable


class CompanyInfoDbData(val dao: CompanyDao, val tableName: String) : DataSource<CompanyInfo> {
    
    override fun getAll(): Observable<List<CompanyInfo>> {
        val infos = dao.getAllCompanyInfo()
        
        return infos.toObservable()
    }
    
    override fun saveAll(list: List<CompanyInfo>): Observable<List<CompanyInfo>> {
        return Completable.fromCallable { dao.insert(*list.toTypedArray()) }
            .andThen(Observable.just(list))
    }
    
    override fun removeAll(list: List<CompanyInfo>): Completable {
        return Completable.fromCallable { dao.deleteAllInfo(list) }
    }
    
    override fun removeAll(): Completable {
        return Completable.fromCallable { dao.deleteAllInfo(emptyList()) }
    }
    
    override fun getAll(query: DataSource.Query<CompanyInfo>): Observable<List<CompanyInfo>> {
        return dao.getCompanyInfoByRawQuery(DbData.sqlWhere(tableName, query.params)).toObservable()
    }
}