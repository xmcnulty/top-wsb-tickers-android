<div align="center">

# Trendies

### Lightweight Open-Source WallStreetBets Monitor

[![Android](https://img.shields.io/badge/Android-grey?logo=android&style=flat)](https://www.android.com/)
[![AndroidAPI](https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat)](https://www.android.com/)
[![Kotlin](https://img.shields.io/badge/kotlin-1.7.20-blue.svg?logo=kotlin)](https://kotlinlang.org)
[![JetpackCompose](https://img.shields.io/badge/Jetpack%20Compose-1.3.3-yellow)](https://developer.android.com/jetpack/compose)
[![CodeFactor](https://www.codefactor.io/repository/github/xmcnulty/wsb-trendies/badge)](https://www.codefactor.io/repository/github/xmcnulty/wsb-trendies)

</div>

---

Lightweight, fast, and open-source monitoring of r/wallstreetbets for Android 📱📈 No Ads, no trackers.

## Table of Contents
- [Visual Overview](#visual-overview)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Technical Details](#technical-details)
- [Contribution](#contribution)
- [License](#license)
- [Acknowledgments](#acknowledgments)

## Visual Overview

<div align="center">

### 📊 **Trending Stocks Overview**
<img src="https://github.com/xmcnulty/wsb-trendies/blob/develop/metadata/images/list-screenshot.png" width="45%" alt="Trending on WallStreetBets" />

This view showcases the trending stocks on r/WallStreetBets with their sentiment indicators. Users can view at a glance which stocks are being discussed the most and the overall sentiment (bullish/bearish) for each.

### 📈 **Detailed Stock Insights**
<img src="https://github.com/xmcnulty/wsb-trendies/blob/develop/metadata/images/detail-screenshot.png" width="45%" alt="Stock Detailed View" />

Dive deeper into any stock to see its performance chart, essential details, and recent comments from the r/WallStreetBets community. This provides a comprehensive look at the stock's current sentiment and market data.

</div>

## Features

- **Trending**: up-to-date list of the trending stock tickers mentioned on the r/wallstreetbets subreddit.
- **Details**: view comments, sentiment, and market details for each trending stock.
- **Lightweight**: Stores data locally and updates them automatically only when needed, minimizing network data and battery consumption.
- **Designed for Android**: The User Interface has been designed following the latest Google's Material Design guidelines, using only native Android components and animations.

## Installation
To install and run Trendies on your device, follow these steps:

1. Clone the repository.
2. Open the project in Android Studio.
3. Run the app on your emulator or physical device.

## Usage
After installation, open the app and:
- Browse trending stock tickers from the main screen.
- Tap on any ticker to view its detailed insights.

## Technical Details

- **100% Jetpack Compose** 🚀: The app's UI is entirely built using the modern declarative UI toolkit Jetpack Compose, ensuring a consistent and updated user interface experience.
  
- **Material Design 3** 💎: The latest material design principles have been implemented, ensuring a modern and sleek user experience.
  
- **MVVM Architecture**: The app follows the Model-View-ViewModel architectural pattern, promoting separation of concerns and making the codebase modular and easier to maintain.
  
- **Networking with Retrofit**: Data from r/WallStreetBets is fetched using the Retrofit library, coupled with OkHttp for efficient HTTP requests.
  
- **Data Persistence with Room**: The app uses the Room library for data persistence, ensuring efficient data storage and retrieval with an SQLite backend.
  
- **Performance Optimizations**: The app employs efficient data structures and algorithms, along with caching mechanisms, to ensure smooth performance even with large volumes of data.
  
- **Other Libraries**: Additional tools and libraries used include Glide for image loading and Dagger Hilt for dependency injection, streamlining development and ensuring efficient operations.

## Contribution
Contributions are welcomed! If you have suggestions or issues, please open a GitHub issue. If you'd like to improve the code or add a feature, please send a pull request.

## License
This project is licensed under the MIT License. See the [LICENSE.md](LICENSE.md) file for details.

## Acknowledgments
- Special thanks to [Philipp Lackner](https://www.youtube.com/@PhilippLackner) for his amazing video guides and tutorials on Android development.
