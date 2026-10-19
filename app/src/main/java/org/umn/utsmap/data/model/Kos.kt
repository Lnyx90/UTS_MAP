package org.umn.utsmap.data.model

data class Kos(
    val id: Int,
    val name: String,
    val category: String,
    val address: String,
    val price: String,
    val rating: Double,
    val reviewCount: Int,
    val distanceToCampus: String,
    val facilities: List<String>,
    val imageRes: String
)