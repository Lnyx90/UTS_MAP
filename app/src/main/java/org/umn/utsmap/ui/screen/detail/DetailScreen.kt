package org.umn.utsmap.ui.screen.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.umn.utsmap.R
import org.umn.utsmap.ui.theme.EarthyBrown
import org.umn.utsmap.ui.theme.OutlineBorder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onBackClick: () -> Unit,
    onRentClick: () -> Unit
) {
    val kos by viewModel.kos.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail kos", fontWeight = FontWeight.Bold, fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Lainnya")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        },
        bottomBar = {
            // Sticky Bottom Action Bar
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp,
                shadowElevation = 8.dp
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Button(
                        onClick = onRentClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = EarthyBrown)
                    ) {
                        Text(text = "Ajukan Sewa", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        if (kos == null) return@Scaffold

        val currentKos = kos!!
        val isOdd = currentKos.id % 2 != 0
        val mainImageRes = if (isOdd) R.drawable.kos1 else R.drawable.kos2
        val sideImageRes = if (isOdd) R.drawable.kos3 else R.drawable.kos4

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = mainImageRes),
                        contentDescription = currentKos.name,
                        modifier = Modifier
                            .weight(2f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Image(
                        painter = painterResource(id = sideImageRes),
                        contentDescription = "${currentKos.name} Detail",
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                Surface(
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.BottomEnd),
                    shape = RoundedCornerShape(8.dp),
                    color = Color.Black.copy(alpha = 0.65f)
                ) {
                    Text(
                        text = "1/8 foto",
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Header Info
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = currentKos.name, style = MaterialTheme.typography.titleLarge, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text(text = currentKos.address, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFF59E0B), modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${currentKos.rating} (${currentKos.reviewCount} ulasan) · ${currentKos.category}", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                }
            }

            HorizontalDivider(color = OutlineBorder)

            // Pricing Breakdown Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, OutlineBorder)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(text = "Rincian Biaya", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Sewa / bulan", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text(currentKos.price, fontWeight = FontWeight.SemiBold)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Listrik (tarif tetap)", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text("Rp100.000", fontWeight = FontWeight.SemiBold)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Air", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text("Rp50.000", fontWeight = FontWeight.SemiBold)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Laundry (opsional 5 kg)", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text("Rp75.000", fontWeight = FontWeight.SemiBold)
                    }
                    HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), color = OutlineBorder)
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Total dengan laundry", fontWeight = FontWeight.Bold)
                        Text("Rp1.425.000", fontWeight = FontWeight.Bold, color = EarthyBrown, fontSize = 16.sp)
                    }
                }
            }

            // Facilities Section
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Fasilitas Kos", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    currentKos.facilities.forEach { facility ->
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = EarthyBrown.copy(alpha = 0.1f)
                        ) {
                            Text(
                                text = facility,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                color = EarthyBrown,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            // Location & Distance Section
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(text = "Lokasi & Sekitar", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = "• ${currentKos.distanceToCampus}", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(text = "• 100 m ke warung makan & minimarket", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(text = "• 50 m ke jalan utama kendaraan umum", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            // Map Image Section
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Peta Lokasi", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, OutlineBorder)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.map),
                        contentDescription = "Peta Lokasi Kos",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}