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
│       └── [x] map.png
│
├── data/
│   ├── model/
│   │   ├── [x] Kos.kt
│   │   ├── [x] ApplicationForm.kt
│   │   ├── [ ] Invoice.kt
│   │   └── [ ] NotificationItem.kt
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
│   │   ├── [ ] NotificationCard.kt
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
│       │   ├── [ ] PaymentScreen.kt
│       │   └── [ ] PaymentViewModel.kt
│       └── notification/
│           ├── [ ] NotificationScreen.kt
│           └── [ ] NotificationViewModel.kt
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
    * Header Info: Nama Kos ("Kos Cemara Residence"), Alamat ("Jl. Kaliurang Km 4,5, Sleman, Yogyakarta"), Rating & Ulasan ("4,8 (32 ulasan) - Kos putri").
    * Pricing Breakdown Card:
        - Sewa / bulan: Rp1.200.000
        - Listrik (tarif tetap): Rp100.000
        - Air: Rp50.000
        - Laundry (pilihan 5 kg): Rp75.000
        - Total dengan laundry: Rp1.425.000 (Bold Primary Color).
    * Facilities Row: Badges ikon (AC, Wi-Fi, KM dalam, Ukuran 3x4 m).
    * Location & Distance Section: Distance items ("800 m ke UGM", "100 m ke warung", "50 m ke jalan utama").
    * Static Map Preview Mockup: Card berisi gambar peta lokasi sekitar kampus UGM.
    * Sticky Bottom Action Bar: Button "Ajukan Sewa" full width.
- Navigation Argument: Accepting kosId dynamically from SearchScreen.

SCREEN 3: LAYAR FORM — ApplicationScreen (Kriteria Rubrik: Form Input & Validation)
- UI Components:
    * Top Bar: Back Button, Title "Pengajuan sewa", Timestamp date ("28 September 2026").
    * Step Progress Indicator: Horizontal progress bar (1. Pengajuan [Active] -> 2. Persetujuan -> 3. Pembayaran).
    * Selected Kos Card Summary: Image thumbnail, Nama Kos ("Kos Cemara Residence"), No. Kamar & Price ("Kamar A-07 - Rp1.200.000 / bulan").
    * Form Fields (Input Validation):
        - Nama Lengkap: OutlinedTextField (KeyboardType.Text, Required field).
        - Nomor WhatsApp: OutlinedTextField (KeyboardType.Phone / Number, Numeric-only validation).
        - Alamat Email: OutlinedTextField (KeyboardType.Email, Email format regex validation).
        - Durasi Sewa: Dropdown menu / Select box ("6 bulan").
        - Mulai Tinggal: DatePicker field ("1 Okt 2026") dengan ikon Kalender.
        - Catatan Tambahan (opsional): Multiline OutlinedTextField.
    * Information Banner: Warning/Notice box "Pemilik meninjau pengajuan terlebih dahulu. Pembayaran baru dilakukan setelah disetujui."
    * Action Button: Button "Kirim Pengajuan" (Disabled jika field mandatory tidak valid / kosong).
- State Behavior: Real-time validation, error state message (isError), dynamic button state.

SCREEN 4: LAYAR UI FITUR NATIVE MOCKUP — PaymentScreen (Kriteria Rubrik: Native Feature UI Mockup)
- UI Components:
    * Top Bar: Back Button, Title "Pembayaran", Sub-header date ("30 September 2026").
    * Status Banner: Alert box hijau "Pengajuan Nadia disetujui pada 29 Sep. Kamar A-07 siap ditempati mulai 1 Oktober."
    * Invoice Card Summary:
        - Invoice ID & Status: "INV-202610-A07", Status Badge "Belum dibayar".
        - Detail: "Kos Cemara Residence - A-07", "Oktober 2026 - Jatuh tempo 1 Okt 2026".
        - Line Items: Sewa kamar (Rp1.200.000), Listrik (Rp100.000), Air (Rp50.000), Laundry 5kg (Rp75.000).
        - Total Pembayaran: Rp1.425.000.
    * Payment Method Selection Box:
        - Option 1: BCA Virtual Account ("8808 0123 4567 8901") + Copy Code Button (Simulasi Clipboard Native).
        - Option 2: QRIS - Semua dompet digital + Scan QRIS Camera Frame Mockup (Statis/Dummy Kamera UI representasi fitur native).
    * Auto Verification Info Card: "Pembayaran diverifikasi otomatis. Status tagihan berubah menjadi Lunas tanpa konfirmasi ke pemilik."
    * Sticky Action Button: Button "Bayar Sekarang".

