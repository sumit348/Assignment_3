package com.example.datatransferapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextData = findViewById<EditText>(R.id.editTextData)
        val buttonSend = findViewById<Button>(R.id.buttonSend)

        buttonSend.setOnClickListener {
            val data = editTextData.text.toString()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("EXTRA_DATA", data)
            startActivity(intent)
        }
    }
}
