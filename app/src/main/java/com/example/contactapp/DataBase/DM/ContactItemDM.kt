package com.example.contactapp.DataBase.DM

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contactItemDM")
data class ContactItemDM(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var contactImage: String?=null,
    var contactName: String?=null,
    var emailAddress: String?=null,
    var phoneNumber: String?=null
)