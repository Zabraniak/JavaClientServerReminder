# Java Client-Server Reminder

![Java](https://img.shields.io/badge/Java-v8%2B-blue)
![License](https://img.shields.io/badge/license-MIT-green)

## Table of Contents

- [Description](#description)
- [Prerequisites](#prerequisites)
- [Features](#features)
- [Usage](#usage)
- [Screenshots](#screenshots)
- [Contributing](#contributing)
- [License](#license)
- [Info](#info)

## Description

The JavaClientServerReminder is a simple client-server reminder application built in Java. It allows users to send messages with specified delays to a server, which will then send notifications to the client when it's time to display the messages. This application is a basic example of socket communication in Java and serves as a reminder system.

## Prerequisites

Before using the JavaClientServerReminder, ensure you have the following prerequisites installed and configured:
Java Development Kit (JDK): Java is required to run both the client and server applications. You can download it from Oracle's website or use OpenJDK.

## Features

- Client-Server Communication: The application uses socket-based communication to allow the client to send messages and delays to the server.
- Reminder System: Users can set reminders by specifying a message and a delay in seconds. The server will notify the client when it's time to display the message.
- Error Handling: The application includes error handling for invalid IP addresses, port numbers, and delays to ensure a smooth user experience.
- Colors (self-explanatory) mostly server-side communication.

## Usage

1. Clone the repository:

   ```sh
   git clone https://github.com/Zabraniak/JavaClientServerReminder.git

2. Navigate to the repository folder.

3. Compile the Code:

   ```sh
   javac Server.java
   javac Client.java
   / or javac *.java

4. Start the Server: (optional port)

   ```sh
   java Server.java port

5. Start the Client: (optional IP, port)

   ```sh
   java Client.java IP port

Clients can connect to the server by specifying the server's IP address and port number.
Clients send messages and delays to the server, which schedules and notifies clients when it's time to display the messages.
Multiple clients can connect to the server concurrently to schedule and receive reminders.

## Screenshots

| SERVER START | CLIENT START |
| ----- | ----- |
| ![SERVER](https://i.imgur.com/qUgjbBL.png) | ![CLIENT](https://i.imgur.com/4xOuif4.png) |

| SERVER RECEIVING | CLIENT SENDING |
| ----- | ----- |
| ![RECEIVING](https://i.imgur.com/B11n0hA.png) | ![SENDING](https://i.imgur.com/mMnvHRw.png) |

| SERVER RESPONSE | CLIENT NOTIFICATION |
| ----- | ----- |
| ![RESPONSE](https://i.imgur.com/zjLy1XA.png) | ![NOTIFICATION](https://i.imgur.com/dJbFjJC.png) |

## Contributing
Contributions are welcome! If you have any improvements or new features to add, you can fork this repository, make your changes, and submit a pull request.

## License
This project is licensed under the MIT License.

## Info
The project demonstrates the principles of network communication, multi-threading, and error handling in Java, providing a foundation for more complex client-server applications. Users can compile and run the client and server components to set reminders and receive timely notifications.
