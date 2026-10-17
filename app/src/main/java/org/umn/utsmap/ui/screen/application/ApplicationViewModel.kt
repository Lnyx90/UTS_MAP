package org.umn.utsmap.ui.screen.application

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ApplicationViewModel : ViewModel() {
    var fullName by mutableStateOf("")
        private set

    var phoneNumber by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var rentalDuration by mutableStateOf("6 Bulan")
        private set

    var startDate by mutableStateOf("1 Okt 2026")
        private set

    var notes by mutableStateOf("")
        private set

    // Validasi Real-time
    val isEmailValid: Boolean
        get() = email.isEmpty() || android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    val isPhoneValid: Boolean
        get() = phoneNumber.all { it.isDigit() }

    val isFormValid: Boolean
        get() = fullName.isNotBlank() &&
                phoneNumber.isNotBlank() &&
                isPhoneValid &&
                email.isNotBlank() &&
                isEmailValid

    fun updateFullName(value: String) { fullName = value }
    fun updatePhoneNumber(value: String) { if (value.all { it.isDigit() }) phoneNumber = value }
    fun updateEmail(value: String) { email = value }
    fun updateRentalDuration(value: String) { rentalDuration = value }
    fun updateStartDate(value: String) { startDate = value }
    fun updateNotes(value: String) { notes = value }
}