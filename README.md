# BomberMan
Some explainations related to the Continious deployment(CD)
For our project,each time you commit or do a pull request.
The workflow action will generate the brand new version of the production jar files.
Two tasks are set in the configuration file of gradle.
One called player1,this task is created in order to generate the jar file for the game running.
The other called server,which can generate the jar file to set up the server.
//
Based on this,we also created two more tasks seperately runnning these two jar file.
Runserver for server,and runExecutableJar for the game. 
Right now we can easily run the game and the server by type in gradle <task_name>(working successfully).
And as we planned,we should also be able to run the game and server by simply double-click the jar files(JRE)
But sadly not,server.jar works kind of okay,the demo.jar only pops out a extremly small window with no graphic inside.
The generating process of the gradle is working quit fine,we have proved this by manually export our project as a jar file by eclipse(the same issue happnened)
We have tried to google for a possible solution for this ,but this problem is really kind of unique(it might somewhere went wrong with the src code).
Let's make a summary for this
We have CD working to generate jar files which can be easily used by the end user.one of the jar file doesn't work as we expected,But we can stil run this project by calling the tasks inside build.gradle which can excute a jar file. 
##User Manual

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
