package com.example.d_day_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_data.*
import kotlinx.android.synthetic.main.activity_view_data.*

class ViewDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_data)

        val i = Intent()

        val id = intent.getIntExtra("id", 0)
        val title = intent.getStringExtra("title")!!.toString()
        val content = intent.getStringExtra("content")!!.toString()

        view_title.setText(title)
        view_content.setText(content)


        back_view.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            setResult(Activity.RESULT_CANCELED, i)
            finish()
        }

        modify_button.setOnClickListener {
            val title = view_title.text.toString()
            val content = view_content.text.toString()
            if(title == "" || content == "")
            {
                Toast.makeText(this, "공백 입력하지마세연", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            i.putExtra("id", id)
            i.putExtra("title", title)
            i.putExtra("content", content)
            setResult(Activity.RESULT_OK, i)
            finish()
        }

        delete_button.setOnClickListener {
            i.putExtra("id", id)
            setResult(Activity.RESULT_FIRST_USER, i)
            finish()
        }
    }
}