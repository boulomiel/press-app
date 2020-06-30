package com.moondev.press.models

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log


class Flag(val bitmap: Bitmap , val id : String, val country : String? ){
    override fun toString(): String {
        return "Flag(bitmap=$bitmap, id='$id', country=$country)"
    }
}

class FlagList(context: Context){

    val list = arrayListOf<Flag>()
    private val id = arrayListOf("ar","au","at","be","br","bg","ca","cn","co","cz","eg",
        "fr","de","gr","hu","in","id","ie","il","it","jp","lv","my","mx","ma","nl","nz","ng","no","ph","pl","pt","ru","rs","sg",
        "sk","si","za","kr","se","ch","tw","th","tr","ua","gb","us","ve"
    )
     private val countries = arrayListOf("Argentina","Australia","Austria",
        "Belgium","Brazil","Bulgaria","Canada","China","Colombia","Czech-Republic","Egypt","France",
        "Germany","Greece","Hungary","India","Indonesia","Ireland","Israel","Italy","Japan","Latvia","Malaysia",
        "Mexico","Morocco","Netherlands","New Zealand","Nigeria","Norway","Philippines","Poland","Portugal",
        "Russia","Serbia","Singapore","Slovakia","Slovenia","South Africa","South Korea","Sweden","Switzerland",
        "Taiwan","Thailand","Turkey","Ukraine","United Kingdom","Usa","Venezuela"
    )


    init {
        val am = context.assets
        val files = am.list("myFlags")
        Log.e("FLAg", "${files!!.size}")
        for (i in files.indices) {
            val d = BitmapFactory.decodeStream(am.open("myFlags/${files[i]}"))
            list.add(Flag(d, id[i],countries[i]))
            Log.e("FLAGS", "$i : ${Flag(d, id[i],countries[i])}")
        }
    }

    fun fromCountryCodeToLanguage(countryCode :  String) : String{
        return when(countryCode){
            "cn" -> "zn"
            "co","ar","ve" -> "es"
            "fr" -> "fr"
            "il" -> "he"
            "it" -> "it"
            "nl" -> "nl"
            "no" -> "no"
            "pt" -> "pt"
            "ru" -> "ru"
            "se" -> "se"
            else -> "en"
        }
    }



    fun getIndexOfCountry(countryCode: String) : Int = countries.indexOf(countryCode)


    fun getIndexWhileTyping(text :String) : Int {
        for( i in countries.indices){
            if (countries[i].contains(text)){
                Log.e("FLAG", countries[i])
               return  countries.indexOf(countries[i])
            }
        }

        return  0
    }
}