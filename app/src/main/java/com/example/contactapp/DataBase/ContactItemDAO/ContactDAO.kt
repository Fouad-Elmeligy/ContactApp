package com.example.contactapp.DataBase.ContactItemDAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.contactapp.DataBase.DM.ContactItemDM

@Dao
interface ContactDAO {
    @Insert
    fun insertContactItem(contactItemDM: ContactItemDM)
    @Delete
    fun deleteContactItem(contactItemDM: ContactItemDM)
    @Update
    fun updateContactItem(contactItemDM: ContactItemDM)
    @Query("SELECT * FROM contactitemdm")
    fun getAllContacts(): LiveData<List<ContactItemDM>>
}