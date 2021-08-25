package com.example.internalproject.presenter;

import android.widget.Button
import android.widget.EditText

import com.example.internalproject.R


class OrgPresenter(val view: android.view.View) {

    lateinit var reference : FirebasePresenter

    lateinit var registerB:Button
    lateinit var usernameIndv : EditText
    lateinit var dob : EditText
    lateinit var workexp : EditText
    lateinit var techchoice : EditText
    lateinit var education : EditText

    fun initialiseIndvValues(){
        usernameIndv = view.findViewById(R.id.indvName_EV)
        dob =view.findViewById(R.id.indvDOB_EV)
        registerB= view.findViewById(R.id.indvRegisterB)
        education = view.findViewById(R.id.indvEdu_EV)
        workexp=view.findViewById(R.id.IndvWorkexp_EV)
        techchoice=view.findViewById(R.id.IndvTechChoice_EV)
    }

    lateinit var username : EditText
    lateinit var orgDOB: EditText
    lateinit var orgEdu : EditText
    lateinit var orgRegister : Button

    fun initialiseOrgValues(view: android.view.View) {
        username = view.findViewById(R.id.orgName_EV)
        orgDOB = view.findViewById(R.id.orgDOB_EV)
        orgEdu = view.findViewById(R.id.orgEducation)
        orgRegister = view.findViewById(R.id.orgRegisterB)
    }

    interface View {
        fun registerOrg()
        fun initialiseValues()
    }

}