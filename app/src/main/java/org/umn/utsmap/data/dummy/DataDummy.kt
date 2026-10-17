package org.umn.utsmap.data.dummy

import org.umn.utsmap.data.model.Invoice
import org.umn.utsmap.data.model.Kos
import org.umn.utsmap.data.model.NotificationItem

object DummyData {
    val kosList = listOf(
        Kos(
            id = 1,
            name = "Kos Cemara Residence",
            category = "Putri · 3 kamar",
            address = "Jl. Kaliurang Km 4,5, Sleman, Yogyakarta",
            price = "Rp1.200.000 /bulan",
            rating = 4.8,
            reviewCount = 32,
            distanceToCampus = "800 m ke UGM",
            facilities = listOf("AC", "Wi-Fi", "KM dalam", "3 x 4 m"),
            imageRes = "cemara_residence"
        ),
        Kos(
            id = 2,
            name = "Kos Taman Sari",
            category = "Campur · 2 kamar",
            address = "Jl. Pogung Baru, Sinduadi, Mlati, Sleman",
            price = "Rp1.350.000 /bulan",
            rating = 4.7,
            reviewCount = 24,
            distanceToCampus = "1,2 km ke UGM",
            facilities = listOf("AC", "Wi-Fi", "KM dalam"),
            imageRes = "taman_sari"
        )
    )

    val invoiceList = listOf(
        Invoice(
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
    )

    val notificationList = listOf(
        NotificationItem(
            id = "1",
            title = "Ingat, bayar tagihan November",
            description = "INV-202611-A07 - Rp1.425.000 jatuh tempo hari ini",
            timestamp = "1 Nov 2026 - 08.00",
            type = "BILL",
            statusBadge = "Jatuh Tempo",
            isPaid = false
        ),
        NotificationItem(
            id = "2",
            title = "Tagihan baru sudah terbit",
            description = "Tagihan November INV-202611-A07 siap dibayar lewat VA atau QRIS",
            timestamp = "25 Okt 2026 - 08.00",
            type = "BILL",
            statusBadge = null,
            isPaid = false
        ),
        NotificationItem(
            id = "3",
            title = "Pembayaran Oktober berhasil",
            description = "INV-202610-A07 - Rp1.425.000 diterima melalui BCA VA",
            timestamp = "30 Sep 2026 - 14.32",
            type = "PAYMENT",
            statusBadge = "Lunas",
            isPaid = true
        )
    )
}