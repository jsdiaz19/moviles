package com.example.datastorage.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.datastorage.R

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val  name: String = intent.getStringExtra("name")
        val  correo: String = intent.getStringExtra("email")
        val  age: String = intent.getStringExtra("age")
        val nameId = findViewById<TextView>(R.id.nameProfile)
        val ageId = findViewById<TextView>(R.id.ageProfile)
        val correoId = findViewById<TextView>(R.id.emailProfile)
        ageId.text = age
        nameId.text = name
        correoId.text = correo
    }

    fun Volver(view: View)
    {
        val intent = Intent(this, UsersListActivity::class.java)
        startActivity(intent)
    }
}
