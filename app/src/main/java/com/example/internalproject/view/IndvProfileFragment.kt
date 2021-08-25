package com.example.internalproject.view

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.internalproject.presenter.FirebasePresenter
import com.example.internalproject.presenter.IndvProfilePresenter

import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class IndvProfileFragment : Fragment() {

    lateinit var reference: FirebasePresenter
    lateinit var profilePresenter : IndvProfilePresenter
    lateinit var currentUserId : String

    lateinit var uploadB : Button
    lateinit var createPostB : FloatingActionButton
    lateinit var editInfo : Button
    lateinit var viewPosts : Button
    lateinit var viewConnections : TextView
    lateinit var viewEndorsements : TextView
    lateinit var viewJobsApplied : TextView
    lateinit var viewFollowing : TextView
    lateinit var userPfp : ImageView

    var galleryPick : Int = 0

    var imgUri:Uri = Uri.parse("")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.example.internalproject.R.layout.indv_self_profile,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initializing presenter reference
        reference = FirebasePresenter(view)
        currentUserId = reference.auth.currentUser!!.uid
        profilePresenter = IndvProfilePresenter(view)

//        uploadB = view.findViewById(R.id.uploadB)
//        createPostB = view.findViewById(R.id.selfCreatePostB)
//        editInfo = view.findViewById(R.id.selfEdit_IV)
//        viewPosts = view.findViewById(R.id.selfViewPostsB)
//        viewConnections = view.findViewById(R.id.selfConnection_TV)
//        viewEndorsements = view.findViewById(R.id.selfEndorsement_TV)
//        viewJobsApplied = view.findViewById(R.id.selfJobsApplied_TV)
//        viewFollowing = view.findViewById(R.id.selfFollowing)




        profilePresenter.populateIndvProfile(reference,currentUserId,requireActivity())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == galleryPick && resultCode == RESULT_OK && data != null) {
            imgUri = data.data!!
           // profilePresenter.uploadtoStorage(reference,currentUserId,imgUri,requireActivity())
            //userPfp.setImageURI(imgUri)
        }
         else {
            Toast.makeText(activity, "Error occured", Toast.LENGTH_SHORT).show()
        }
    }
}