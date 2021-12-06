package com.example.ca_3

import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        val name= findViewById<EditText>(R.id.name);
        val email= findViewById<EditText>(R.id.email);
        val spinner: Spinner = findViewById(R.id.qual_spinner);
        val submitBtn = findViewById<Button>(R.id.button);
        val male_radio = findViewById<RadioButton>(R.id.male_btn);
        val female_radio = findViewById<RadioButton>(R.id.female_btn);
        val tnc = findViewById<CheckBox>(R.id.tnc);

        ArrayAdapter.createFromResource(
            this,
            R.array.qual_items,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.adapter = adapter;
        }

        submitBtn.setOnClickListener {
            if (name.text.isNullOrBlank()) return@setOnClickListener showSnack(it, "Empty Name field.");
            if (email.text.isNullOrBlank() or !isValidEmail(email.text.toString())) return@setOnClickListener showSnack(it, "Invalid Email");
            if (!tnc.isChecked) return@setOnClickListener showSnack(it, "TNC must be checked");

            intent = Intent(this, Result::class.java);
            intent.putExtra("name", name.text.toString());
            intent.putExtra("email", email.text.toString());
            intent.putExtra("gender", if (male_radio.isChecked) "Male" else if (female_radio.isChecked) "Female" else null);
            intent.putExtra("highest_qual", spinner.selectedItem.toString());
            startActivity(intent);
        }

    }

    private fun showSnack(context:View, text:String){
        Snackbar.make(context, text, Snackbar.LENGTH_LONG).show();
    }

    private fun isValidEmail (email:String):Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_me -> {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle(R.string.about_me)

                var image = ImageView(this);
                image.setImageResource(R.drawable.dp);
                image.layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                );
                image.setPadding(0,50,0,50);

                dialog.setCustomTitle(image);
                dialog.setMessage(R.string.my_info);
                dialog.setPositiveButton("Close") { dialogInterface, _ ->
                    {
                        dialogInterface.dismiss();
                    }
                }
                dialog.show();

                true;
            }
            R.id.exit_app -> {
                finish();
                true;
            }
            else -> super.onOptionsItemSelected(item);
        }
        return true;
    }
}