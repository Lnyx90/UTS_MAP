package org.umn.utsmap.ui.screen.search

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.umn.utsmap.ui.components.KostellaBottomBar
import org.umn.utsmap.ui.components.KosCard
import org.umn.utsmap.ui.theme.EarthyBrown
import org.umn.utsmap.ui.theme.OutlineBorder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = viewModel(),
    onKosClick: (Int) -> Unit
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val kosList by viewModel.kosList.collectAsState()
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            KostellaBottomBar(selectedTab = selectedTab, onTabSelected = { selectedTab = it })
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            // Header Title
            Text(
                text = "Temukan kosmu",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Dekat kampus, sesuai kantongmu",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Search Bar & Filter Button Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { viewModel.updateSearchQuery(it) },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Cari lokasi atau nama kos...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = EarthyBrown) },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EarthyBrown,
                        unfocusedBorderColor = OutlineBorder
                    ),
                    singleLine = true
                )

                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(56.dp)
                        .border(1.dp, OutlineBorder, RoundedCornerShape(12.dp))
                ) {
                    Icon(Icons.Default.Tune, contentDescription = "Filter", tint = EarthyBrown)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Filter Chips Horizontal Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = true,
                    onClick = { },
                    label = { Text("≤ Rp1,5 juta") },
                    colors = FilterChipDefaults.filterChipColors(selectedContainerColor = EarthyBrown.copy(alpha = 0.1f), selectedLabelColor = EarthyBrown)
                )
                FilterChip(
                    selected = false,
                    onClick = { },
                    label = { Text("Lokasi") }
                )
                FilterChip(
                    selected = false,
                    onClick = { },
                    label = { Text("Fasilitas") }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Counter Header & Map View Toggle
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${kosList.size} kos di sekitar UGM",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                TextButton(onClick = { }) {
                    Text(text = "Peta", color = EarthyBrown, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // LazyColumn List of Kos
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(kosList) { kos ->
                    KosCard(kos = kos, onClick = { onKosClick(kos.id) })
                }
            }
        }
    }
}