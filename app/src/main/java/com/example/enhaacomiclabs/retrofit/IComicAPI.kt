package com.example.enhaacomiclabs.retrofit

import com.example.enhaacomiclabs.model.Comic
import com.example.enhaacomiclabs.model.ComicDto
import io.reactivex.Observable
import retrofit2.http.GET

interface IComicAPI {
    @get:GET(value = "list-comic")
    val listComic:Observable<ComicDto>
    @get:GET(value = "popular-comic")
    val popularComic:Observable<ComicDto>
}