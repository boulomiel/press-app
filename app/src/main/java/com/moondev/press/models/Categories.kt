package com.moondev.press.models

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory


class Categories(bitmap: Bitmap,val  name : String)



class Icons(val context: Context, private val  languageCode : String) {

    val listOfBitmaps  = arrayListOf<Categories>()
    private var listOfNames = arrayListOf<String>()
     val listOfCategoriesForApi = arrayListOf("General","Health","Science","Sport","Technology")


    private fun languagesInCategory(){

        when(languageCode){
            "zn"-> listOfNames = arrayListOf("一般", "健康","科学","运动","技术")
            "es"-> listOfNames = arrayListOf("General", "Salud", "Ciencia", "Deporte", "Tecnología")
            "fr"->listOfNames = arrayListOf("Général", "Santé","Sciences","Sport","Technologie")
            "he"->listOfNames = arrayListOf("כללי", "בריאות", "מדע", "ספורט", "טכנולוגיה")
            "it"->listOfNames = arrayListOf("Generale", "salute", "Science", "Sport", "tecnologia")
            "nl"->listOfNames = arrayListOf("Algemeen", "Gezondheid", "Wetenschap", "Sport", "Technologie")
            "no"->listOfNames = arrayListOf("Generelt", "helse", "Science", "Sport", "Technology")
            "pt"->listOfNames = arrayListOf("Geral", "Saúde", "Ciência", "Esporte", "Tecnologia")
            "ru"->listOfNames = arrayListOf("Общие", "Здоровье", "Наука", "Спорт", "Технология")
            "se"->listOfNames = arrayListOf("Allmänt", "Hälsa", "Science", "Sport", "Technology")
            "en"-> listOfNames = arrayListOf("General", "Health","Science","Sport","Technology")

        }
    }



    init{

        languagesInCategory()

        val am = context.assets
        val files = am.list("categories")
        for (i in files!!.indices){
            val b =  BitmapFactory.decodeStream(am.open("categories/${files[i]}"))
            val n =  listOfNames[i]
            listOfBitmaps.add(Categories(b,n))
        }
    }




}