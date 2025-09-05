package com.example.contactapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contactapp.databinding.ActivityHomeBinding
import kotlin.random.Random

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var contacts: ArrayList<ContactItemDM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        var adapter = ContactAdapter(initiateDate())
        binding.contactRecyclerView.adapter = adapter
        adapter.onContactItemClick = object : onContactItemClick {
            override fun onClick(
                contactItem: ContactItemDM,
                position: Int
            ) {
                var intent = Intent(this@HomeActivity, DisplayContactItemActivity::class.java)
                intent.putExtra("contactImage",contactItem.contactImgae)
                intent.putExtra("contactName",contactItem.contactName)
                intent.putExtra("emailAdress",contactItem.emailAdress)
                intent.putExtra("phoneNumber",contactItem.phoneNumber)
                startActivity(intent)
            }

        }

    }

    fun initiateDate(): ArrayList<ContactItemDM> {
        contacts = ArrayList<ContactItemDM>()


        for (i in 0 until 7) {
            contacts.add(
                ContactItemDM(
                    ( Uri.parse("android.resource://$packageName/${getImage(i)}").toString()),
                    "fouad${i} ",
                    "fouad${i}@gamil.com",
                    "${i * 10342}"
                )
            )
        }
        return contacts;
    }

    fun getImage(i: Int): Int {
        if (i % 2 == 0)
            return R.drawable.lionel
        return R.drawable.anime
    }
}
