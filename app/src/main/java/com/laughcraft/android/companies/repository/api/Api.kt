package com.laughcraft.android.companies.repository.api

import com.laughcraft.android.companies.repository.entity.CompanyInfo
import com.laughcraft.android.companies.repository.entity.CompanyName
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    
    @GET("test.php")
    fun getCompanies(): Observable<List<CompanyName>>
    
    @GET("test.php")
    fun getCompanyInfo(@Query("id") id: Int): Observable<List<CompanyInfo>>
    
}