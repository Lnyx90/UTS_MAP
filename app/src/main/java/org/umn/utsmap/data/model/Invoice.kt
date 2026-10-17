package org.umn.utsmap.data.model

data class Invoice(
    val invoiceId: String,
    val kosName: String,
    val roomNumber: String,
    val period: String,
    val dueDate: String,
    val rentAmount: Int,
    val electricityFee: Int,
    val waterFee: Int,
    val laundryFee: Int,
    val totalAmount: Int,
    val status: String, // "Belum dibayar" atau "Lunas"
    val vaNumber: String
)