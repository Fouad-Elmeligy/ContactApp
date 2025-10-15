package com.example.contactapp

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.DataBase.DM.ContactItemDM
import com.example.contactapp.databinding.ContactItemBinding
import com.example.contactapp.onContactItemClick


class ContactAdapter(var contacts: List<ContactItemDM>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private lateinit var binding: ContactItemBinding
    lateinit var onContactItemClick: onContactItemClick
    lateinit var onDeleteItemClick: onDeleteItemClick
    fun setNewList(newContactsList: List<ContactItemDM>){
        contacts=newContactsList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactViewHolder {
        binding = ContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactAdapter.ContactViewHolder, position: Int) {
        var contactItem: ContactItemDM = contacts[position]
        holder.bind(contactItem, position)
        holder.binding.root.setOnClickListener {
            onContactItemClick.onClick(contactItem, position)

        }
        holder.binding.deleteButton.setOnClickListener {
            onDeleteItemClick.onDeleteClick(contactItem,position)
        }

    }

    override fun getItemCount(): Int {
        return contacts.size
    }


    class ContactViewHolder(var binding: ContactItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contactItem: ContactItemDM, position: Int) {
            binding.contactImage.setImageURI(Uri.parse(contactItem.contactImage))
            binding.contactName.text = contactItem.contactName
            binding.phoneNumber.text = contactItem.phoneNumber
            binding.emailAddress.text = contactItem.emailAddress

        }
    }
}