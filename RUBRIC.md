## RUBRIK PENILAIAN

### 1. Screen Implementation (Rubrik 1 — Bukti 5 Layar Utama)

#### Layar 1 (List Screen)

**File:** `ui/screen/search/SearchScreen.kt`

```kotlin
@Composable
fun SearchScreen(viewModel: SearchViewModel = viewModel(), onKosClick: (Int) -> Unit) {
    val kosList by viewModel.kosList.collectAsState()
    LazyColumn {
        items(kosList) { kos ->
            KosCard(kos = kos, onClick = { onKosClick(kos.id) })
        }
    }
}
```

#### Layar 2 (Detail Screen)

**File:** `ui/screen/detail/DetailScreen.kt`

```kotlin
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = viewModel(),
    onBackClick: () -> Unit,
    onRentClick: () -> Unit
) {
    val kos by viewModel.kos.collectAsState()
    // Photo gallery, pricing breakdown, map preview, sticky "Ajukan Sewa"
}
```

#### Layar 3 (Form Screen)

**File:** `ui/screen/application/ApplicationScreen.kt`

```kotlin
@Composable
fun ApplicationScreen(
    viewModel: ApplicationViewModel = viewModel(),
    onBackClick: () -> Unit,
    onSubmitSuccess: () -> Unit
) {
    Scaffold(topBar = { TopAppBar(title = { Text("Pengajuan sewa") }) }) { padding ->
        Column {
            StepItem("1", "Pengajuan", true)
            FormFields(viewModel)
            Button(onClick = onSubmitSuccess, enabled = viewModel.isFormValid) {
                Text("Kirim Pengajuan")
            }
        }
    }
}
```

#### Layar 4 (Native Mockup UI Screen)

**File:** `ui/screen/payment/PaymentScreen.kt`

```kotlin
@Composable
fun PaymentScreen(
    viewModel: PaymentViewModel = viewModel(),
    onBackClick: () -> Unit,
    onPaymentSuccess: () -> Unit
) {
    val inv = viewModel.invoice
    // TabRow: BCA VA (Clipboard Native) / QRIS (Image Mockup)
    // Button "Bayar Sekarang" dengan loading indicator
}
```

#### Layar 5 (Free Choice Screen)

**File:** `ui/screen/notification/NotificationScreen.kt`

```kotlin
@Composable
fun NotificationScreen(
    viewModel: NotificationViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    onTabSelected: (Int) -> Unit = {}
) {
    Column {
        ActiveBillCard()
        FilterChips(viewModel.selectedFilter, viewModel::setFilter)
        LazyColumn {
            items(viewModel.billingList) { item ->
                NotificationCard(item)
            }
        }
    }
}
```

### 2. UI Theming (Rubrik 2)

- **File:** `ui/theme/Color.kt`, `ui/theme/Theme.kt`

**Snippet:**

```kotlin
val EarthyBrown = Color(0xFF6F5E4E)
val GreenAccent = Color(0xFF10B981)
val KostellaColorScheme = lightColorScheme(primary = EarthyBrown, secondary = GreenAccent)
// Diterapkan di MainActivity: KostellaTheme { NavGraph() }
```

### 3. State Management (Rubrik 3 — Input, Seleksi, Loading)

#### State Input Form

**File:** `ui/screen/application/ApplicationViewModel.kt`

```kotlin
class ApplicationViewModel : ViewModel() {
    var fullName by mutableStateOf("")
        private set

    fun updateFullName(newVal: String) {
        fullName = newVal
    }
}
```

#### State Seleksi Filter

**File:** `ui/screen/search/SearchViewModel.kt` & `notification/NotificationViewModel.kt`

```kotlin
class SearchViewModel : ViewModel() {
    private val _searchQuery = MutableStateFlow("")   // ← FIX: kosong, sesuai kode
    val searchQuery = _searchQuery.asStateFlow()

    fun updateSearchQuery(q: String) {
        _searchQuery.value = q
    }
}

// NotificationViewModel
var selectedFilter by mutableStateOf("Semua")
    private set

fun setFilter(f: String) {
    selectedFilter = f
}
```

#### State Loading Verifikasi Pembayaran

**File:** `ui/screen/payment/PaymentViewModel.kt`

```kotlin
class PaymentViewModel : ViewModel() {
    var isLoading by mutableStateOf(false)
        private set

    var paymentStatus by mutableStateOf("Belum dibayar")
        private set

    fun payNow() {
        viewModelScope.launch {
            isLoading = true
            paymentStatus = "Menunggu Verifikasi"
            delay(1500) // simulasi verifikasi
            paymentStatus = "Lunas"
            isLoading = false
        }
    }
}
```

### 4. Navigation & Argument Passing (Rubrik 4)

- **File:** `navigation/NavGraph.kt`, `navigation/ScreenRoutes.kt`

