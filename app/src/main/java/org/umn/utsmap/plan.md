# PLAN.md — Development Roadmap & Architecture Kostella (UTS IF570)

## 1. PROJECT OVERVIEW

- **App Name:** Kostella (Marketplace & Management Kos)
- **Target Package:** `org.umn.utsmap`
- **Tech Stack:** Kotlin, Jetpack Compose, Material 3, Navigation Compose, ViewModel, StateFlow

### Design System

- **Primary Color:** Coklat Hangat / Earthy Brown (`#6F5E4E`)
- **Secondary / Accent Color:** Green (`#10B981`)
- **Surface / Background Color:** Off-white (`#F9FAFB`) & Dark Neutral (`#1E293B`)
- **Typography:** Sans-serif / Inter
- **Visual Style:** Soft card elevation, rounded corners (12dp–16dp), clean outline borders, minimal icons

---

## 2. DIRECTORY STRUCTURE & CHECKLIST STATUS

**Root:** `org.umn.utsmap`

```text
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
│   │
│   ├── components/
│   │   ├── [x] KostellaBottomBar.kt
│   │   ├── [x] KosCard.kt
│   │   ├── [x] NotificationCard.kt
│   │   └── [x] StepIndicator.kt
│   │
│   └── screen/
│       ├── search/
│       │   ├── [x] SearchScreen.kt
│       │   └── [x] SearchViewModel.kt
│       │
│       ├── detail/
│       │   ├── [x] DetailScreen.kt
│       │   └── [x] DetailViewModel.kt
│       │
│       ├── application/
│       │   ├── [x] ApplicationScreen.kt
│       │   └── [x] ApplicationViewModel.kt
│       │
│       ├── payment/
│       │   ├── [x] PaymentScreen.kt
│       │   └── [x] PaymentViewModel.kt
│       │
│       └── notification/
│           ├── [x] NotificationScreen.kt
│           └── [x] NotificationViewModel.kt
│
└── navigation/
    ├── [x] NavGraph.kt
    └── [x] ScreenRoutes.kt
```

---

## 3. DETAILED SCREEN SPECIFICATIONS (5 REQUIREMENT SCREENS)

### SCREEN 1: LAYAR LIST — SearchScreen (Kriteria Rubrik: List Screen)

**UI Components:**

- **Top Bar:** Header "Temukan kosmu" dengan sub-header "Dekat kampus, sesuai kantongmu".
- **Search Bar:** Outlined search field pre-filled dengan lokasi "UGM, Yogyakarta" dan filter button.
- **Filter Chips Row:** Horizontal scrollable filter chips (<= Rp1,5 juta, Lokasi, Fasilitas).
- **Counter Header:** Text "12 kos di sekitar UGM" dan tombol switch view "Peta".
- **Card Items (LazyColumn):**
  - Image Banner dengan badge gender/ketersediaan ("Putri - 3 kamar", "Campur - 2 kamar") dan ikon Heart/Favorite.
  - Title Kos: "Kos Cemara Residence" & "Kos Taman Sari".
  - Rating Badge: Ikon Bintang + Skor (4.8 / 4.7).
  - Distance & Facilities: Text "800 m ke UGM - AC, Wi-Fi, kamar mandi dalam".
  - Price: Highlighted bold text "Rp1.200.000 /bulan".
- **Bottom Navigation Bar:** Integrated navigation icons (Cari, Sewa, Tagihan, Akun).

**Interactive State:**

- Filter selection state.
- Search text input state.
- List item click action.

### SCREEN 2: LAYAR DETAIL — DetailScreen (Kriteria Rubrik: Detail Screen)

**UI Components:**

- **Top Bar:** Navigation Back Button, Title "Detail kos", More options action button.
- **Photo Gallery Section:** Main photo + side thumbnail grid dengan badge "1/8 foto".
- **Header Info:** Nama Kos, Alamat, Rating & Ulasan.
- **Pricing Breakdown Card:**
  - Sewa / bulan: Rp1.200.000
  - Listrik (tarif tetap): Rp100.000
  - Air: Rp50.000
  - Laundry (pilihan 5 kg): Rp75.000
  - Total dengan laundry: Rp1.425.000 (Bold Primary Color).
