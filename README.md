<h1 align="center">Note.AI</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/skydoves/pokedex-compose/actions"><img alt="Build Status" src="https://github.com/skydoves/pokedex-compose/workflows/Android%20CI/badge.svg"/></a> <br>
  <a href="https://devlibrary.withgoogle.com/products/android/repos/skydoves-pokedex"><img alt="Google" src="https://skydoves.github.io/badges/google-devlib.svg"/></a>
  <a href="https://proandroiddev.com/exploring-dagger-hilt-and-whats-main-differences-with-dagger-android-c8c54cd92f18"><img alt="Medium" src="https://skydoves.github.io/badges/Story-Medium.svg"/></a>
  <a href="https://youtu.be/RG9ATodwbT4"><img alt="Profile" src="https://skydoves.github.io/badges/youtube-google-developers.svg"/></a> 
  <a href="https://github.com/doveletter"><img alt="Profile" src="https://skydoves.github.io/badges/dove-letter.svg"/></a>
</p>

<p align="center">  
🤖 Note.AI modern Android development with Jetpack Compose, Hilt, Coroutines, Flow, Jetpack (Room, ViewModel), and Material Design based on MVVM architecture.
</p>

<p align="center">
<img src="previews/screenshot.png"/>
</p>

## Download
Go to the [Releases](https://github.com/skydoves/pokedex-compose/releases) to download the latest APK.

<img src="previews/preview.gif" align="right" width="320"/>

## Tech stack & Open-source libraries
- Minimum SDK level 26.
- [Kotlin](https://kotlinlang.org/) based, utilizing [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous operations.
- Jetpack Libraries:
    - Jetpack Compose: Android’s modern toolkit for declarative UI development.
    - Lifecycle: Observes Android lifecycles and manages UI states upon lifecycle changes.
    - ViewModel: Manages UI-related data and is lifecycle-aware, ensuring data survival through configuration changes.
    - Navigation: Facilitates screen navigation, complemented by [Hilt Navigation Compose](https://developer.android.com/jetpack/compose/libraries#hilt) for dependency injection.
    - Room: Constructs a database with an SQLite abstraction layer for seamless database access.
    - [Hilt](https://dagger.dev/hilt/): Facilitates dependency injection.
- Architecture:
    - MVVM Architecture (View - ViewModel - Model): Facilitates separation of concerns and promotes maintainability.
    - Repository Pattern: Acts as a mediator between different data sources and the application's business logic.
- [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization): Kotlin multiplatform / multi-format reflectionless serialization.
- [ksp](https://github.com/google/ksp): Kotlin Symbol Processing API for code generation and analysis.
- [Turbine](https://github.com/cashapp/turbine): A small testing library for kotlinx.coroutines Flow.
- [Baseline Profiles](https://medium.com/proandroiddev/improve-your-android-app-performance-with-baseline-profiles-297f388082e6): Enhances app performance by including specifications of classes and methods in the APK that can be utilized by Android Runtime.


## Architecture
**Note.AI** adheres to the MVVM architecture and implements the Repository pattern, aligning with [Google's official architecture guidance](https://developer.android.com/topic/architecture).

The architecture of **Note.AI** is structured into two distinct layers: the UI layer and the data layer. Each layer fulfills specific roles and responsibilities, outlined as follows:

**Note.AI** follows the principles outlined in the [Guide to app architecture](https://developer.android.com/topic/architecture), making it an exemplary demonstration of architectural concepts in practical application.

### Architecture Overview

![architecture](figure/figure1.png)

- Each layer adheres to the principles of [unidirectional event/data flow](https://developer.android.com/topic/architecture/ui-layer#udf): the UI layer sends user events to the data layer, and the data layer provides data streams to other layers.
- The data layer operates autonomously from other layers, maintaining purity without dependencies on external layers.

This loosely coupled architecture enhances component reusability and app scalability, facilitating seamless development and maintenance.

### UI Layer

![architecture](figure/figure2.png)

The UI layer encompasses UI elements responsible for configuring screens for user interaction, alongside the [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), which manages app states and restores data during configuration changes.
- UI elements observe the data flow, ensuring synchronization with the underlying data layer.

### Data Layer

![architecture](figure/figure3.png)

The data layer is composed of repositories that handle business logic tasks such as retrieving data from a local database or fetching remote data from a network. This layer is designed to prioritize offline access, functioning primarily as an offline-first repository of business logic. It adheres to the principle of "single source of truth," ensuring that all data operations are centralized and consistent.<br>

**Pokedex Compose** is an offline-first app, meaning it can perform all or most of its essential functions without an internet connection. This design allows users to access core features reliably, regardless of network availability, reducing their need for constant updates and decreasing data usage. For more details on how to build an offline-first application, you can visit [Build an offline-first app](https://developer.android.com/topic/architecture/data-layer/offline-first).

## Open API
Note.AI use open.AI to transcription voice to text.

## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/skydoves/pokedex-compose/stargazers)__ for this repository. :star: <br>
Also, __[follow me](https://github.com/skydoves)__ on GitHub for my next creations! 🤩

# License MIT
```
Copyright (c) 2025 Gonzalo Lozg

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```