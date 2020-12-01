package com.example.d_day_app

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_bar.view.*


class Adapter(val list : List<Recyclerview_item>, val callback : (Recyclerview_item)->Unit) : RecyclerView.Adapter<Adapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recyclerview_bar, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], callback)
    }

    inner class ViewHolder(private val view : View) : RecyclerView.ViewHolder(view)
    {
        fun bind(item : Recyclerview_item, callback : (Recyclerview_item)->Unit)
        {
            view.ID.text = item.id.toString()
            view.title.text = item.title
            view.content.text = item.content
            view.setOnClickListener{
                callback(item)
            }
        }
    }

}