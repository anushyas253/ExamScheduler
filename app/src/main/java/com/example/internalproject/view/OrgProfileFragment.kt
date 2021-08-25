package com.example.internalproject.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.example.internalproject.presenter.FirebasePresenter
import com.example.internalproject.presenter.IndvProfilePresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import org.w3c.dom.Text

class OrgProfileFragment : Fragment() {

    lateinit var reference : FirebasePresenter
    lateinit var profileReference: IndvProfilePresenter

    /*var userProfileName : TextView? = null
    var orgProfileInfo : TextView? = null*/
    lateinit var uploadB : Button
    lateinit var orgPfp : ImageView
    lateinit var createJobB : FloatingActionButton
    lateinit var editInfoB : Button
    lateinit var viewJobs : Button
    lateinit var viewFollowers : TextView

    var galleryPick : Int = 0

    var imgUri: Uri = Uri.parse("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.example.internalproject.R.layout.org_self_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initializing presenter reference
        reference = FirebasePresenter(view)
        profileReference = IndvProfilePresenter(view)

        /*userProfileName = view.findViewById(R.id.selfOrgName_TV)
        orgProfileInfo = view.findViewById(R.id.selfOrgInfo_TV)*/
//        createJobB = view.findViewById(R.id.selfOrgCreatePost_FAB)
//        uploadB = view.findViewById(R.id.orguploadB)
//        //orgPfp = view.findViewById(R.id.selfOrgImg_IV)
//        editInfoB = view.findViewById(R.id.selfOrgEditInfoB)
//        viewJobs = view.findViewById(R.id.orgViewPostsB5)
//        viewFollowers = view.findViewById(R.id.orgFollowers)



        profileReference.populateOrgProfile(reference,requireActivity())

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == galleryPick && resultCode == Activity.RESULT_OK && data != null) {
            imgUri = data.data!!

            //userPfp.setImageURI(imgUri)
            //profileReference.uploadtoStorage(reference,reference.currentUserId!!,imgUri,requireActivity())

        } else {
            Toast.makeText(activity, "Error occured", Toast.LENGTH_SHORT).show()
        }
    }

}