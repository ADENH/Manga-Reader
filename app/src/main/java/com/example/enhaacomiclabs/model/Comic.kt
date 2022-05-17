package com.example.enhaacomiclabs.model

class Comic {
    var title:String?=null
    var typeComic:String?=null
    var chapter:String?=null
    var rating:String?=null
    var comicUrl:String?=null
    var thumbnailUrl:String?=null

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