**Snippet (kosId dikirim dari Search -> Detail):**

```kotlin
// ScreenRoutes.kt
sealed class ScreenRoutes(val route: String) {
    object Search : ScreenRoutes("search")

    object Detail : ScreenRoutes("detail/{kosId}") {
        fun createRoute(kosId: Int) = "detail/$kosId"
    }

    object Application : ScreenRoutes("application")
    object Payment : ScreenRoutes("payment")
    object Notification : ScreenRoutes("notification")
}

// NavGraph.kt
composable(
    route = ScreenRoutes.Detail.route,
    arguments = listOf(navArgument("kosId") { type = NavType.StringType })
) { backStackEntry ->
    val kosId = backStackEntry.arguments?.getString("kosId")

    DetailScreen(
        onBackClick = { navController.popBackStack() },
        onRentClick = { navController.navigate(ScreenRoutes.Application.route) }
    )
}
```

### 5. Form Input & Validation dengan Appropriate Keyboard Type (Rubrik 5)

**File:** `ui/screen/application/ApplicationScreen.kt`

**Snippet Field Validation & Keyboard Types:**

```kotlin
// 1. Nama Lengkap (KeyboardType.Text)
OutlinedTextField(
    value = viewModel.fullName,
    onValueChange = { viewModel.updateFullName(it) },
    label = { Text("Nama Lengkap *") },
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
)

// 2. Nomor WhatsApp (KeyboardType.Phone)
OutlinedTextField(
    value = viewModel.phoneNumber,
    onValueChange = { viewModel.updatePhoneNumber(it) },
    label = { Text("Nomor WhatsApp *") },
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
)

// 3. Email (KeyboardType.Email)
OutlinedTextField(
    value = viewModel.email,
    onValueChange = { viewModel.updateEmail(it) },
    label = { Text("Alamat Email *") },
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    isError = !viewModel.isEmailValid,
    supportingText = {
        if (!viewModel.isEmailValid) {
            Text("Format email tidak valid", color = MaterialTheme.colorScheme.error)
        }
    }
)

// 4. Durasi Sewa (KeyboardType.Text)
OutlinedTextField(
    value = viewModel.rentalDuration,
    onValueChange = { viewModel.updateRentalDuration(it) },
    label = { Text("Durasi Sewa") },
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
)

// 5. Mulai Tinggal (KeyboardType.Text + Ikon Kalender)
OutlinedTextField(
    value = viewModel.startDate,
    onValueChange = { viewModel.updateStartDate(it) },
    label = { Text("Mulai Tinggal") },
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    trailingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) }
)

// Submit Button Enabled Validation
Button(
    onClick = { onSubmitSuccess() },
    enabled = viewModel.isFormValid
) {
    Text("Kirim Pengajuan")
}
```

### 6. Dummy Data (Rubrik 6)

**File:** `data/dummy/DummyData.kt`, `data/model/Kos.kt`, `Invoice.kt`, `NotificationItem.kt`

**Snippet:**

```kotlin
object DummyData {
    val kosList = listOf(
        Kos(id = 1, name = "Kos Cemara Residence", price = "Rp1.200.000 /bulan",
            rating = 4.8, category = "Putri · 3 kamar"),
        Kos(id = 2, name = "Kos Taman Sari", price = "Rp1.350.000 /bulan",
            rating = 4.7, category = "Campur · 2 kamar")
    )

    val invoiceList = listOf(
        Invoice(
            invoiceId = "INV-202610-A07",
            kosName = "Kos Cemara Residence",
            roomNumber = "A-07",
            period = "Oktober 2026",
            dueDate = "1 Okt 2026",
            rentAmount = 1200000,
            electricityFee = 100000,
            waterFee = 50000,
            laundryFee = 75000,
            totalAmount = 1425000,
            status = "Belum dibayar",
            vaNumber = "8808 0123 4567 8901"
        )
    )

    val notificationList = listOf(
        NotificationItem(
            id = "1",
            title = "Ingat, bayar tagihan November",
            description = "INV-202611-A07 - Rp1.425.000 jatuh tempo hari ini",
            timestamp = "1 Nov 2026 - 08.00",
            type = "BILL",
            statusBadge = "Jatuh Tempo",
            isPaid = false
        ),
        NotificationItem(
            id = "2",
            title = "Tagihan baru sudah terbit",
            description = "Tagihan November siap dibayar lewat VA atau QRIS",
            timestamp = "25 Okt 2026 - 08.00",
            type = "BILL",
            statusBadge = null,
            isPaid = false
        ),
        NotificationItem(
            id = "3",
            title = "Pembayaran Oktober berhasil",
            description = "INV-202610-A07 - Rp1.425.000 diterima melalui BCA VA",
            timestamp = "30 Sep 2026 - 14.32",
            type = "PAYMENT",
            statusBadge = "Lunas",
            isPaid = true
        )
    )
}
```
