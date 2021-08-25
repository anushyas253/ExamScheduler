package com.example.internalproject.presenter

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.internalproject.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class IndvProfilePresenter(val view: android.view.View) {

    lateinit var nameE : TextView
    lateinit var education : TextView
    lateinit var TechTrain : TextView
    lateinit var WorkExp : TextView
    lateinit var TechChoice : TextView
    lateinit var DOB:TextView


    var userProfileName : TextView? = null
    var orgEdu : TextView? = null
    var orgDOB : TextView? = null


    fun initialiseIndvValues(){
        nameE = view.findViewById(R.id.selfName_EV)
        education = view.findViewById(R.id.selfEducation_EV)
        TechTrain = view.findViewById(R.id.selftechtrain_EV)
        WorkExp=view.findViewById(R.id.selfWorkExp_TV)
        TechChoice=view.findViewById(R.id.selftechchoice_TV)
        DOB=view.findViewById(R.id.selfDOB_TV)

    }

    fun populateIndvProfile(reference: FirebasePresenter, currentUserId:String, activity: Context){
        initialiseIndvValues()
        reference.userReference.child(currentUserId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    if (snapshot.hasChild("accountType")) {
                        val type = snapshot.child("accountType").getValue().toString()
                        if (type.compareTo("individual") == 0) {


                            if (snapshot.hasChild("username")) {
                                val name = snapshot.child("username").getValue().toString()
                                nameE.setText(name)
                            }
                            if (snapshot.hasChild("dateOfBirth") ) {
                                val dob = snapshot.child("dateOfBirth").getValue().toString()
                                DOB.setText("Date of Birth: $dob")
                            }
                            if(snapshot.hasChild("technology training"))
                            {
                             val ttr = snapshot.child("technology training").getValue().toString()
                                TechTrain.setText("$ttr")
                            }

                            if(snapshot.hasChild("technology choice"))
                            {
                                val ttc = snapshot.child("technology choice").getValue().toString()
                                TechChoice.setText("$ttc")
                            }

                            if(snapshot.hasChild("workexp"))
                            {
                                val wexp = snapshot.child("workexp").getValue().toString()
                                WorkExp.setText("$wexp")
                            }

                            if (snapshot.hasChild("education")) {
                                val occ = snapshot.child("education").getValue().toString()
                                education.setText(occ)
                            } else {
                                Toast.makeText(
                                        activity,
                                        "Profile name does not exists!",
                                        Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun initialiseOrgValues(){
        userProfileName = view.findViewById(R.id.selfOrgName_TV)
        orgEdu = view.findViewById(R.id.selfOrgEdu_TV)
        orgDOB=view.findViewById(R.id.selfOrgDOB_TV)

    }

    fun populateOrgProfile(reference: FirebasePresenter, activity: Context){
        initialiseOrgValues()
        reference.userReference.child(reference.currentUserId!!).addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    if(snapshot.hasChild("accountType")) {
                        val type = snapshot.child("accountType").getValue().toString()
                        if (type.compareTo("organisation") == 0) {

                            //validation
                            if (snapshot.hasChild("username")) {
                                val name = snapshot.child("username").getValue().toString()
                                userProfileName?.setText(name)
                            }

                            if (snapshot.hasChild("OrgDOB") ) {
                                val ordob = snapshot.child("OrgDOB").getValue().toString()
                                orgDOB?.setText("Date of Birth: $ordob")
                            }
                            if (snapshot.hasChild("OrgEdu")) {
                                val orged = snapshot.child("OrgEdu").getValue().toString()
                                orgEdu?.setText(orged)


                            }
                            else {
                                Toast.makeText(activity, "Profile name does not exists!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

//    fun uploadtoStorage(reference: FirebasePresenter, currentUserId: String, imgUri: Uri, activity: Context) {
//        val resultUri = imgUri
//
//        val path = reference.userProfileImgRef.child("$currentUserId.jpg")
//
//        path.putFile(resultUri).addOnCompleteListener {
//
//            if (it.isSuccessful) {
//                Toast.makeText(activity, "Profile image stored to database!!",
//                        Toast.LENGTH_SHORT
//                ).show()
//                path.downloadUrl.addOnSuccessListener {
//                    val downloadUrl = it.toString()
//                    reference.userReference.child(currentUserId).child("profileImage").setValue(downloadUrl)
//                            .addOnCompleteListener {
//                                if (it.isSuccessful) {
//                                    Toast.makeText(
//                                            activity,
//                                            "Image stored to firebase database",
//                                            Toast.LENGTH_SHORT
//                                    ).show()
//                                }
//                            }
//                }
//            } else Toast.makeText(
//                    activity,
//                    "Error: ${it.exception?.message}",
//                    Toast.LENGTH_SHORT
//            ).show()
//        }
//        userPfp.setImageURI(imgUri)
//    }


//    fun uploadtoOrgStorage(reference: FirebasePresenter, currentUserId: String, imgUri: Uri, activity: Context) {
//        val resultUri = imgUri
//
//        val path = reference.userProfileImgRef.child("$currentUserId.jpg")
//
//        path.putFile(resultUri).addOnCompleteListener {
//
//            if (it.isSuccessful) {
//                Toast.makeText(activity, "Profile image stored to database!!",
//                        Toast.LENGTH_SHORT
//                ).show()
//                path.downloadUrl.addOnSuccessListener {
//                    val downloadUrl = it.toString()
//                    reference.userReference.child(currentUserId).child("profileImage").setValue(downloadUrl)
//                            .addOnCompleteListener {
//                                if (it.isSuccessful) {
//                                    Toast.makeText(
//                                            activity,
//                                            "Image stored to firebase database",
//                                            Toast.LENGTH_SHORT
//                                    ).show()
//                                }
//                            }
//                }
//            } else Toast.makeText(
//                    activity,
//                    "Error: ${it.exception?.message}",
//                    Toast.LENGTH_SHORT
//            ).show()
//        }
//        orgPfp.setImageURI(imgUri)
//    }

}