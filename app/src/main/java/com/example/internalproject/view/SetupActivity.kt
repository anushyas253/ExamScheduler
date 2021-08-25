package com.example.internalproject.view

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.connectin.view.IndvFragment
import com.example.connectin.view.OrgFragment
import com.example.internalproject.R

class SetupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)

    }

    fun radioClicked(view: View) {
        if(view is RadioButton){
            val isChecked = view.isChecked
            if(isChecked){
                when(view.id){
                    R.id.radioButton2 -> {
                        val frag = IndvFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.registerFrame,frag)
                            .commit()
                    }
                    R.id.radioButton3 -> {
                        val frag = OrgFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.registerFrame,frag)
                            .commit()
                    }
                }
            }
        }
    }
}