package org.umn.utsmap.data.model

data class NotificationItem(
    val id: String,
    val title: String,
    val description: String,
    val timestamp: String,
    val type: String, // "BILL", "PAYMENT", "INFO"
    val statusBadge: String? = null, // "Lunas", "Jatuh Tempo", dll
    val isPaid: Boolean = false
)