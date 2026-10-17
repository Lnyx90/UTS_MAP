package org.umn.utsmap.ui.screen.application

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.umn.utsmap.R
import org.umn.utsmap.ui.theme.EarthyBrown
import org.umn.utsmap.ui.theme.OutlineBorder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationScreen(
    viewModel: ApplicationViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onBackClick: () -> Unit,
    onSubmitSuccess: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Pengajuan sewa", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("28 September 2026", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp,
                shadowElevation = 8.dp
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Button(
                        onClick = onSubmitSuccess,
                        enabled = viewModel.isFormValid,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EarthyBrown,
                            disabledContainerColor = Color.LightGray
                        )
                    ) {
                        Text(text = "Kirim Pengajuan", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Step Progress Indicator Mockup
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StepItem(step = "1", title = "Pengajuan", active = true)
                Divider(modifier = Modifier.width(30.dp), color = EarthyBrown)
                StepItem(step = "2", title = "Persetujuan", active = false)
                Divider(modifier = Modifier.width(30.dp), color = OutlineBorder)
                StepItem(step = "3", title = "Pembayaran", active = false)
            }

            // Selected Kos Card Summary
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, OutlineBorder)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.kos1),
                        contentDescription = "Kos Summary",
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(text = "Kos Cemara Residence", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Text(text = "Kamar A-07 · Rp1.200.000 / bulan", fontSize = 13.sp, color = EarthyBrown, fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            // Form Inputs Section
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(text = "Data Diri Penyewa", fontWeight = FontWeight.Bold, fontSize = 16.sp)

                // Nama Lengkap (Required)
                OutlinedTextField(
                    value = viewModel.fullName,
                    onValueChange = { viewModel.updateFullName(it) },
                    label = { Text("Nama Lengkap *") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                // Nomor WhatsApp (Numeric-only validation)
                OutlinedTextField(
                    value = viewModel.phoneNumber,
                    onValueChange = { viewModel.updatePhoneNumber(it) },
                    label = { Text("Nomor WhatsApp *") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true
                )

                // Alamat Email (Email format validation)
                OutlinedTextField(
                    value = viewModel.email,
                    onValueChange = { viewModel.updateEmail(it) },
                    label = { Text("Alamat Email *") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    isError = !viewModel.isEmailValid,
                    supportingText = {
                        if (!viewModel.isEmailValid) {
                            Text("Format email tidak valid", color = MaterialTheme.colorScheme.error)
                        }
                    },
                    singleLine = true
                )

                // Durasi Sewa
                OutlinedTextField(
                    value = viewModel.rentalDuration,
                    onValueChange = { viewModel.updateRentalDuration(it) },
                    label = { Text("Durasi Sewa") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                // Mulai Tinggal
                OutlinedTextField(
                    value = viewModel.startDate,
                    onValueChange = { viewModel.updateStartDate(it) },
                    label = { Text("Mulai Tinggal") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    trailingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) },
                    singleLine = true
                )

                // Catatan Tambahan
                OutlinedTextField(
                    value = viewModel.notes,
                    onValueChange = { viewModel.updateNotes(it) },
                    label = { Text("Catatan Tambahan (Opsional)") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    minLines = 3
                )
            }

            // Information Banner
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = EarthyBrown.copy(alpha = 0.1f)
            ) {
                Text(
                    text = "Pemilik meninjau pengajuan terlebih dahulu. Pembayaran baru dilakukan setelah disetujui.",
                    modifier = Modifier.padding(12.dp),
                    color = EarthyBrown,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun StepItem(step: String, title: String, active: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            shape = RoundedCornerShape(50),
            color = if (active) EarthyBrown else Color.LightGray,
            modifier = Modifier.size(28.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = step, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = title, fontSize = 11.sp, fontWeight = if (active) FontWeight.Bold else FontWeight.Normal)
    }
}