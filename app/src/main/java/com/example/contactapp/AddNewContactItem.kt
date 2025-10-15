package com.example.contactapp

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.contactapp.DataBase.ContactsDataBase
import com.example.contactapp.DataBase.DM.ContactItemDM
import com.example.contactapp.databinding.FragmentaAddNewContactItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddNewContactItemFragment : BottomSheetDialogFragment() {
    val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
        if (it != null) {
            binding.contactImage.setImageURI(it)
        }else{
            binding.contactImage.setImageResource(R.drawable.person)
        }

    }
    private lateinit var binding: FragmentaAddNewContactItemBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentaAddNewContactItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contactImage.setOnClickListener {
            requestPermission()
        }
        binding.savedContactItem.setOnClickListener {
            if (isValid()){
                if(binding.contactImage.drawable ==null){
                    var image= ImageView(requireContext())
                image.setImageResource(R.drawable.person)
                    ContactsDataBase.getInstance(requireContext()).getContactDao().insertContactItem(
                        ContactItemDM(contactImage = R.drawable.person.toString(), contactName = binding.userName.editText?.text.toString(), emailAddress = binding.userEmail.editText?.text.toString(), phoneNumber = binding.userPhone.editText?.text.toString()))
                }else{
                ContactsDataBase.getInstance(requireContext()).getContactDao().insertContactItem(
                    ContactItemDM(contactImage = binding.contactImage.toString(), contactName = binding.userName.editText?.text.toString(), emailAddress = binding.userEmail.editText?.text.toString(), phoneNumber = binding.userPhone.editText?.text.toString()))
            }}
            dismiss()
        }

    }

    fun isValid(): Boolean {
        var isValid = true
        if (binding.userName.editText?.text.toString()
                .isEmpty() || binding.userName.editText?.text.toString().isBlank()
        ) {
            binding.userName.error = "Enter Valid User Name"
            isValid = false
        } else binding.userName.error = null

        if (binding.userEmail.editText?.text.toString()
                .isEmpty() || binding.userEmail.editText?.text.toString().isBlank()
        ) {
            binding.userEmail.error = "Enter Valid User Name"
            isValid = false
        } else binding.userEmail.error = null

        if (binding.userPhone.editText?.text.toString()
                .isEmpty() || binding.userPhone.editText?.text.toString().isBlank()
        ) {
            binding.userPhone.error = "Enter Valid User Name"
            isValid = false
        } else binding.userPhone.error = null

        return isValid
    }

    fun requestPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                pickImage.launch("image/*")
            }

            shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE) -> showRationaleDialog()
            else -> pickImage.launch("image/*")

        }
    }

    private fun showRationaleDialog() {
        AlertDialog.Builder(requireContext())
            .setMessage("If you Wanted to access the gallery to select specific photo , you should accept permission")
            .setCancelable(false).setPositiveButton("I Agree") { dialog, _ ->
                dialog.dismiss()
                pickImage.launch("image/*")
            }.setNegativeButton("I Deny") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }
}
