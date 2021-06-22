# Android App Remote Control Joystick

 An android app with remote control joystick on flight gear.
 ![Joystick Emulator](https://github.com/sarits991/JoystickAndroidApp/blob/develop/attached_files/JoystickEmulator.PNG)
 
## Getting Started
The following instructions will guide you how to install FlightGear and run the android application.
 * ### FlightGear installation and operation
   * [Download and install FlightGear](https://www.flightgear.org/download/) 
   * Press the settings button and change to the following settings:
      ```
         --telnet=socket,in,10,127.0.0.1,6400,tcp 
      ```
   * Run the flight simulator by press the Fly button.
   * Press on Cessna C172P and choose the Autostart to start simulation.
 * ### Set up JDK
    * Run `java -version` to check if JDK is already installed.
      If this command has an error - please install JDK and set up JAVA_HOME.
 * ### Operating Instructions in Android Studio
   *  [Download and install Android Studio](https://developer.android.com/studio) 
   *  Import the github project into Android Studio.
   *  Create android emulator: Device Pixel 3A with API 30.
   *  Run the app on an emulator. 

## App Operating Instructions
   * ### Connect to the simulator:
     * Insert the **ip** of the computer you installed the simulator.
     * Insert **6400** as the port of the simulator.
     * Press on the **connect** button.
       If the connection was successful a popup message will appear with the message **connection successfully**
       If the connection failed a popup message will appear with the message **connection failure**
       If the data is invalid (null or empty) a popup message will appear with the message **invalid data**
   * ### Remote Control:
     **This controls will be enabled only after connection to the simulator** 
     * Use the **throttle** seek bar in order to control the engine of the aircraft.
     * Use the **rudder** seek bar in order to control the position of the nose of the aircraft.
     * Use the **joystick** to control the motion of the aircraft.

## Project Structure
 
  The project structure is based on Android App structure, we will explore the main parts:
  1. **Manifests folder** : this folder contains AndroidManifest.xml file for creating the android application.
  2. **Java folder** : this folder contains all the java source code. 
      The project architecture is based on MVVM design pattern.Therefore, this folder divide into three main parts:
      * **Model** : the model holds the logic business: connect to the fg, and send to the simulator the new values of the aircraft. The model run the requests in a different thread.
      * **ViewModel** : the view model implements the logic of the view, holds instance of the model. The view model link between the Model and the View
      * **View** : the view represents the UI of the app. Has the MainActivity class and a class for the joystick view.
  3. **Resource folder** : this folder contains the XML layouts, UI strings and more.
      * **Layout folder** : this folder contains all XML layout files, including the activity_main.xml file.
      * **Values folder** : this folder contains strings.xml file to define the UI strings.
  4. **build.gradle** :  defines the app module build configuration.

## Features and Design Patterns
   * Show a popup message if the connection success/ connection failed/ ip or port is empty.
   * Using Thread Pool design pattern in order to run the logic in the model in a different thread than the main activity thread.
   * Using dependency injection with Strategy Pattern in the Joystick view in order to make this class reusable in other projects.
   * The controls in the view (joystick and seek bars) will be enabled only after connection to the simulator
   
## Class Diagram
The class diagram has three layers according to MVVM architecture : Model, ViewModel, View

![ClassDiagram](https://github.com/sarits991/JoystickAndroidApp/blob/develop/attached_files/ClassDiagram.png)