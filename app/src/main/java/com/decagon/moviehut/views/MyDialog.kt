package com.decagon.moviehut.views

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.decagon.moviehut.R
import com.decagon.moviehut.controllers.repositories.URLRepository
import java.lang.ClassCastException

class MyDialog : AppCompatDialogFragment() {


    private lateinit var radioGroup: RadioGroup
    private lateinit var silverbirdRadioButton: RadioButton
    private lateinit var genesisRadioButton: RadioButton
    private lateinit var filmhouseRadioButton: RadioButton
//    private lateinit var listener: MyDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity?.layoutInflater
        val view = inflater!!.inflate(R.layout.dialog, null)
        radioGroup = view.findViewById(R.id.radio_group)
        silverbirdRadioButton = view.findViewById(R.id.radio_button_silver)
        genesisRadioButton = view.findViewById(R.id.radio_button_genesis)
        filmhouseRadioButton = view.findViewById(R.id.radio_button_film_house)

        builder.setTitle("Select Cinema")
            .setView(view)
            .setNegativeButton("Cancel") { dialog, which -> }
            .setPositiveButton("Go") { dialog, which ->
                val site = openPopup()
                loadPage(site)
            }

        return builder.create()
    }

    private fun openPopup(): Int {
        return when (radioGroup.checkedRadioButtonId) {
            R.id.radio_button_genesis -> 1
            R.id.radio_button_silver -> 2
            R.id.radio_button_film_house -> 3
            else -> 0
        }
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//
//        try {
//            listener = context as MyDialogListener
//        } catch (e: ClassCastException) {
//            throw ClassCastException(context.toString()  + "Must implement Mydialoglistener")
//        }
//    }


//    interface MyDialogListener{
//        fun applySelection(site: Int)
//    }

    private fun loadPage(site: Int) {
        val url = when (site) {
            1 -> URLRepository.GENESIS_CINEMA
            2 -> URLRepository.SILVER_BIRD_GALLERIA
            3 -> URLRepository.FILM_HOUSE_CINEMA
            else -> "https://www.google.com"
        }
        val page = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, page)
        if(intent.resolveActivity(activity!!.packageManager) != null){
            startActivity(intent)
        }
    }
}