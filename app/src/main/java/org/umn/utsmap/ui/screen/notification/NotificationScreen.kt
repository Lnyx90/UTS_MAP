package org.umn.utsmap.ui.screen.notification

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.umn.utsmap.ui.components.KostellaBottomBar
import org.umn.utsmap.ui.components.NotificationCard
import org.umn.utsmap.ui.theme.EarthyBrown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    viewModel: NotificationViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onBackClick: () -> Unit = {},
    onTabSelected: (Int) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Tagihan & notifikasi", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("1 November 2026 - Nadia Putri", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        },
        bottomBar = {
            KostellaBottomBar(
                selectedTab = 2, // Tab "Tagihan" aktif
                onTabSelected = onTabSelected
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Active Bill Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = EarthyBrown),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = Color.White.copy(alpha = 0.2f)
                        ) {
                            Text(
                                text = "November 2026",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = Color(0xFFEF4444).copy(alpha = 0.3f)
                        ) {
                            Text(
                                text = "Jatuh tempo hari ini",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                fontSize = 11.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Text(text = "Rp1.425.000", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color.White)
                    Text(text = "INV-202611-A07 - Cemara Residence, A-07", fontSize = 12.sp, color = Color.White.copy(alpha = 0.85f))
                }
            }

            // Info Box
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
                        text = "Tak perlu tanya pemilik. Bukti pembayaran dan status Lunas tersimpan otomatis di sini.",
                        fontSize = 12.sp,
                        color = EarthyBrown,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Filter Tabs
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Semua", "Tagihan", "Pembayaran").forEach { filter ->
                    FilterChip(
                        selected = viewModel.selectedFilter == filter,
                        onClick = { viewModel.setFilter(filter) },
                        label = { Text(filter) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = EarthyBrown,
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }

            // LazyColumn
            Text(
                text = "Aktivitas & Riwayat (${viewModel.billingList.size} items)",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                val filteredList = viewModel.billingList.filter { item ->
                    when (viewModel.selectedFilter) {
                        "Tagihan" -> item.type == "BILL"
                        "Pembayaran" -> item.type == "PAYMENT"
                        else -> true
                    }
                }

                items(filteredList) { item ->
                    NotificationCard(item = item)
                }
            }
        }
    }
}