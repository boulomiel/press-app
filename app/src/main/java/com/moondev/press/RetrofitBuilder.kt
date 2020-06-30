package com.moondev.press

import com.moondev.press.models.ListOfArticle
import com.moondev.press.models.ListOfSource
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    var repo : APInterface? = null

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
         repo = retrofit.create(APInterface::class.java)
    }


   fun  getHeadlines(countryId : String, category : String, callback : Callback<ListOfArticle> ){
       val call = repo!!.getHeadlines(countryId,category ,api_key)
       call.enqueue(callback)
   }

    fun  getEverything(query : String, language : String, callback : Callback<ListOfArticle> ){
        val call = repo!!.getEveryThing(query,language,api_key)
        call.enqueue(callback)
    }

    fun  getSources(language : String, callback : Callback<ListOfSource> ){
        val call = repo!!.getSources(language, api_key)
        call.enqueue(callback)
    }
}