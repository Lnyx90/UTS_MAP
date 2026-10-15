package org.umn.utsmap.domain.model

import java.util.Date

enum class PaymentStatus {
    UNPAID,
    PAID,
    OVERDUE
}

data class Invoice(
    val invoiceId: String,
    val kosName: String,
    val roomNumber: String,
    val tenantName: String,
    val period: String, // Contoh: "Oktober 2026"
    val dueDate: Date,
    val roomPrice: Double,
    val electricityFee: Double = 0.0,
    val waterFee: Double = 0.0,
    val laundryFee: Double = 0.0,
    val status: PaymentStatus = PaymentStatus.UNPAID
) {
    val totalPrice: Double
        get() = roomPrice + electricityFee + waterFee + laundryFee
}