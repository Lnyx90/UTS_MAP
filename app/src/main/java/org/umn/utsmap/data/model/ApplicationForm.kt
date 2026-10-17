package org.umn.utsmap.data.model

data class ApplicationForm(
    val fullName: String,
    val phoneNumber: String,
    val email: String,
    val rentalDuration: String,
    val startDate: String,
    val notes: String
)