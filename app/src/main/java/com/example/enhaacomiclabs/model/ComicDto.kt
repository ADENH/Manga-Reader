package com.example.enhaacomiclabs.model

class ComicDto {
    val data: List<Comic>
        
    constructor(data: List<Comic>) {
        this.data = data
    }
}