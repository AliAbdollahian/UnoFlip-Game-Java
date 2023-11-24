SYSC3110 Fall 2023 Project - Uno Flip card game! Milestone 2 Version 1.0; 14/11/2023

The project can be reached at:
Website: www.brightspace.carleton.ca
Email: wardasaleh@cmail.carleton.ca
Email: aliabdollahian@cmail.carleton.ca
Email: antoniocinottiballa@cmail.carleton.ca
Email: arkanslabi@cmail.carleton.ca

Authors:
___________

1. Ali Abdollahian
2. Antonio Cinotti Ballarte
3. Arkan Slabi
4. Warda Saleh


Project Description:
___________

- This project is based on programming a version of the Uno Flip card game
- All classes and their descritions of the text-based playable version are listed below and in a package
- UnoGame.java: Main class which handles the Uno Flip card Game and relevant interactions 
- Deck.java: Holds the deck of cards and discard pile for the game with shuffling and add/remove methods
- Player.java: Class containing the players of the games scores, names, and hand
- Card.java: associates each card with light & dark colours & values with methods to retrieve them
- Value.java: Contains enumurator of the values/Action Cards 
- Side.java: Contains enumarator of the sides of cards, LIGHT & DARK
- Color.java: Contains enumarator of possible colours for each card
- UnoGameModel: Uno Game's dynamic structures class which manages the logic of the game
- UnoGameController: Accept user input actions and converts it to commands for the model.
- UnoGameView: This the the visual representation class of the UNO Flip Game.
- UnoGame.Test
- Deck.Test
- Player.Test
- Card.Test


Contributions:
___________

Ali Abdollahian
Milestone 1
- Worked on developing UnoGame Class 
- Contributed to UML diagrams 
- Completed documentation for UNOGame class
- Attempted JUnit testing for UNOGame class
- Contributed to Sequence diagram
Milestone 2
- Developed the MVC Model in UnoGame.java
- Completed documentation for the Model
- Contributed to the MVC View
- Completed UML diagram
- Completed Sequence diagram
Milestone 3
- Worked on documentation. 
- Completed Section 1: Uno Flip Integration.
- Worked on Section 2: AI Player Capability.

Antonio Cinotti Ballarte
- Worked on developing UnoGame Class 
- Contributed to UML diagrams 
- Completed documentation for UNOGame class
- Attempted JUnit testing for UNOGame class
- Contributed to Sequence diagram
Milestone 2
- Developed the MVC Controller
- Completed documentation for the UnoController
Milestone 3
- Worked on Section 1: Uno Flip Integration.

Arkan Slabi
- Developed Player class 
- Contributed to readme file
- Contributed to UML class diagrams 
- Contributed to Sequence diagram 
- Completed JUnit testing for Player Class 
Milestone 2
- Worked on developing the MVC View

Warda Saleh 
- Developed Deck class
- Developed Card class
- Developed enumerator classes for Value, Side, and Color
- Contributed to UML class diagrams
- Completed documentation for Deck, Color, Value, Card, Side, and Player.
- Contributed to Sequence diagram
- Contributed to readme file
- Completed JUnit testing for Deck and Card classes.
Milestone 2
- Worked on developing the MVC View
- Updated readme file
- Completed documentation for UnoView class
Milestone 3
- Worked on documentation.
- Updated readme file
- Section 2: AI Player Capability
- Completed UML diagram


Running Instrctions:
- Replace the directory path in the UnoView.java, method: displaySampleCards().
- After entering the player's information, enlarge the view screen in the top left corner.


Milestone 1 Deliverables:
___________
- Items below are stored in a zip file
- code (source + executable in jar file)
- readme file explaining 
- UML diagram showing the classes and relations
- Sequence Diagram
- Data Structure Explanation

Milestone 2 Deliverables:
___________
- Items below are stored in a zip file
- code (source + executable in jar file)
- readme file explaining 
- UML diagram showing the classes and relations
- Sequence Diagram
- Data Structure Explanation

Milestone 3 Deliverables:
___________
- Items below are stored in a zip file
- code (source + executable in jar file)
- readme file explaining 
- UML diagram showing the classes and relations


Milestone 1 Known Issues:
___________
- TestPlayer.java is not updated
- Wild method in UNOGame.java returns null

Milestone 2 Known Issues:
___________
- Buttons do not work.
- Player info is not saved.




next steps:
___________
- Transition the text-based playable version of Uno Flip to a GUI-based iteration (Done)
- Add Uno Flip cards to game with related rules and scoring mechanisms (Done)
- Implement AI Player Capability with intelligent gameplay
- Add Redo Capabilities so players can undo moves made by mistake
- Add Replay Capability of the game
- Implement a Save/Load system 


Milestones:
___________

- Milestone 1 (completed milestone): Develop a text-based, playable version of Uno Flip via console using keyboard input
- Milestone 2: Develop a GUI-based iteration of Uno Flip using an MVC design pattern (model-view-controller)
- Milestone 3: Add Uno FLip Integration and AI Player Capability 
- Milestone 4: Implement Redo capabilities, Replay Capability, and Save/Load Features

Installation:
____________

IntelliJ IDEA 2023.2 recommended or any other Java IDE
JDK 21 recommended or any other version must be installed

Rules:
___________

https://www.ultraboardgames.com/uno/flip-game-rules.php
