package com.example.contactapp

import com.example.contactapp.DataBase.DM.ContactItemDM

interface onContactItemClick {
    fun onClick(contactItem: ContactItemDM, position:Int)
}