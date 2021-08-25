package com.example.connectin.view

import android.content.Context.MODE_PRIVATE
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.internalproject.R
import com.example.internalproject.presenter.FirebasePresenter
import com.example.internalproject.view.NavigationActivity

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.individual_registration_layout.*


class IndvFragment :Fragment() {

    lateinit var reference: FirebasePresenter

    lateinit var currentUserId : String

    lateinit var registerB:Button
    lateinit var username : EditText
    lateinit var dob : EditText
    lateinit var education : EditText
    lateinit var techtrain : EditText
    lateinit var techchoice : EditText
    lateinit var workexp : EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.individual_registration_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initializing presenter reference
        reference = FirebasePresenter(view)
        currentUserId = reference.auth.currentUser!!.uid


        username = view.findViewById(R.id.indvName_EV)
        dob =view.findViewById(R.id.indvDOB_EV)
        education =view.findViewById(R.id.indvEdu_EV)
        registerB= view.findViewById(R.id.indvRegisterB)
        techchoice = view.findViewById(R.id.IndvTechChoice_EV)
        workexp=view.findViewById(R.id.IndvWorkexp_EV)
        techtrain=view.findViewById(R.id.indvTECHTRAIN_EV)

        var indvName = username.text
        var indvDOB = dob.text
        var indvEdu = education.text
        var indvtechchoice=techchoice.text
        var indvworkexp=workexp.text
        var indvtechtrain=techtrain.text




        registerB.setOnClickListener {
            if(indvName.isEmpty()) Toast.makeText(activity,"Please enter valid name",Toast.LENGTH_LONG).show()
            if(indvDOB.isEmpty()) Toast.makeText(activity,"Please enter valid date of birth",Toast.LENGTH_LONG).show()
            if(indvEdu.isEmpty()) Toast.makeText(activity,"Please enter valid education",Toast.LENGTH_LONG).show()
            if(indvtechchoice.isEmpty()) Toast.makeText(activity,"Please enter valid technology Choice",Toast.LENGTH_LONG).show()
            if(indvworkexp.isEmpty()) Toast.makeText(activity,"Please enter valid work experience",Toast.LENGTH_LONG).show()
            if(indvtechtrain.isEmpty()) Toast.makeText(activity,"Please enter valid Technology training",Toast.LENGTH_LONG).show()

            else {
                val hm = HashMap<String,Any>()
                hm["username"] = indvName.toString()
                hm["dateOfBirth"] = indvDOB.toString()
                hm["education"] = indvEdu.toString()
                hm["technology training"] = indvtechtrain.toString()
                hm["accountType"] = "individual"
                hm["workexp"] = indvworkexp.toString()
                hm["technology choice"] = indvtechchoice.toString()


                reference.userReference.child(currentUserId).updateChildren(hm).addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        Toast.makeText(activity,"Your account is successfully created",Toast.LENGTH_SHORT).show()
                        val i = Intent(activity, NavigationActivity::class.java)
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(i)
                        activity?.finish()
                    } else Toast.makeText(activity,"Error: ${it.exception?.message}",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
