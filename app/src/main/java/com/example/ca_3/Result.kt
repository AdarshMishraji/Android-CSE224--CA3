package com.example.ca_3

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Result : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result);

        val res_name = findViewById<TextView>(R.id.res_name);
        val res_email = findViewById<TextView>(R.id.res_email);
        val res_gender = findViewById<TextView>(R.id.res_gender);
        val res_highest_qual = findViewById<TextView>(R.id.res_highest_qual);

        val b:Bundle? = intent.extras;

        val name = b?.get("name").toString();
        val email = b?.get("email").toString();
        val gender = b?.get("gender").toString();
        val highest_qual = b?.get("highest_qual").toString();

        res_name.text = name;
        res_email.text = email;
        res_gender.text = gender
        res_highest_qual.text = highest_qual;
    }
}