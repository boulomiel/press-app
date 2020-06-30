package com.moondev.press

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moondev.press.models.ListOfArticle
import com.moondev.press.models.ListOfSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Callback

class FlagViewModel : ViewModel(){
    var retrofitBuilder : RetrofitBuilder? = null
    init{
        retrofitBuilder = RetrofitBuilder()
    }


     var languagelol  : String?  = null
    private var country : String?  =  null
    var codeLanguage = MutableLiveData<String>()
    var countryId = MutableLiveData<String>()
    private var category : String?  = null
    var categoryMutable =  MutableLiveData<String>()

    var indexWhileTyping = MutableLiveData<Int>()
    var indexOnClick =  MutableLiveData<Int>()


    fun setCountryId(id : String){
        country = id
        countryId.value =country
    }

    fun setLanguage(code : String){
        languagelol = code
        codeLanguage.value =languagelol
    }

    fun setCategorym(mCategory :  String){
       category = mCategory
        categoryMutable.value = category
    }

    suspend fun getHeadLinesArticle(category :String , callback: Callback<ListOfArticle>){
        withContext(Dispatchers.IO){
            retrofitBuilder!!.getHeadlines(countryId.value!!,category.toLowerCase(),callback)
        }
    }

    suspend fun getListOfArticles(query : String,language : String,  callback : Callback<ListOfArticle>){
            withContext(Dispatchers.IO){
              retrofitBuilder!!.getEverything(query,language,callback)

            }
    }

    suspend fun getListOfSources(language : String, callback : Callback<ListOfSource>){
        withContext(Dispatchers.IO){
            retrofitBuilder!!.getSources(language,callback)

        }
    }





}