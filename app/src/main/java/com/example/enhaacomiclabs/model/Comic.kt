package com.example.enhaacomiclabs.model

import com.google.gson.annotations.SerializedName

class Comic {
    var title:String?=null
    @SerializedName("type_comic") var typeComic:String?=null
    var chapter:String?=null
    var rating:String?=null
    @SerializedName("comic_url") var comicUrl:String?=null
    @SerializedName("thumbnail_url") var thumbnailUrl:String?=null

    constructor(){

    }

    constructor(
        title: String?,
        typeComic: String?,
        chapter: String?,
        rating: String?,
        comicUrl: String?,
        thumbnailUrl: String?
    ) {
        this.title = title
        this.typeComic = typeComic
        this.chapter = chapter
        this.rating = rating
        this.comicUrl = comicUrl
        this.thumbnailUrl = thumbnailUrl
    }

}