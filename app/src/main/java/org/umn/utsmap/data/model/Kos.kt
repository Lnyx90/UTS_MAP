package org.umn.utsmap.data.model

data class Kos(
    val id: Int,
    val name: String,
    val category: String, // e.g. "Putri - 3 kamar"
    val address: String,
    val price: String,
    val rating: Double,
    val reviewCount: Int,
    val distanceToCampus: String,
    val facilities: List<String>,
    val imageRes: String // placeholder name or URL
)