package com.moondev.press.models

import com.google.gson.annotations.SerializedName


class ListOfSource( val status : String,
                    @SerializedName("sources")
                    val list: List<Source>
                    ){

    override fun toString(): String {
        return "ListOfSource(status='$status', list=${list})"
    }
}

class Source(
            val id :String?,
             val name : String?,
             val description : String?,
             var url :String?,
             val category : String?,
            val language : String?,
            val country : String?

){

    override fun toString(): String {
        return "Source(id=$id, name=$name, description=$description, url=$url, category=$category, country=$country, language=$language)"
    }
}
