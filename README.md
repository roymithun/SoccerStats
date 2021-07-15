# SoccerStats

|<img width="250" alt="Screenshot_1626387245" src="https://user-images.githubusercontent.com/5355440/125864716-9b0c32db-1fbf-4d3a-8e4d-868ac6e0b37c.png">
<img width="250" alt="Screenshot_1626387251" src="https://user-images.githubusercontent.com/5355440/125864725-7b46d3b8-d654-404e-b5a6-b5f3d0acdf35.png">
<img width="250" alt="Screenshot_1626387363" src="https://user-images.githubusercontent.com/5355440/125864751-7a3e8071-b00f-446b-a806-5c0cc89da031.png">

Intention of SoccerStats to pull some dummy responses for soccer matches from 2 sources and combine them together to show it in UI using a RecyclerView.

# Additional features:
  - Offline/Local storage
  - Pull to refresh RecyclerView
  - Multiple variants for development and production
  - Network awarness by listening to Connectivity Service

# Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous programming.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [Kotlin Flow](https://developer.android.com/kotlin/flow)
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
- [Dependency Injection](https://developer.android.com/training/dependency-injection) -
  - [Hilt-Dagger](https://dagger.dev/hilt/) - Standard library to incorporate Dagger dependency injection into an Android application.
  - [Hilt-ViewModel](https://developer.android.com/training/dependency-injection/hilt-jetpack) - DI for injecting `ViewModel`.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Moshi](https://github.com/square/moshi) - A modern JSON library for Kotlin and Java.
- [Moshi Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/moshi) - A Converter which uses Moshi for serialization to and from JSON.
- [SwipeRefreshLayout](https://developer.android.com/jetpack/androidx/releases/swiperefreshlayout)
- [Coil-kt](https://coil-kt.github.io/coil/) - An image loading library for Android backed by Kotlin Coroutines.

# Package Structure
    com.inhouse.soccerstats       # Root Package
    .
    ├── data                      # For data handling.
    │   ├── local                 # Local Persistence Database. Room (SQLite) database
    |   │   ├── dao               # Data Access Object for Room   
    |   |   └── database          # Room database
    │   ├── remote                # Remote Data Handlers     
    |   │   └── api               # Retrofit API for remote end point.
    |   |       └── apiservice   
    │   └── repository            # Single source of data.
    |
    ├── model                     # Model classes
    |   ├── match                 # room entity
    │   └── networmatch           # network facing model     
    |
    ├── di                        # Dependency Injection             
    │   ├── datamodule            # dao provider
    │   └── networkmodule         # network interface and moshi provider       
    |
    ├── ui                        # Activity/View layer
    |   ├── base  
    │   |   └── baseactivity      # Base View
    │   ├── main                  # Article List Screen & ViewModel
    |   │   ├── adapter           # Adapter for RecyclerView
    |   |   ├── fragment          # Article list Fragment
    |   │   └── viewmodel         # ViewHolder for RecyclerView   
    │   └── detail                # Detail Screen Fragment
    |   │   ├── fragment
    |   │   └── viewmodel
    |   │ 
    |   └── di                    # Dependency Injection in ui
    |       └── viewmodelmodule   # ViewModelComponenet module
    └── utils                     # Utility Classes / Kotlin extensions

# Design Ideology

 ![image](https://user-images.githubusercontent.com/5355440/125871454-78633ac4-36ed-4ea2-b955-6b3cbf247cbd.png)
