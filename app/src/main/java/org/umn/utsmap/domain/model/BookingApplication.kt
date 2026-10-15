package org.umn.utsmap.domain.model

import java.util.Date

enum class BookingStatus {
    PENDING,
    APPROVED,
    REJECTED
}

data class BookingApplication(
    val id: String,
    val kosId: String,
    val roomNumber: String,
    val applicantName: String,
    val applicantPhone: String,
    val applicantEmail: String,
    val rentalDurationMonths: Int,
    val startDate: Date,
    val notes: String? = null,
    val status: BookingStatus = BookingStatus.PENDING
)