SCREEN 5: LAYAR BEBAS PILIHAN — NotificationScreen (Kriteria Rubrik: Free Choice Screen)
- UI Components:
    * Top Bar: Title "Tagihan & notifikasi", Date & User context ("1 November 2026 - Nadia Putri").
    * Active Bill Highlight Card:
        - Month Badge: "November 2026", Due Badge: "Jatuh tempo hari ini".
        - Total Amount: "Rp1.425.000".
        - Bill ID: "INV-202611-A07 - Cemara Residence, A-07".
        - Action Link: "Lihat & bayar tagihan ->".
    * Filter Tabs Row: Filter pills (Semua [Selected], Tagihan, Pembayaran).
    * Activity Timeline List (LazyColumn):
        - Notification Item 1: Bell Icon, Title "Ingat, bayar tagihan November", Desc "INV-202611-A07 - Rp1.425.000 jatuh tempo hari ini", Time "1 Nov 2026 - 08.00".
        - Notification Item 2: Document Icon, Title "Tagihan baru sudah terbit", Desc "Tagihan November INV-202611-A07 siap dibayar lewat VA atau QRIS", Time "25 Okt 2026 - 08.00".
        - Notification Item 3: Success Check Icon, Title "Pembayaran Oktober berhasil", Desc "INV-202610-A07 - Rp1.425.000 diterima melalui BCA VA", Time "30 Sep 2026 - 14.32", Badge "Lunas" (Green).
    * Guarantee Information Box: "Tak perlu tanya pemilik. Bukti pembayaran dan status Lunas tersimpan otomatis di sini."
    * Bottom Navigation Bar: Tab "Tagihan" active state.


4. RUBRIK PENILAIAN

1. Screen Implementation (Rubrik 1)
    - File: ui/screen/application/ApplicationScreen.kt
    - Snippet:
      @Composable
      fun ApplicationScreen(
      kosId: String?,
      viewModel: ApplicationViewModel = viewModel(),
      onBack: () -> Unit,
      onSubmit: () -> Unit
      ) {
      Scaffold(
      topBar = { TopAppBar(title = { Text("Pengajuan sewa") }) }
      ) { padding ->
      Column(modifier = Modifier.padding(padding)) {
      StepIndicator(currentStep = 1)
      SelectedKosCard(kosName = "Kos Cemara Residence", room = "A-07", price = "Rp1.200.000 /bulan")
      FormFields(viewModel = viewModel)
      Button(
      onClick = onSubmit,
      enabled = viewModel.isFormValid()
      ) {
      Text("Kirim Pengajuan")
      }
      }
      }
      }

2. UI Theming (Rubrik 2)
    - File: ui/theme/Color.kt, ui/theme/Theme.kt
    - Snippet:
      val EarthyBrown = Color(0xFF6F5E4E)
      val KostellaColorScheme = lightColorScheme(primary = EarthyBrown)

3. State Management (Rubrik 3)
    - File: ui/screen/application/ApplicationViewModel.kt
    - Snippet:
      class ApplicationViewModel : ViewModel() {
      var fullName by mutableStateOf("")
      private set
      fun updateFullName(newVal: String) { fullName = newVal }
      }

4. Navigation & Argument Passing (Rubrik 4)
    - File: navigation/NavGraph.kt, navigation/ScreenRoutes.kt
    - Snippet:
      composable(
      route = ScreenRoutes.Detail.route,
      arguments = listOf(navArgument("kosId") { type = NavType.StringType })
      ) { backStackEntry ->
      val kosId = backStackEntry.arguments?.getString("kosId")
      DetailScreen(kosId = kosId)
      }

5. Form Input & Validation (Rubrik 5)
    - File: ui/screen/application/ApplicationScreen.kt
    - Snippet:
      OutlinedTextField(
      value = email,
      onValueChange = { email = it },
      label = { Text("Alamat Email") },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
      isError = email.isNotEmpty() && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
      )

      Button(
      onClick = { /* Kirim Pengajuan */ },
      enabled = fullName.isNotBlank() && phone.isNotBlank() && email.isNotBlank()
      ) {
      Text("Kirim Pengajuan")
      }

6. Dummy Data (Rubrik 6)
    - File: data/dummy/DummyData.kt, data/model/Kos.kt
    - Snippet:
      object DummyData {
      val kosList = listOf(
      Kos(id = 1, name = "Kos Cemara Residence", price = "Rp1.200.000 /bulan", rating = 4.8, category = "Putri")
      )
      }
   
5. CHECKPOINT STATUS & BRANCHING PLAN

- [x] CHECKPOINT 1: Foundation, M3 Theme Setup & Search List Screen (Branch: feature/cp1-search-list) -> COMPLETED
- [x] CHECKPOINT 2: Navigation & Detail Kos Screen (Branch: feature/cp2-detail-screen) -> COMPLETED
- [x] CHECKPOINT 3: Form Input & Validation / Pengajuan Sewa (Branch: feature/cp3-application-form) -> COMPLETED
- [ ] CHECKPOINT 4: UI Fitur Native Mockup / Pembayaran QRIS (Branch: feature/cp4-payment-native-mockup) -> PENDING
- [ ] CHECKPOINT 5: Free Choice Screen / Tagihan & Notifikasi & Final (Branch: feature/cp5-notifications-and-final) -> PENDING