package org.umn.utsmap.ui.screen.payment

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.umn.utsmap.R
import org.umn.utsmap.ui.theme.EarthyBrown
import org.umn.utsmap.ui.theme.OutlineBorder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    viewModel: PaymentViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onBackClick: () -> Unit,
    onPaymentSuccess: () -> Unit
) {
    val context = LocalContext.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val inv = viewModel.invoice

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Pembayaran", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("30 September 2026", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
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
                        onClick = {
                            viewModel.payNow()
                            Toast.makeText(context, "Pembayaran dikirim, memverifikasi...", Toast.LENGTH_SHORT).show()
                            onPaymentSuccess()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = EarthyBrown)
                    ) {
                        Text(text = "Bayar Sekarang", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
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
            // Status Banner
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFF10B981).copy(alpha = 0.15f)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF10B981))
                    Text(
                        text = "Pengajuan Nadia disetujui pada 29 Sep. Kamar A-07 siap ditempati mulai 1 Oktober.",
                        fontSize = 12.sp,
                        color = Color(0xFF065F46),
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Invoice Card Summary
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, OutlineBorder)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = inv.invoiceId, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = when (viewModel.paymentStatus) {
                                "Menunggu Verifikasi" -> Color(0xFF3B82F6).copy(alpha = 0.2f)
                                "Lunas" -> Color(0xFF10B981).copy(alpha = 0.2f)
                                else -> Color(0xFFF59E0B).copy(alpha = 0.2f)
                            }
                        ) {
                            Text(
                                text = viewModel.paymentStatus,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = when (viewModel.paymentStatus) {
                                    "Menunggu Verifikasi" -> Color(0xFF1D4ED8)
                                    "Lunas" -> Color(0xFF065F46)
                                    else -> Color(0xFFB45309)
                                }
                            )
                        }
                    }

                    Text(text = "${inv.kosName} \n${inv.roomNumber}", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(text = "Periode: ${inv.period} · Jatuh tempo ${inv.dueDate}", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)

                    HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), color = OutlineBorder)

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Sewa kamar", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 13.sp)
                        Text("Rp1.200.000", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Listrik (tarif tetap)", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 13.sp)
                        Text("Rp100.000", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Air", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 13.sp)
                        Text("Rp50.000", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Laundry 5kg", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 13.sp)
                        Text("Rp75.000", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), color = OutlineBorder)

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Total Pembayaran", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Text("Rp1.425.000", fontWeight = FontWeight.Bold, color = EarthyBrown, fontSize = 16.sp)
                    }
                }
            }

            // Payment Method Selection Tabs
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Pilih Metode Pembayaran", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                TabRow(
                    selectedTabIndex = viewModel.selectedTab,
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = EarthyBrown
                ) {
                    Tab(
                        selected = viewModel.selectedTab == 0,
                        onClick = { viewModel.selectTab(0) },
                        text = { Text("BCA Virtual Account", fontWeight = FontWeight.SemiBold) }
                    )
                    Tab(
                        selected = viewModel.selectedTab == 1,
                        onClick = { viewModel.selectTab(1) },
                        text = { Text("QRIS", fontWeight = FontWeight.SemiBold) }
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                if (viewModel.selectedTab == 0) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, OutlineBorder)
                    ) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(text = "BCA Virtual Account", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text(text = "Nomor VA:", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(inv.vaNumber, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = EarthyBrown)
                                OutlinedButton(
                                    onClick = {
                                        clipboardManager.setText(AnnotatedString(inv.vaNumber))
                                        Toast.makeText(context, "Nomor VA disalin ke clipboard!", Toast.LENGTH_SHORT).show()
                                    },
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text("Salin Kode")
                                }
                            }
                        }
                    }
                } else {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, OutlineBorder)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(text = "Scan QRIS untuk Semua Dompet Digital", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Image(
                                painter = painterResource(id = R.drawable.qris),
                                contentDescription = "QRIS Pembayaran",
                                modifier = Modifier
                                    .size(180.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Fit
                            )
                            Text(text = "Gunakan aplikasi m-banking atau e-wallet untuk scan", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }

            // Auto Verification Info Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = EarthyBrown.copy(alpha = 0.1f)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Info, contentDescription = null, tint = EarthyBrown)
                    Text(
                        text = "Pembayaran diverifikasi otomatis. Status tagihan berubah menjadi Lunas tanpa konfirmasi ke pemilik.",
                        fontSize = 12.sp,
                        color = EarthyBrown,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}