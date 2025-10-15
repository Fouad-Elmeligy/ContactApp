package com.example.contactapp

import com.example.contactapp.DataBase.DM.ContactItemDM

interface onDeleteItemClick {
    fun onDeleteClick(contactItemClick: ContactItemDM,position: Int)
}