- **Facilities Row:** Badges ikon (AC, Wi-Fi, KM dalam, Ukuran 3x4 m).
- **Location & Distance Section:** Distance items.
- **Static Map Preview Mockup:** Card berisi gambar peta lokasi.
- **Sticky Bottom Action Bar:** Button "Ajukan Sewa" full width.

**Navigation Argument:** Accepting kosId dynamically from SearchScreen.

### SCREEN 3: LAYAR FORM — ApplicationScreen (Kriteria Rubrik: Form Input & Validation)

**UI Components:**

- **Top Bar:** Back Button, Title "Pengajuan sewa", Timestamp date.
- **Step Progress Indicator:** Horizontal progress bar (1. Pengajuan [Active] -> 2. Persetujuan -> 3. Pembayaran).
- **Selected Kos Card Summary:** Image thumbnail, Nama Kos, No. Kamar & Price.
- **Form Fields (Input Validation):**
  - Nama Lengkap: OutlinedTextField (KeyboardType.Text, Required field).
  - Nomor WhatsApp: OutlinedTextField (KeyboardType.Phone, Numeric-only validation).
  - Alamat Email: OutlinedTextField (KeyboardType.Email, Email format regex validation).
  - Durasi Sewa: OutlinedTextField (KeyboardType.Text).
  - Mulai Tinggal: OutlinedTextField (KeyboardType.Text) dengan ikon Kalender.
  - Catatan Tambahan (opsional): Multiline OutlinedTextField.
- **Information Banner:** Warning/Notice box.
- **Action Button:** Button "Kirim Pengajuan" (Disabled jika field mandatory tidak valid / kosong).

**State Behavior:**

- Real-time validation.
- Error state message (`isError`).
- Dynamic button state.

### SCREEN 4: LAYAR UI FITUR NATIVE MOCKUP — PaymentScreen (Kriteria Rubrik: Native Feature UI Mockup)

**UI Components:**

- **Top Bar:** Back Button, Title "Pembayaran", Sub-header date.
- **Status Banner:** Alert box hijau "Pengajuan disetujui. Kamar siap ditempati."
- **Invoice Card Summary:**
  - Invoice ID & Status: "INV-202610-A07", Status Badge dinamis.
  - Detail: Kos, kamar, periode, jatuh tempo.
  - Line Items: Sewa, Listrik, Air, Laundry.
  - Total Pembayaran: Rp1.425.000.
- **Payment Method Selection Box (TabRow):**
  - Option 1: BCA Virtual Account + Copy Code Button (Clipboard Native).
  - Option 2: QRIS Image (`qris.png`) — representasi mockup fitur native kamera.
- **Auto Verification Info Card.**
- **Sticky Action Button:** Button "Bayar Sekarang" dengan loading indicator.

**State:**

- `selectedTab` (VA/QRIS).
- `isLoading`.
- `paymentStatus`.

### SCREEN 5: LAYAR BEBAS PILIHAN — NotificationScreen (Kriteria Rubrik: Free Choice Screen)

**UI Components:**

- **Top Bar:** Title "Tagihan & notifikasi", Date & User context.
- **Active Bill Highlight Card:** EarthyBrown.
- **Filter Tabs Row:** Filter pills (Semua, Tagihan, Pembayaran).
- **Activity Timeline List (LazyColumn):**
  - Notification Item dengan icon berbeda per type (BILL/PAYMENT/INFO).
  - Badge status (Lunas/Jatuh Tempo).
- **Guarantee Information Box.**
- **Bottom Navigation Bar:** Tab "Tagihan" active state.

**State:** `selectedFilter` (Semua/Tagihan/Pembayaran).

---

## 4. CHECKPOINT STATUS & BRANCHING PLAN

- [x] **CHECKPOINT 1:** Foundation, M3 Theme Setup & Search List Screen
- [x] **CHECKPOINT 2:** Navigation & Detail Kos Screen
- [x] **CHECKPOINT 3:** Form Input & Validation / Pengajuan Sewa
- [x] **CHECKPOINT 4:** UI Fitur Native Mockup / Pembayaran QRIS
- [x] **CHECKPOINT 5:** Free Choice Screen / Tagihan & Notifikasi & Final
