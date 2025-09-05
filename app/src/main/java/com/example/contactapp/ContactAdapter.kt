package com.example.contactapp

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.databinding.ContactItemBinding
import com.example.contactapp.onContactItemClick
import java.util.ArrayList

class ContactAdapter(var contacts: ArrayList<ContactItemDM>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private lateinit var binding: ContactItemBinding
     lateinit var onContactItemClick: onContactItemClick
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactViewHolder {
        binding = ContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ContactViewHolder,
        position: Int
    ) {
        var contactItem: ContactItemDM = contacts[position]
        holder.binding.contactImage.setImageURI(Uri.parse(contactItem.contactImgae))
        holder.binding.contactName.text = contactItem.contactName
        holder.binding.phoneNumber.text = contactItem.phoneNumber
        holder.binding.emailAddress.text = contactItem.emailAdress
        holder.binding.root.setOnClickListener{
            onContactItemClick.onClick(contactItem,position)
        }

    }

    override fun getItemCount(): Int = contacts.size


    class ContactViewHolder(var binding: ContactItemBinding) :
        RecyclerView.ViewHolder(binding.root) {}
}