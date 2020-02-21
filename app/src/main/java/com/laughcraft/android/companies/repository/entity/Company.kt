package com.laughcraft.android.companies.repository.entity

import androidx.room.Embedded
import androidx.room.Relation

data class Company(@Embedded
                   val companyName: CompanyName,
                   @Relation(parentColumn = "id",
                             entityColumn = "id")
                   val companyInfo: CompanyInfo){
    
    override fun hashCode(): Int {
        return companyName.hashCode()
    }
    
    override fun equals(other: Any?): Boolean {
        return if (other is Company) other.companyInfo.id == companyInfo.id else false
    }
}