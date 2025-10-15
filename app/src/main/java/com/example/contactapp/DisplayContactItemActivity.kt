package com.example.contactapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contactapp.DataBase.ContactsDataBase
import com.example.contactapp.databinding.ActivityDisplayContactItemBinding

class DisplayContactItemActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayContactItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayContactItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.displayContactImage.setImageURI(Uri.parse(intent.getStringExtra("contactImage")))
        binding.displayEmailAddress.text = intent.getStringExtra("emailAdress")
        binding.displayPhoneNumber.text = intent.getStringExtra("phoneNumber")
        binding.displayContactName.text = intent.getStringExtra("contactName")


    }
}