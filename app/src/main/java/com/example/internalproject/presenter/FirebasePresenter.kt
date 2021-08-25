package com.example.internalproject.presenter

import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FirebasePresenter(val view: View) {

    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val currentUserId = auth.currentUser?.uid


    val userReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("Users")

}