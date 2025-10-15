package com.example.contactapp

import android.content.Intent
import android.view.View
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contactapp.DataBase.ContactsDataBase
import com.example.contactapp.DataBase.DM.ContactItemDM
import com.example.contactapp.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    private lateinit var addNewContactItemFragment: AddNewContactItemFragment
    private lateinit var adapter: ContactAdapter
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        binding.addNewContact.setOnClickListener {
            addNewContactItemFragment = AddNewContactItemFragment()
            addNewContactItemFragment.show(supportFragmentManager, null)
        }
    }

    private fun initRecyclerView(contacts: List<ContactItemDM>) {
        adapter = ContactAdapter(contacts.toMutableList())
        binding.contactRecyclerView.adapter = adapter


        adapter.onContactItemClick = object : onContactItemClick {
            override fun onClick(contactItem: ContactItemDM, position: Int) {
                val intent = Intent(this@HomeActivity, DisplayContactItemActivity::class.java)
                intent.putExtra("contactImage", contactItem.contactImage)
                intent.putExtra("contactName", contactItem.contactName)
                intent.putExtra("emailAdress", contactItem.emailAddress)
                intent.putExtra("phoneNumber", contactItem.phoneNumber)
                startActivity(intent)

            }


        }
        adapter.onDeleteItemClick=object : onDeleteItemClick{
            override fun onDeleteClick(
                contactItemClick: ContactItemDM,
                position: Int
            ) {
                ContactsDataBase.getInstance(this@HomeActivity).getContactDao().deleteContactItem(contactItemClick)
                adapter.notifyItemRemoved(position)
            }

        }
    }

    private fun initViews() {
        ContactsDataBase.getInstance(this).getContactDao().getAllContacts()
            .observe(this) { contacts ->

                if (contacts.isNotEmpty()) {
                    binding.noContactAdded.visibility = View.INVISIBLE
                    binding.textNoContactAdded.visibility = View.INVISIBLE
                } else {
                    binding.noContactAdded.visibility = View.VISIBLE
                    binding.textNoContactAdded.visibility = View.VISIBLE
                }

                if (!::adapter.isInitialized) {
                    initRecyclerView(contacts)
                } else {
                    adapter.setNewList(contacts)
                }
            }
    }
}
