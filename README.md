# Inside Out App

The Inside Out App is an Android application designed to introduce and showcase the characters from the *Inside Out 2* animation film. Users can interact with various character switches to view quotes and play background music related to the characters.

## Features

- **Character Switches**: Toggle switches to view quotes from different characters in the animation. Each switch dynamically updates the app's menu items and functionality.
- **Dynamic Menu Management**: Automatically add and remove items from the `BottomNavigationView` based on the selected character.
- **Music Playback**: Control background music playback with options to play and pause.
- **User Interaction**: Engaging and responsive UI that reflects changes based on user interactions with the character switches.

## Installation

### Requirements
- Android Studio 2024 or newer
- Kotlin 1.9.0
- Android API 21 or higher

### Steps
1. **Clone the Project**:
   ```bash
   git clone https://github.com/kvkfurkan/InsideOut

2. **Sync Project**:

   Open the project in Android Studio and click on Sync Project with Gradle Files.

3. **Music Controls**:

- Play: Click the "Play" button to start the background music.
- Pause: Click the "Pause" button to pause the music.

3. **Add Resources**:
  - Add mp3 files in the res/raw directory.


### Usage

1. Run the App: Run your application in Android Studio.

2. Interact with Switches: Use the switches to view quotes from different characters. The BottomNavigationView updates based on the selected character.

3. Music Controls:

    Play: Click the "Play" button to start the background music.
    
    Pause: Click the "Pause" button to pause the music.


### Contributing
If you would like to contribute, please open a pull request or create an issue to provide feedback. All contributions are welcome!

### License

This project is licensed under the GPL License. See the LICENSE file for details.

### Libraries and Dependencies

Glide: 4.16.0

Kotlin: 1.9.0

AndroidX Core KTX: 1.13.1

JUnit: 4.13.2

Espresso Core: 3.6.1

AppCompat: 1.7.0

Material Components: 1.12.0

Activity: 1.9.1

ConstraintLayout: 2.1.4

Navigation Fragment KTX: 2.7.7
