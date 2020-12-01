package com.example.d_day_app

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime


class MainActivity : AppCompatActivity() {

    var list : ArrayList<Recyclerview_item> = arrayListOf()
    lateinit var adapter : Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(applicationContext, LocalDateTime.now().toString(), Toast.LENGTH_SHORT).show()
        // DatePickerDialog
        val listener : DatePickerDialog.OnDateSetListener =
            DatePickerDialog.OnDateSetListener { view : DatePicker, year, monthOfYear, dayOfMonth -> Toast.makeText(applicationContext, "$year 년  ${monthOfYear+1} 월 $dayOfMonth 일", Toast.LENGTH_SHORT).show() }
        val dialog : DatePickerDialog = DatePickerDialog(this, listener, LocalDateTime.now().year, LocalDateTime.now().monthValue - 1, LocalDateTime.now().dayOfMonth)
        dialog.show();

        adapter = Adapter(list){
            onItemClicked(it)
        };

        Thread{
            list.addAll(Database.getInstance(applicationContext).dao().getAllData())
            adapter.notifyDataSetChanged()
        }.start()

        RecyclerView.layoutManager = LinearLayoutManager(this)
        RecyclerView.adapter = adapter



        AddButton.setOnClickListener{
            val i = Intent(this, AddDataActivity::class.java)
            startActivityForResult(i, 404)
        }

        DeleteButton.setOnClickListener {
            val handler = Handler()
            Thread {
                Database.getInstance(applicationContext).dao().deleteAllData()
                list.clear()
                list.addAll(Database.getInstance(applicationContext).dao().getAllData())
                handler.post{
                    adapter.notifyDataSetChanged()
                }
            }.start()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK)
        {
            if(requestCode == 404)
            {
                val title : String? = data?.getStringExtra("title")?.toString()
                val content: String?  = data?.getStringExtra("content")?.toString()
                if(title != null && content != null) {
                    val handler = Handler()
                    Thread {
                        Database.getInstance(applicationContext).dao().insert(Recyclerview_item(0, title, content))
                        list.clear()
                        list.addAll(Database.getInstance(applicationContext).dao().getAllData())
                        handler.post{
                            adapter.notifyDataSetChanged()
                        }
                    }.start()
                }
            }
            else if(requestCode == 500)
            {
                val id = data!!.getIntExtra("id", 0)
                val title : String? = data?.getStringExtra("title")?.toString()
                val content: String?  = data?.getStringExtra("content")?.toString()
                if(title != null && content != null) {
                    val handler = Handler()
                    Thread {
                        Database.getInstance(applicationContext).dao().update(Recyclerview_item(id, title, content))
                        list.clear()
                        list.addAll(Database.getInstance(applicationContext).dao().getAllData())
                        handler.post{
                            adapter.notifyDataSetChanged()
                        }
                    }.start()
                }
            }
        }
        if(resultCode == Activity.RESULT_FIRST_USER)
        {
            if(requestCode == 500)
            {
                val id = data!!.getIntExtra("id", 0)
                val handler = Handler()
                Thread {
                    Database.getInstance(applicationContext).dao().delete(Recyclerview_item(id, "",""))
                    list.clear()
                    list.addAll(Database.getInstance(applicationContext).dao().getAllData())
                    handler.post{
                        adapter.notifyDataSetChanged()
                    }
                }.start()
            }
        }
    }

    fun onItemClicked(item : Recyclerview_item)
    {
        val i = Intent(this, ViewDataActivity::class.java).apply {
            putExtra("id", item.id)
            putExtra("title", item.title)
            putExtra("content", item.content)
        }
        startActivityForResult(i, 500)
    }
}