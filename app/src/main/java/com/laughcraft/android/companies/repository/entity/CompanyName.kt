package com.laughcraft.android.companies.repository.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company_names")
data class CompanyName(@PrimaryKey
                       var id: Int,
                       var name: String,
                       var img: String){
    
    override fun hashCode(): Int {
        return id.hashCode()
    }
    
    override fun equals(other: Any?): Boolean {
        return if (other is CompanyName) other.id == id else false
    }
}