package org.umn.utsmap.ui.screen.notification

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.umn.utsmap.data.model.NotificationItem

class NotificationViewModel : ViewModel() {
    var selectedFilter by mutableStateOf("Semua")
        private set
    var rentalDurationMonths by mutableStateOf(6)
        private set
    val billingList: List<NotificationItem> by lazy {
        val list = mutableListOf<NotificationItem>()
        list.add(
            NotificationItem(
                id = "INV-202610-A07",
                title = "Pembayaran Oktober berhasil",
                description = "INV-202610-A07 - Rp1.425.000 diterima melalui BCA VA",
                timestamp = "30 Sep 2026 - 14.32",
                type = "PAYMENT",
                statusBadge = "Lunas",
                isPaid = true
            )
        )
        val months = listOf("November", "Desember", "Januari", "Februari", "Maret", "April")
        for (i in 1 until rentalDurationMonths) {
            val monthName = if (i < months.size) months[i] else "Bulan ke-${i+1}"
            list.add(
                NotificationItem(
                    id = "INV-2026${10 + i}-A07",
                    title = "Tagihan sewa $monthName 2026",
                    description = "INV-2026${10 + i}-A07 - Rp1.425.000 siap dibayar",
                    timestamp = "1 $monthName 2026 - 08.00",
                    type = "BILL",
                    statusBadge = "Belum Bayar",
                    isPaid = false
                )
            )
        }
        list
    }

    fun setFilter(filter: String) {
        selectedFilter = filter
    }

    fun setRentalDuration(durationStr: String) {
        val digits = durationStr.filter { it.isDigit() }.toIntOrNull()
        if (digits != null) {
            rentalDurationMonths = digits
        }
    }
}