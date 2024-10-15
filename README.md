# 🎧 Audio Player Application
**Author**: [Ionita Alexandru-Andrei]  
**Year**: 2023-2024

## 📜 Description
This project is a multi-phase application simulating an audio streaming platform, similar in functionality to Spotify. It is structured in stages, each focused on enhancing the platform's capabilities, such as 🎶 **audio management** and 🖥️ **user interaction**. The application handles operations for both audio files and user interface navigation, using commands received via input files to simulate actions from various types of users (normal users, artists, hosts).

## 🎼 Stage 1: Audio Player

In this first stage, we implemented core **audio player functionality** that allows users to interact with different types of audio files. 

### 📂 Entities:
- **Audio Files**: 🎵 Consist of songs and podcast episodes, which users can interact with.
- **Collections**: Audio files are categorized into 📚 **libraries**, 🎶 **playlists**, and 🎙️ **podcasts**, each offering various interaction options.
  
### 🌟 Features:
1. 🔍 **Search Bar**: Users can search for songs, playlists, or podcasts based on criteria such as name, artist, tags, lyrics, and genre.
2. ▶️ **Music Player**: The application simulates an audio player, where users can load, play, pause, skip, and shuffle tracks, with support for repeat modes.
3. 🎶 **User Playlists**: Users can create and manage playlists, either private or public, with tracks from the library.
4. 🧑‍🤝‍🧑 **User Management**: Each user has unique interactions with the platform, from playlist creation to music search.
5. ⏱️ **Player Status**: A timestamp is used to simulate real-time playback and user interactions.

## 📄 Stage 2: Pagination System

The second phase extends the application by implementing a **pagination system** that mimics the GUI of an audio streaming service. Users can now navigate through different pages, each containing relevant content based on their interactions with the platform.

### 🔥 New Features:
1. 🏠 **HomePage**: Shows static "recommendations" based on liked songs and followed playlists.
2. ❤️ **LikedContentPage**: Displays all songs and playlists a user has liked or followed.
3. 🎨 **Artist Page**: Allows users to view albums, merch, and events from their favorite artists.
4. 🎙️ **Host Page**: Displays podcasts and announcements from hosts, with users able to view detailed information for each.
  
### 📜 Commands for Pagination:
- 🛤️ **ChangePage**: Allows users to navigate between pages (HomePage, LikedContentPage, Artist Page, Host Page).
- 🖨️ **PrintCurrentPage**: Displays the current page with relevant content formatted for display.

## ⚙️ Key Functionalities
- **User Interaction**: Users can like, follow, and interact with content, with each command reflected in real-time on the respective pages.
- **Admin Controls**: Admins can manage users and content (adding/removing users, albums, podcasts, events).

## 🎯 Conclusion
This project helped in understanding object-oriented design principles, refactoring for scalability, and implementing design patterns. The addition of the pagination system provided insights into how UI components are managed in a real-world application.
