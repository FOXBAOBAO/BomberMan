# BomberMan
## Some explainations related to the Continious deployment(CD)
1. [Go to the release dashboard.](https://github.com/FOXBAOBAO/BomberMan/releases),and download sourcecode. 

2. [Download and install Gradle lastest version from the official website](https://gradle.org/install)

 It must be the lastest version.

3. Go to the directory where the sourcecode is located.

4. Run the command `gradle init` and `gradle wrapper`

5. Go inside the folder `BomberMan-1.0-beta` and run the command `gradle build`

6. In the Linux terminal, first run the server by using the command: `gradle server` and `gradle runserver`

 Then open 2 more terminal instances to run the client by using the command: `gradle player1` and `gradle runExecutableJar`


## User Manual

This is a two-player battle game.
Initial Interface

![Image text](https://github.com/FOXBAOBAO/BomberMan/blob/CreateCI/images/interface.png)

The user may enter any User name or password to login.(You can even login without user name😊)

Lobby Interface:
![Image text](https://github.com/FOXBAOBAO/BomberMan/blob/CreateCI/images/lobby%20interface.png)

Each player should choose a different role.
Use the Ready button to get ready.
 
The chat room section is for players to communicate with each other.
After writing your message you may click the send button or just use the enter button from your keyboard to send the message.

![Image text](https://github.com/FOXBAOBAO/BomberMan/blob/CreateCI/images/message%20section.png)

If both players are ready, the game starts automatically.

![Image text](https://github.com/FOXBAOBAO/BomberMan/blob/CreateCI/images/two%20players%20ready.png)

Keyboard instruction:
↑Face upwards
↓Face downwards
←Face leftwards
→Face rightwards
Space: Place a bomb

If one of the players uses a bomb to kill another player, then he wins.
![Image text](https://github.com/FOXBAOBAO/BomberMan/blob/CreateCI/images/battle.png)
