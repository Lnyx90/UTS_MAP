PLAN.md — Development Roadmap & Architecture Kostella (UTS IF570)

1. PROJECT OVERVIEW
- App Name: Kostella (Marketplace & Management Kos)
- Target Package: org.umn.utsmap
- Tech Stack: Kotlin, Jetpack Compose, Material 3, Navigation Compose, ViewModel, StateFlow
- Design System:
    * Primary Color: Coklat Hangat / Earthy Brown (#6F5E4E)
    * Secondary / Accent Color: Green (#10B981)
    * Surface / Background Color: Off-white (#F9FAFB) & Dark Neutral (#1E293B)
    * Typography: Sans-serif / Inter
    * Visual Style: Soft card elevation, rounded corners (12dp - 16dp), clean outline borders, minimal icons


2. DIRECTORY STRUCTURE & CHECKLIST STATUS (Root: org.umn.utsmap)

org.umn.utsmap/
│
├── [x] MainActivity.kt
├── [x] PLAN.md
│
├── res/
│   └── drawable/
│       ├── [x] kos1.jpg
│       ├── [x] kos2.jpg
│       ├── [x] kos3.jpg
│       ├── [x] kos4.jpg
│       ├── [x] map.png
│       └── [x] qris.png
│
├── data/
│   ├── model/
│   │   ├── [x] Kos.kt
│   │   ├── [x] ApplicationForm.kt
│   │   ├── [x] Invoice.kt
│   │   └── [x] NotificationItem.kt
│   └── dummy/
│       └── [x] DummyData.kt
│
├── ui/
│   ├── theme/
│   │   ├── [x] Color.kt
│   │   ├── [x] Shape.kt
│   │   ├── [x] Theme.kt
│   │   └── [x] Type.kt
│   ├── components/
│   │   ├── [x] KostellaBottomBar.kt
│   │   ├── [x] KosCard.kt
│   │   ├── [x] NotificationCard.kt
│   │   └── [x] StepIndicator.kt
│   └── screen/
│       ├── search/
│       │   ├── [x] SearchScreen.kt
│       │   └── [x] SearchViewModel.kt
│       ├── detail/
│       │   ├── [x] DetailScreen.kt
│       │   └── [x] DetailViewModel.kt
│       ├── application/
│       │   ├── [x] ApplicationScreen.kt
│       │   └── [x] ApplicationViewModel.kt
│       ├── payment/
│       │   ├── [x] PaymentScreen.kt
│       │   └── [x] PaymentViewModel.kt
│       └── notification/
│           ├── [x] NotificationScreen.kt
│           └── [x] NotificationViewModel.kt
│
└── navigation/
├── [x] NavGraph.kt
└── [x] ScreenRoutes.kt


3. DETAILED SCREEN SPECIFICATIONS (5 REQUIREMENT SCREENS)

SCREEN 1: LAYAR LIST — SearchScreen (Kriteria Rubrik: List Screen)
- UI Components:
    * Top Bar: Header "Temukan kosmu" dengan sub-header "Dekat kampus, sesuai kantongmu".
    * Search Bar: Outlined search field pre-filled dengan lokasi "UGM, Yogyakarta" dan filter button.
    * Filter Chips Row: Horizontal scrollable filter chips (<= Rp1,5 juta, Lokasi, Fasilitas).
    * Counter Header: Text "12 kos di sekitar UGM" dan tombol switch view "Peta".
    * Card Items (LazyColumn):
        - Image Banner dengan badge gender/ketersediaan ("Putri - 3 kamar", "Campur - 2 kamar") dan ikon Heart/Favorite.
        - Title Kos: "Kos Cemara Residence" & "Kos Taman Sari".
        - Rating Badge: Ikon Bintang + Skor (4.8 / 4.7).
        - Distance & Facilities: Text "800 m ke UGM - AC, Wi-Fi, kamar mandi dalam".
        - Price: Highlighted bold text "Rp1.200.000 /bulan".
    * Bottom Navigation Bar: Integrated navigation icons (Cari, Sewa, Tagihan, Akun).
- Interactive State: Filter selection state, search text input state, list item click action.

SCREEN 2: LAYAR DETAIL — DetailScreen (Kriteria Rubrik: Detail Screen)
- UI Components:
    * Top Bar: Navigation Back Button, Title "Detail kos", More options action button.
    * Photo Gallery Section: Main photo + side thumbnail grid dengan badge "1/8 foto".
    * Header Info: Nama Kos, Alamat, Rating & Ulasan.
    * Pricing Breakdown Card:
        - Sewa / bulan: Rp1.200.000
        - Listrik (tarif tetap): Rp100.000
        - Air: Rp50.000
        - Laundry (pilihan 5 kg): Rp75.000
        - Total dengan laundry: Rp1.425.000 (Bold Primary Color).
    * Facilities Row: Badges ikon (AC, Wi-Fi, KM dalam, Ukuran 3x4 m).
    * Location & Distance Section: Distance items.
    * Static Map Preview Mockup: Card berisi gambar peta lokasi.
    * Sticky Bottom Action Bar: Button "Ajukan Sewa" full width.
- Navigation Argument: Accepting kosId dynamically from SearchScreen.

SCREEN 3: LAYAR FORM — ApplicationScreen (Kriteria Rubrik: Form Input & Validation)
- UI Components:
    * Top Bar: Back Button, Title "Pengajuan sewa", Timestamp date.
    * Step Progress Indicator: Horizontal progress bar (1. Pengajuan [Active] -> 2. Persetujuan -> 3. Pembayaran).
    * Selected Kos Card Summary: Image thumbnail, Nama Kos, No. Kamar & Price.
    * Form Fields (Input Validation):
        - Nama Lengkap: OutlinedTextField (KeyboardType.Text, Required field).
        - Nomor WhatsApp: OutlinedTextField (KeyboardType.Phone, Numeric-only validation).
        - Alamat Email: OutlinedTextField (KeyboardType.Email, Email format regex validation).
        - Durasi Sewa: OutlinedTextField (KeyboardType.Text).
        - Mulai Tinggal: OutlinedTextField (KeyboardType.Text) dengan ikon Kalender.
        - Catatan Tambahan (opsional): Multiline OutlinedTextField.
    * Information Banner: Warning/Notice box.
    * Action Button: Button "Kirim Pengajuan" (Disabled jika field mandatory tidak valid / kosong).
- State Behavior: Real-time validation, error state message (isError), dynamic button state.

SCREEN 4: LAYAR UI FITUR NATIVE MOCKUP — PaymentScreen (Kriteria Rubrik: Native Feature UI Mockup)
- UI Components:
    * Top Bar: Back Button, Title "Pembayaran", Sub-header date.
    * Status Banner: Alert box hijau "Pengajuan disetujui. Kamar siap ditempati."
    * Invoice Card Summary:
        - Invoice ID & Status: "INV-202610-A07", Status Badge dinamis.
        - Detail: Kos, kamar, periode, jatuh tempo.
        - Line Items: Sewa, Listrik, Air, Laundry.
        - Total Pembayaran: Rp1.425.000.
    * Payment Method Selection Box (TabRow):
        - Option 1: BCA Virtual Account + Copy Code Button (Clipboard Native).
        - Option 2: QRIS Image (qris.png) — representasi mockup fitur native kamera.
    * Auto Verification Info Card.
    * Sticky Action Button: Button "Bayar Sekarang" dengan loading indicator.
- State: selectedTab (VA/QRIS), isLoading, paymentStatus.

SCREEN 5: LAYAR BEBAS PILIHAN — NotificationScreen (Kriteria Rubrik: Free Choice Screen)
- UI Components:
    * Top Bar: Title "Tagihan & notifikasi", Date & User context.
    * Active Bill Highlight Card (EarthyBrown).
    * Filter Tabs Row: Filter pills (Semua, Tagihan, Pembayaran).
    * Activity Timeline List (LazyColumn):
        - Notification Item dengan icon berbeda per type (BILL/PAYMENT/INFO).
        - Badge status (Lunas/Jatuh Tempo).
    * Guarantee Information Box.
    * Bottom Navigation Bar: Tab "Tagihan" active state.
- State: selectedFilter (Semua/Tagihan/Pembayaran).


4. RUBRIK PENILAIAN & BUKTI IMPLEMENTASI

1. Screen Implementation (Rubrik 1 — Bukti 5 Layar Utama)
    * Layar 1 (List Screen): ui/screen/search/SearchScreen.kt
      @Composable
      fun SearchScreen(viewModel: SearchViewModel = viewModel(), onKosClick: (Int) -> Unit) {
      val kosList by viewModel.kosList.collectAsState()
      LazyColumn { items(kosList) { kos -> KosCard(kos = kos, onClick = { onKosClick(kos.id) }) } }
      }

    * Layar 2 (Detail Screen): ui/screen/detail/DetailScreen.kt
      @Composable
      fun DetailScreen(
      viewModel: DetailViewModel = viewModel(),
      onBackClick: () -> Unit,
      onRentClick: () -> Unit
      ) {
      val kos by viewModel.kos.collectAsState()
      // Photo gallery, pricing breakdown, map preview, sticky "Ajukan Sewa"
      }

    * Layar 3 (Form Screen): ui/screen/application/ApplicationScreen.kt
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

    * Layar 4 (Native Mockup UI Screen): ui/screen/payment/PaymentScreen.kt
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

    * Layar 5 (Free Choice Screen): ui/screen/notification/NotificationScreen.kt
      @Composable
      fun NotificationScreen(
      viewModel: NotificationViewModel = viewModel(),
      onBackClick: () -> Unit = {},
      onTabSelected: (Int) -> Unit = {}
      ) {
      Column {
      ActiveBillCard()
      FilterChips(viewModel.selectedFilter, viewModel::setFilter)
      LazyColumn { items(viewModel.billingList) { item -> NotificationCard(item) } }
      }
      }

2. UI Theming (Rubrik 2)
    - File: ui/theme/Color.kt, ui/theme/Theme.kt
    - Snippet:
      val EarthyBrown = Color(0xFF6F5E4E)
      val GreenAccent = Color(0xFF10B981)
      val KostellaColorScheme = lightColorScheme(primary = EarthyBrown, secondary = GreenAccent)
      // Diterapkan di MainActivity: KostellaTheme { NavGraph() }

3. State Management (Rubrik 3 — Input, Seleksi, Loading)
    - State Input Form: ui/screen/application/ApplicationViewModel.kt
      class ApplicationViewModel : ViewModel() {
      var fullName by mutableStateOf("")
      private set
      fun updateFullName(newVal: String) { fullName = newVal }
      }

    - State Seleksi Filter: ui/screen/search/SearchViewModel.kt & notification/NotificationViewModel.kt
      class SearchViewModel : ViewModel() {
      private val _searchQuery = MutableStateFlow("")   // ← FIX: kosong, sesuai kode
      val searchQuery = _searchQuery.asStateFlow()
      fun updateSearchQuery(q: String) { _searchQuery.value = q }
      }

      // NotificationViewModel
      var selectedFilter by mutableStateOf("Semua")
      private set
      fun setFilter(f: String) { selectedFilter = f }

    - State Loading Verifikasi Pembayaran: ui/screen/payment/PaymentViewModel.kt
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

4. Navigation & Argument Passing (Rubrik 4)
    - File: navigation/NavGraph.kt, navigation/ScreenRoutes.kt
    - Snippet (kosId dikirim dari Search → Detail):   // ← FIX: hapus "→ Application"
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

5. Form Input & Validation dengan Appropriate Keyboard Type (Rubrik 5)
    - File: ui/screen/application/ApplicationScreen.kt
    - Snippet Field Validation & Keyboard Types:
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
      if (!viewModel.isEmailValid) Text("Format email tidak valid", color = MaterialTheme.colorScheme.error)
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

6. Dummy Data (Rubrik 6)
    - File: data/dummy/DummyData.kt, data/model/Kos.kt, Invoice.kt, NotificationItem.kt
    - Snippet:
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
              NotificationItem(id = "1", title = "Ingat, bayar tagihan November",
                  description = "INV-202611-A07 - Rp1.425.000 jatuh tempo hari ini",
                  timestamp = "1 Nov 2026 - 08.00", type = "BILL",
                  statusBadge = "Jatuh Tempo", isPaid = false),
              NotificationItem(id = "2", title = "Tagihan baru sudah terbit",
                  description = "Tagihan November siap dibayar lewat VA atau QRIS",
                  timestamp = "25 Okt 2026 - 08.00", type = "BILL",
                  statusBadge = null, isPaid = false),
              NotificationItem(id = "3", title = "Pembayaran Oktober berhasil",
                  description = "INV-202610-A07 - Rp1.425.000 diterima melalui BCA VA",
                  timestamp = "30 Sep 2026 - 14.32", type = "PAYMENT",
                  statusBadge = "Lunas", isPaid = true)
          )
      }

5. CHECKPOINT STATUS & BRANCHING PLAN

- [x] CHECKPOINT 1: Foundation, M3 Theme Setup & Search List Screen
- [x] CHECKPOINT 2: Navigation & Detail Kos Screen
- [x] CHECKPOINT 3: Form Input & Validation / Pengajuan Sewa
- [x] CHECKPOINT 4: UI Fitur Native Mockup / Pembayaran QRIS
- [x] CHECKPOINT 5: Free Choice Screen / Tagihan & Notifikasi & Final