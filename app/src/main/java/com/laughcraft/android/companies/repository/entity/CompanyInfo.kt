package com.laughcraft.android.companies.repository.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "company_info")
data class CompanyInfo(@PrimaryKey
                       var id: Int,
                       var description: String,
                       var lat: String,
                       var lon: String,
                       var www: String,
                       var phone: String){
    
    @Ignore var name: String? = null
    @Ignore var img: String? = null
    
    override fun hashCode(): Int {
        return id.hashCode()
    }
    
    override fun equals(other: Any?): Boolean {
        return if (other is CompanyInfo) other.id == id else false
    }
}