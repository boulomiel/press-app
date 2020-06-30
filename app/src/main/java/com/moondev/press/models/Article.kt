package com.moondev.press.models

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)


class ListOfArticle(    val status : String,
                        val totalResults : String,
                        @SerializedName("articles")
                        val list: MutableList<Article> ){

}




class Article (


    val name: String,
    val author : String,
    val title : String,
    val description : String,
    val url :String,
    val urlToImage:String,
    val publishedAt: String,
    val content : String?
){

    override fun toString(): String {
        return "Article(author='$author', title='$title', " +
                "description='$description', url='$url'," +
                " urlToImage='$urlToImage', publishedAt='$publishedAt'" +
                ", content=$content)"
    }




}



