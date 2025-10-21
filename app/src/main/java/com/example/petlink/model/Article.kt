package com.example.petlink.model

import java.util.Date

data class Article (
    val id : Int,
    val date : Date,
    val title : String,
    val content : String,
    val imageUrl : String
)