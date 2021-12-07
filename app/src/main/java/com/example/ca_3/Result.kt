package com.example.ca_3

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Result : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result)

        val resName = findViewById<TextView>(R.id.res_name)
        val resEmail = findViewById<TextView>(R.id.res_email)
        val resGender = findViewById<TextView>(R.id.res_gender)
        val resHighestQual = findViewById<TextView>(R.id.res_highest_qual)
        val backBtn = findViewById<Button>(R.id.back_button)

        val b:Bundle? = intent.extras

        val name = b?.get("name").toString()
        val email = b?.get("email").toString()
        val gender = b?.get("gender").toString()
        val highest_qual = b?.get("highest_qual").toString()

        resName.text = name
        resEmail.text = email
        resGender.text = gender
        resHighestQual.text = highest_qual

        backBtn.setOnClickListener{
            finish()
        }
    }
}