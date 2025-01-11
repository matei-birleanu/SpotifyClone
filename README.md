# GlobalWaves Project

**Author:** BIRLEANU TEODOR MATEI 324CA  

---

### Overview
The GlobalWaves project is a Spotify-like application designed to manage users, songs, podcasts, and playback functionality. It includes features for generating user-specific statistics, monetization management, recommendations, and detailed playback tracking. The program also incorporates robust design patterns for scalability and maintainability.

---

### Functionalities

#### 1. **Statistics Generation**
- Tracks playback statistics for albums, songs, artists, and podcasts.
- Updates the number of listens dynamically based on playback data.
- Handles time-based statistics using the `listening` method to track content consumption between actions like loading or searching.

#### 2. **Notifications**
- All users can receive notifications about new albums, merchandise, or events.
- Notifications are managed through a dedicated class containing their name and description.

#### 3. **Recommendations**
- Recommends songs of the same genre as the currently playing one if the song has been played for at least 30 seconds.
- Uses a random utility to generate and display recommendations.

#### 4. **Playback and State Management**
- Allows playback of albums, songs, and podcasts, with detailed tracking of what has been consumed.
- Simulates playback pause and resume when users go offline or return online, using the `updateTimestamp` method.

#### 5. **End-of-Program Summary**
- Provides a summary of playback data, including artist details and consumption statistics.
- Uses the `EndProgramOutput` class to aggregate and display the results.

#### 6. **Content Management**
- Supports adding, deleting, and modifying users, songs, albums, and podcasts.
- Ensures proper cascading deletions (e.g., deleting an album also removes its songs).

#### 7. **Page Navigation**
- Implements a navigation system to move between pages (e.g., user playlists, announcements, albums).
- Tracks the current and previous pages to enhance user interaction.

---

### Design Patterns

#### 1. **Singleton Pattern**
- Ensures a single instance of the `Admin` class is used throughout the application.
- Provides a global access point for managing users, songs, and podcasts.

#### 2. **Command Pattern**
- Simplifies navigation between pages and the execution of commands.
- Allows easy extension of commands by encapsulating operations in a command interface.

#### 3. **Factory Pattern**
- Used in the `PageFactory` class to dynamically create pages based on their type.
- Provides a modular and extensible solution for page creation and management.

#### 4. **Strategy Pattern**
- Implements shuffle functionality for playlists.
- Encapsulates the shuffle logic in a `ShuffleStrategy` interface and a `RandomStrategy` class for reuse across the program.

---

### Key Classes

1. **`CommandRunner`**
   - Centralizes command execution and connects with the `Main` class for user input handling.

2. **`User`**
   - Manages user-specific data, including playback statistics, notifications, and recommendations.

3. **`Admin`**
   - Handles global data storage and operations for users, songs, and podcasts.
   - Ensures proper cascading updates when modifying the system's content.

4. **`EndProgramOutput`**
   - Aggregates playback statistics and artist data for the program's final summary.

---

### Highlights
- Comprehensive playback tracking and user-specific statistics.
- Scalable design using well-known patterns for maintainability.
- Enhanced user experience through personalized notifications and recommendations.
- Robust management of content and playback state.

The GlobalWaves project showcases efficient and modular software design, making it a versatile and powerful application for managing digital media content.
