package com.moondev.press.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moondev.press.R
import com.moondev.press.models.Icons

class AdapterCategories(val context : Context, private val  icons: Icons) :RecyclerView.Adapter<AdapterCategories.ViewHolder>() {

    inner class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView){
         var title : TextView? = null

        init{
            title = itemView.findViewById(R.id.category_title)


        }

        fun bind( value :  String){
            title!!.text = value
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_category,parent,false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int = icons.listOfBitmaps.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val img =  icons.listOfBitmaps[position]
        holder.bind( img.name)


    }



}