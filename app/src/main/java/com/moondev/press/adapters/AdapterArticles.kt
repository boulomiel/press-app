package com.moondev.press.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moondev.press.R
import com.moondev.press.formatedDate
import com.moondev.press.models.Article

class AdapterArticles(val list: List<Article>, val context: Context, val listener : OnClickReadMore )  : RecyclerView.Adapter<AdapterArticles.ViewHolderActicle>(){


    inner class ViewHolderActicle(itemView : View) : RecyclerView.ViewHolder(itemView){
        private var titleView : TextView? = null
        private var imageView : ImageView? =null
        private var descriptionView : TextView? =null
        private var dateView : TextView? = null
        private var buttonView : Button? = null
        private var urlView :  TextView? = null
         private var frameView : FrameLayout? =null

        init{
            titleView =  itemView.findViewById(R.id.title_article)
            imageView = itemView.findViewById(R.id.image_article)
            descriptionView = itemView.findViewById(R.id.description_article)
            dateView = itemView.findViewById(R.id.date_article)
            buttonView = itemView.findViewById(R.id.read_article_button)
            urlView = itemView.findViewById(R.id.url_article)
            frameView = itemView.findViewById(R.id.frame_article)

            buttonView!!.setOnClickListener {
                listener.openLink(urlView!!.text.toString(),frameView!!)
            }
        }

        fun bind(title :  String?, urlImage : String?, description :  String?, date : String?, url: String? ){

            titleView!!.text = title
            descriptionView!!.text =  description
            dateView!!.text = date
            urlView!!.text = url
            if(urlImage != null){
                Glide.with(context).load(urlImage).into(imageView!!)
            }else{
                imageView!!.setImageResource(R.drawable.camera)
            }
        }





    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderActicle {
        val view =  LayoutInflater.from(context).inflate(R.layout.item_article,parent,false)
        return ViewHolderActicle(view)
    }

    override fun getItemCount(): Int  = list.size

    override fun onBindViewHolder(holder: ViewHolderActicle, position: Int) {
        val article =  list[position]

        holder.bind(article.title,article.urlToImage,article.description, formatedDate(article.publishedAt),article.url)
    }

    interface OnClickReadMore{
        fun openLink( url : String, view: View){}
    }



}

