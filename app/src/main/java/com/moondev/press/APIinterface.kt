package com.moondev.press

import com.moondev.press.models.ListOfArticle
import com.moondev.press.models.ListOfSource
import com.moondev.press.models.Source
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APInterface {

       // business entertainment health science sports technology

        @GET("/v2/top-headlines")
        fun getHeadlines(@Query("country")country:String,@Query("category")category : String,
                         @Query("apiKey")apiKey :String) : Call<ListOfArticle>
        @GET("/v2/everything")
        fun getEveryThing(@Query("q")query: String,@Query("language")language: String, @Query("apiKey")apiKey: String) : Call<ListOfArticle>
        @GET("/v2/sources")
        fun getSources(@Query("language")language: String, @Query("apiKey")apiKey: String) : Call<ListOfSource>


}