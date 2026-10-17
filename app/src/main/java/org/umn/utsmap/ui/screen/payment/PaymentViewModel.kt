package org.umn.utsmap.ui.screen.payment

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.umn.utsmap.data.model.Invoice

class PaymentViewModel : ViewModel() {
    var selectedTab by mutableStateOf(0) // 0: Virtual Account, 1: QRIS
        private set

    var paymentStatus by mutableStateOf("Belum dibayar")
        private set

    val invoice = Invoice(
        invoiceId = "INV-202610-A07",
        kosName = "Kos Cemara Residence",
        roomNumber = "A-07",
        period = "Oktober 2026",
        dueDate = "1 Okt 2026",
        rentAmount = 1200000,
        electricityFee = 100000,
        waterFee = 50000,
        laundryFee = 75000,
        totalAmount = 1425000,
        status = "Belum dibayar",
        vaNumber = "8808 0123 4567 8901"
    )

    fun selectTab(index: Int) {
        selectedTab = index
    }

    fun payNow() {
        paymentStatus = "Menunggu Verifikasi"
    }
}