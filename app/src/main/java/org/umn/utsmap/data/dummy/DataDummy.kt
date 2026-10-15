package org.umn.utsmap.data.dummy

import org.umn.utsmap.data.model.Kos

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
}