# NewsKMPApp

> Kotlin Multiplatform news application demonstrating Clean Architecture and MVI pattern

---

## 📌 Overview

A **Kotlin Multiplatform** educational project showing how to build a production-ready news app with **70% code sharing** between Android and iOS.

**What it does:**
- Fetches news articles from News API
- Caches data locally with SQL Delight
- Supports offline mode and pull-to-refresh
- Native UI on Android (Compose) and iOS (SwiftUI)

**API:** [NewsAPI.org](https://newsapi.org/) - Top headlines by country/category

---

## 📱 App Demos

| Android | iOS |
|:---:|:---:|
| ![Android Demo](assets/android.gif) | ![iOS Demo](assets/iOS.gif) |

---

## 🏗️ Architecture

### Clean Architecture Layers

![Architectural Layers](assets/Architectural-Layers.png)

**Three layers:**
- **Domain** (Pink) - Business logic, use cases, entities
- **Data** (Green) - Repositories, API, database
- **Presentation** (Blue) - ViewModels, state management

### Data Flow

![Architectural Diagram](assets/Architectural-diagram.png)

```
UI → ViewModel → UseCase → Repository → DataSource → API/Database
```

### MVI Pattern

```
User Action → ViewModel → State Update → UI Renders
```

---

## 🛠️ Tech Stack

**Shared (70%)**
- Kotlin Multiplatform
- Ktor (Networking)
- SQL Delight (Database)
- Koin (DI)
- Coroutines

**Android (15%)**
- Jetpack Compose
- Material 3
- Coil (Images)

**iOS (15%)**
- SwiftUI
- Combine
- AsyncImage

---

## 📁 Project Structure

```
shared/
├── commonMain/          # Shared code (70%)
│   ├── articles/
│   │   ├── data/        # API, database, repository
│   │   ├── domain/      # Entities, use cases
│   │   └── presentation/ # ViewModel, state
│   ├── categories/
│   └── di/              # Dependency injection
├── androidMain/         # Android-specific
└── iosMain/             # iOS-specific

androidApp/              # Android UI (Compose)
iosApp/                  # iOS UI (SwiftUI)
```

---

## 🚀 Quick Start

### Prerequisites
- Android Studio (latest)
- Xcode 14+ (for iOS)
- JDK 17+

### Run Android
```bash
./gradlew :androidApp:assembleDebug
```
Or use Android Studio Run ▶️

### Run iOS
Open `iosApp/iosApp.xcodeproj` in Xcode and press ⌘R

---



---

## 🎓 Learning Path

**Beginner** → Clone, run, explore UI  
**Intermediate** → Study architecture, add features  
**Advanced** → Write tests, optimize performance  

---

## 📚 Resources

- [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html)
- [Ktor](https://ktor.io/)
- [SQL Delight](https://cashapp.github.io/sqldelight/)
- [Koin](https://insert-koin.io/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)

---


## ✨ Key Highlights

✅ **70% code sharing** - Business logic, networking, database  
✅ **Clean Architecture** - Clear separation of layers  
✅ **MVI Pattern** - Predictable state management  
✅ **Offline-first** - Local caching with SQL Delight  
✅ **Type-safe** - Kotlin + SQL Delight  
✅ **Native UI** - Compose & SwiftUI  

---

**Author:** Moamen Zalabya  
**License:** Educational Use

---

**Happy Learning! 🚀**
