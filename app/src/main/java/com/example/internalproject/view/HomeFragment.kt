package com.example.internalproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.internalproject.presenter.FirebasePresenter
import com.example.internalproject.presenter.IndvProfilePresenter

class HomeFragment: Fragment() {
    lateinit var reference: FirebasePresenter
    lateinit var profilePresenter : IndvProfilePresenter
    lateinit var currentUserId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var IndvName=profilePresenter.nameE
        var OrgName=profilePresenter.userProfileName
    }

}