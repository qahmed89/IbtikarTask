# IbtikarTask
IbtitakrTask 
This is a sample project that presents a modern, 2020 approach to Android application development with up to date tech-stack.

The goal of the project is to demonstrate best practices by using up to date tech-stack and presenting modern Android application Architecture that is modular, scalable, maintainable, and testable. This application may look quite simple, but it has all of these small details that will set the rock-solid foundation for the larger app suitable for bigger teams and long application lifecycle.
Environment Setup
First off, you require the latest Android Studio 4.0.1 (or newer) to be able to build the app.

You need to supply API key for TMDb,  The Movie Database (TMDb) API. Below you will find a current list of the available methods on our movie, tv, actor and image API.
https://developers.themoviedb.org/3/getting-started/introduction



# Architecture
The architecture of the application is based, apply and strictly complies with each of the following 5 points:

- A single-activity architecture, using the Navigation component to manage fragment operations.
- Android architecture components, part of Android Jetpack for give to project a robust design, testable and maintainable.
- Pattern Model-View-ViewModel (MVVM) facilitating a separation of development of the graphical user interface.
- S.O.L.I.D design principles intended to make software designs more understandable, flexible and maintainable.
- Modular app architecture allows to be developed features in isolation, independently from other features.
Tech-stack

# Jetpack:
- Android KTX - provide concise, idiomatic Kotlin to Jetpack and Android platform APIs.
- AndroidX - major improvement to the original Android Support Library, which is no longer maintained.
- View Binding - allows you to more easily write code that interacts with views/- 
- Lifecycle - perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
- LiveData - lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services.
- Navigation - helps you implement navigation, from simple button clicks to more complex patterns, such as app bars and the navigation drawer.
- ViewModel - designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.
- Coroutines - managing background threads with simplified code and reducing needs for callbacks.
- Dagger Hilts - Hilt provides a standard way to incorporate Dagger dependency injection into an Android application.
- Retrofit - type-safe HTTP client.
- Glide - image loading and caching library
