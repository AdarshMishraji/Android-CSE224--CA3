package com.example.ca_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name= findViewById<EditText>(R.id.name)
        val email= findViewById<EditText>(R.id.email)
        val spinner: Spinner = findViewById(R.id.qual_spinner)
        val submitBtn = findViewById<Button>(R.id.button)
        val maleRadio = findViewById<RadioButton>(R.id.male_btn)
        val femaleRadio = findViewById<RadioButton>(R.id.female_btn)
        val tnc = findViewById<CheckBox>(R.id.tnc)

        ArrayAdapter.createFromResource(
            this,
            R.array.qual_items,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        name.setOnFocusChangeListener { _, hasFocus -> if (hasFocus) {
                    name.setBackgroundResource(R.drawable.border)
                } else {
                    name.setBackgroundResource(R.color.background)
                }
            }

        email.setOnFocusChangeListener { _, hasFocus -> if (hasFocus) {
                    email.setBackgroundResource(R.drawable.border)
                } else {
                    email.setBackgroundResource(R.color.background)
                }
            }

        submitBtn.setOnClickListener {
            if (name.text.isNullOrBlank()) {
                name.setBackgroundResource(R.drawable.border_error)
                return@setOnClickListener showSnack(it, "Empty Name field.")
            }

            if (email.text.isNullOrBlank() or !isValidEmail(email.text.toString())){
                email.setBackgroundResource(R.drawable.border_error)
                return@setOnClickListener showSnack(it, "Invalid Email")
            }

            if (!tnc.isChecked) return@setOnClickListener showSnack(it, "TNC must be checked")

            intent = Intent(this, Result::class.java)
            intent.putExtra("name", name.text.toString())
            intent.putExtra("email", email.text.toString())
            intent.putExtra("gender", if (maleRadio.isChecked) "Male" else if (femaleRadio.isChecked) "Female" else null)
            intent.putExtra("highest_qual", spinner.selectedItem.toString())
            startActivity(intent)
        }

    }

    private fun showSnack(context:View, text:String){
        Snackbar.make(context, text, Snackbar.LENGTH_LONG).show()
    }

    private fun isValidEmail (email:String):Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_me -> {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle(R.string.about_me)

                val image = ImageView(this)
                image.setImageResource(R.drawable.dp)
                image.layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                )
                image.setPadding(0,50,0,50)

                dialog.setCustomTitle(image)
                dialog.setMessage(R.string.my_info)
                dialog.setPositiveButton("Close") { dialogInterface, _ -> dialogInterface.dismiss() }
                dialog.show()
            }
            R.id.exit_app -> {
                finish()
            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }
}