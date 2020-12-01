package com.example.d_day_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_data.*

class AddDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)

        back.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            setResult(Activity.RESULT_CANCELED, i)
            finish()
        }

        submit.setOnClickListener {
            val title = add_title.text.toString()
            val content = add_content.text.toString()
            if(title == "" || content == "")
            {
                Toast.makeText(this, "공백 입력하지마세연", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val i = Intent()
            i.putExtra("title", title)
            i.putExtra("content", content)
            setResult(Activity.RESULT_OK, i)
            finish()
        }
    }
}