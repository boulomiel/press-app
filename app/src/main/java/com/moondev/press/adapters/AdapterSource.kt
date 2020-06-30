package com.moondev.press.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moondev.press.R
import com.moondev.press.models.Source
import java.lang.StringBuilder

class AdapterSource(val context: Context, val  list: List<Source>?, private val  listener : OnSourceClick) : RecyclerView.Adapter<AdapterSource.ViewHolder>() {


    init {
        Log.e("ADAPTERSOURCE", list!!.size.toString())
    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private var titleView : TextView? = null
        private var descriptionView : TextView? = null
        private var categoryView : TextView? = null
        private var languageView : TextView? = null
        private var readView : Button? = null
        private var frameView : RelativeLayout? =null

        init{
            titleView = itemView.findViewById(R.id.title_source)
            descriptionView = itemView.findViewById(R.id.description_source)
            categoryView = itemView.findViewById(R.id.category_source)
            languageView =  itemView.findViewById(R.id.language_source)
            readView = itemView.findViewById(R.id.link_source)
            frameView = itemView.findViewById(R.id.frame_source)

        }


        fun bind(title :  String?, description : String?, category : String?, language : String?, url: String? ){
            titleView!!.text = title
            descriptionView!!.text = description
            categoryView!!.text = categoryRemake(category)
            languageView!!.text = language
            readView!!.setOnClickListener { listener.transferUrl(url!!,frameView!!) }

        }


        private fun categoryRemake(category: String?) : String{
            val arr = category!!.toCharArray()
            val first = category[0].toUpperCase()
            val builder =  StringBuilder()
            builder.append(first)
            for (i in 1 until arr.size){
                builder.append(arr[i])
            }
            return builder.toString()
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
          val view =  LayoutInflater.from(context).inflate(R.layout.item_source, parent, false)

        return ViewHolder(view)

    }

    override fun getItemCount(): Int  = list!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val source =  list!![position]
        holder.bind(source.name!!, source.description!!, source.category!!, source.language!!.toUpperCase(),source.url!!)

    }

    interface OnSourceClick{

        fun transferUrl(url : String,viewType : View)
    }
}