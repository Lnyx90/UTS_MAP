package org.umn.utsmap.ui.screen.payment

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.umn.utsmap.data.model.Invoice

class PaymentViewModel : ViewModel() {
    var selectedTab by mutableStateOf(0)
        private set

    var paymentStatus by mutableStateOf("Belum dibayar")
        private set

    var isLoading by mutableStateOf(false)
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
        if (isLoading) return
        viewModelScope.launch {
            isLoading = true
            paymentStatus = "Menunggu Verifikasi"
            delay(1500) // simulasi verifikasi
            paymentStatus = "Lunas"
            isLoading = false
        }
    }
}