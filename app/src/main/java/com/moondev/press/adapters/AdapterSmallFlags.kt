package com.moondev.press.adapters

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.moondev.press.R
import com.moondev.press.models.Flag

class AdapterSmallFlags(val context: Context, val list : List<Flag>, private val listener : OnClickMove) : RecyclerView.Adapter<AdapterSmallFlags.ViewHolder>() {

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var imageView : ImageView? = null
        var frame : MaterialCardView? = null

        init {
            imageView = itemView.findViewById(R.id.small_flag)
            frame = itemView.findViewById(R.id.frame_small_flags)
        }

        fun bind( bitmap : Bitmap){
            imageView!!.setImageBitmap(bitmap)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater.from(context).inflate(R.layout.item_small_flags, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =  list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val flag =  list[position]

        holder.bind(flag.bitmap)

        holder.frame!!.setOnClickListener { listener.onClickMoveTo(position) }

    }


    interface OnClickMove{

        fun onClickMoveTo (pos : Int)
    }
}