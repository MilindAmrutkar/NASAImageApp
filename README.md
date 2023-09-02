# NASA Image of the Day App

This Android application utilizes the NASA APOD (Astronomy Picture of the Day) API to fetch and
display space images.
It uses a Clean Architecture approach, with Jetpack Compose for the UI, Retrofit for API calls, and
Room for local database storage.
The app supports an offline-first approach, allowing users to access data even when they are not
connected to the Internet.

## Features

- Fetch and display the Astronomy Picture of the Day from NASA API
- Uses Clean Architecture (core, data, domain, UI layers)
- Provides offline support through Room Database
- Jetpack Compose UI

## Packages

- **core**
  - **di**: Dependency injection setup with Hilt.
  - **network**: Network-related classes like ApiResponse.
  - **utils**: Utility classes like Constants.

- **data**
  - **remote**: API call classes and DTO (Data Transfer Objects).
  - **repository**: Data source abstraction and repository classes.
  - **local**: Local data source with Room Database entities and DAOs.

- **domain**
  - **model**: Data classes that represent the business logic.
  - **usecase**: Business logic is separated into use cases.

- **ui**
  - **view**: Composable functions that define the UI.
  - **viewmodel**: ViewModel classes that interact with use cases and provide data to UI.

## Dependencies

- Jetpack Compose for UI
- Retrofit for network requests
- Moshi for JSON parsing
- Hilt for Dependency Injection
- Room for offline database
- Kotlin Coroutines for asynchronous programming
- Coil for image loading

## How to Run

1. Clone this repository.
   git clone https://github.com/YourUsername/NASAImageApp.git

2. Open the project in Android Studio.
3. Sync Gradle and build the project.
4. Run on an emulator or actual device.

## API Key Configuration

To access the NASA APOD API, you'll need an API key.

1. Register for an API key from [NASA's API portal](https://api.nasa.gov/).
2. Open `local.properties` file and add the following line:
    ```properties
    nasaApiKey="your_nasa_api_key_here
    ```
3. Sync your project.

## Screenshots

![img.png](app_screenshot.png)

