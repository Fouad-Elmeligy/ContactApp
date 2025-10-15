package com.example.contactapp.DataBase

import androidx.room.Database
import com.example.contactapp.DataBase.DM.ContactItemDM
import androidx.room.Room
import android.content.Context
import androidx.room.RoomDatabase
import com.example.contactapp.DataBase.ContactItemDAO.ContactDAO

@Database(entities = [ContactItemDM::class], version = 2)
abstract class ContactsDataBase : RoomDatabase() {
    abstract fun getContactDao(): ContactDAO
    companion object{
        @Volatile
        private var INSTANCE: ContactsDataBase?=null
        fun getInstance(context: Context): ContactsDataBase{
            if(INSTANCE==null)
                INSTANCE= Room.databaseBuilder(context, ContactsDataBase::class.java,"ContactsDataBase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration(true)
                    .build()
            return INSTANCE!!
        }
    }

}