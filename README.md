

# Recipe Database and Meal Planning App
## Preview

<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/6346efe1-d70f-4564-9baf-76423d2555ca" /></td>
    <td><img src="https://github.com/user-attachments/assets/a6bc4b52-aa52-4ccc-aab6-6441d2efdce6" /></td>
  </tr>
</table>

## Overview

The Recipe Database and Meal Planning App is designed to help users discover new recipes, plan their meals, generate shopping lists, and stay organized with notifications and reminders. By integrating the Spoonacular API, this app provides a comprehensive and user-friendly experience with features such as search functionality, meal planning, shopping list generation, favorites, and notifications.

### Project Structure

<img src="https://github.com/user-attachments/assets/541eb101-079b-429a-8c1f-b1b6385fb53c" />


* **Clean Architecture:** Utilizes the modern Android approach of building apps with Clean Architecture and MVVM.
* **Offline-First Approach:** Caches data in Room DB when the internet connection is not available.
* **Network Observer:** Continuously observes network state and handles network changes.
* **Coil Image Loading:** Loads images using Coil, with or without loading animation.
* **Lib Versions Catalog:** Manages dependencies and their versions using `lib.versions.toml`.

## Built With

* **Android Studio Koala:** IDE used for development.
* **Kotlin:** Primary programming language.
* **KSP:** Kotlin Symbol Processing API for Hilt.
* **Jetpack Compose:** UI Toolkit for building native UIs.
* **Jetpack Libraries:** Includes Lifecycle, ViewModel, Navigation Compose, etc.
* **Hilt:** Dependency Injection framework.
* **Coil:** Image loading library.
* **Ktor Client:** Networking and API management.
* **Kotlin Coroutines & Flow:** For asynchronous tasks and data management.
* **Paging 3:** For efficient data paging and loading.
* **Room DB:** For local database storage and caching.
* **Alarm Manager:** Used Alarm Manager for scheduling notification of the recipe
## Features

### Fetch Recipes from Spoonacular API
- Fetch recipes based on various criteria such as ingredients, cuisine, meal type, dietary restrictions, etc.
- Display detailed recipe information including ingredients, instructions, nutrition information, and images.

### Search Functionality
- Search for recipes by ingredients, cuisine, meal type, dietary restrictions, and more.
- Use filters and sorting options to refine search results.

### Meal Planning
- Plan meals for a week or a month.
- Visualize meal plans with a calendar view.
- Add, edit, and remove meals from the plan.

### Notifications and Reminders
- Set reminders for meal prep, cooking times, and grocery shopping.
- Receive notifications to stay on track with scheduled tasks.

- **Recipe Management:** Add, edit, and delete recipes with detailed information.
- **Pagination:** Efficiently handle large datasets with smooth scrolling.
- **Offline Support:** Access and manage recipes even without an internet connection.
- **Reminders:** Schedule and manage reminders for recipes using AlarmManager.
- **Device Restart Handling:** Ensures reminders persist through device reboots.

## Implementation Plan

### Setup and Architecture
- Project setup in Android Studio using Kotlin or Java.
- MVVM architecture for better separation of concerns and testability.
- Integration of libraries such as Retrofit for networking, Room for local database, and WorkManager for background tasks.

### Fetch Recipes from Spoonacular API
- API service using Retrofit to interact with the Spoonacular API.
- Methods to fetch recipes based on various criteria.
- Parsing the API response and displaying recipe details in the UI.

### Search Functionality
- Implement a search bar and filters in the UI.
- Create a search feature that queries the Spoonacular API with user input.
- Display search results and detailed recipe information.

### Notifications and Reminders
- Use WorkManager to schedule and manage notifications.
- Functionality to set reminders for meal prep, cooking times, and grocery shopping.
- Send push notifications and local notifications for scheduled tasks.

## Getting Started

### Prerequisites
- Android Studio
- Spoonacular API Key

### Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/bibekanandan892/Recipe-Database.git
   ```
2. **Open the Project:**
   Open the project in [Android Studio](https://developer.android.com/studio).

3. **Sync Gradle:**
   Ensure all dependencies are up-to-date by syncing the Gradle files.

4. **Run the Application:**
   Connect an Android device or start an emulator and run the app from Android Studio.

### Usage
- Build and run the app on an Android device or emulator.
- Explore the features and plan your meals!

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit them (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.

## License

This project is licensed under the Apache License, Version 2.0. See the [LICENSE](LICENSE) file for more details.


## Contact

For any questions or suggestions, please reach out to bibekanandan892@gmail.com.

---
