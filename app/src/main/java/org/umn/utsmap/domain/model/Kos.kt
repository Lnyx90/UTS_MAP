package org.umn.utsmap.domain.model

data class Kos(
    val id: String,
    val name: String,
    val address: String,
    val category: String,
    val availableRooms: Int,
    val totalRooms: Int,
    val pricePerMonth: Double,
    val rating: Float,
    val reviewCount: Int,
    val distanceToCampus: String,
    val facilities: List<String>,
    val imageUrls: List<String>
)