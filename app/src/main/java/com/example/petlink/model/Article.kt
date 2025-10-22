package com.example.petlink.model

import java.time.LocalDate

data class Article (
    val id : Int,
    val date : LocalDate,
    val title : String,
    val content : String,
    val imageUrl : String
)