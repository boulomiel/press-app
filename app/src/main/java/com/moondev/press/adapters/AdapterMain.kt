package com.moondev.press.adapters

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moondev.press.R
import com.moondev.press.models.Flag
import de.hdodenhof.circleimageview.CircleImageView

class AdapterMain(val context: Context, val list: List<Flag>, private val listener : FlagListener) : RecyclerView.Adapter<AdapterMain.ViewHolder>()  {

    inner class  ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var imageView : CircleImageView? =null

        init{
            imageView = itemView.findViewById(R.id.flag_img)
        }

        fun bind(bitmap: Bitmap){
            imageView!!.setImageBitmap(bitmap)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater.from(context).inflate(R.layout.item_flags, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val flag =  list[position]
        holder.bind(flag.bitmap)
        holder.imageView!!.setOnClickListener { listener.onFlagClick(flag.id, it) }
    }

    interface FlagListener{

        fun onFlagClick(id : String,view: View)
    }

}