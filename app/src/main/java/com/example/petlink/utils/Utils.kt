package com.example.petlink.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun LocalDate.toFrenchDateString() : String {
    return this.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRENCH))
}

fun LocalDate.toSlashString() : String {
    return this.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}