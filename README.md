# SYSC3110 Fall 2023 Project - Uno Flip Card Game!  
**Milestone 4 Version 1.0**  
**Date:** 08/12/2023  

---

## Project Information  
- **Website:** [www.brightspace.carleton.ca](www.brightspace.carleton.ca)  
- **Emails:**  
  - [wardasaleh@cmail.carleton.ca](mailto:wardasaleh@cmail.carleton.ca)  
  - [aliabdollahian@cmail.carleton.ca](mailto:aliabdollahian@cmail.carleton.ca)  
  - [antoniocinottiballa@cmail.carleton.ca](mailto:antoniocinottiballa@cmail.carleton.ca)  
  - [arkanslabi@cmail.carleton.ca](mailto:arkanslabi@cmail.carleton.ca)  

---

## Authors  
1. Ali Abdollahian  
2. Antonio Cinotti Ballarte  
3. Warda Saleh  
4. Arkan Slabi  

---

## Project Description  

This project is a digital version of the Uno Flip card game.  

### Core Classes  
- **UnoGame.java**: Main class that handles the game logic and interactions.  
- **UnoDeck.java**: Manages the deck of cards and discard pile, including shuffling and card management.  
- **UnoPlayer.java**: Represents players, including scores, names, and hands.  
- **UnoCard.java**: Associates each card with light/dark colors, values, and methods for retrieval.  
- **UnoValue.java**: Enum for card values and action cards.  
- **UnoSide.java**: Enum for card sides (LIGHT/DARK).  
- **UnoColor.java**: Enum for possible card colors.  
- **UnoGameState.java**: Manages the state of the game.  
- **UnoGameModel.java**: Handles game logic using the MVC model.  
- **UnoGameController.java**: Processes user input and commands.  
- **UnoGameView.java**: Displays the GUI for the game.  

---

## Contributions  

### **Ali Abdollahian**  
#### Milestone 1  
- Developed **UnoGame** class.  
- Contributed to UML diagrams and sequence diagrams.  
- Completed documentation for **UnoGame** class.  
- Attempted JUnit testing for **UnoGame**.  
#### Milestone 2  
- Developed MVC Model in **UnoGame.java**.  
- Contributed to the MVC View and documentation.  
- Completed UML and sequence diagrams.  
#### Milestone 3  
- Completed Section 1: Uno Flip Integration.  
- Worked on Section 2: AI Player Capability.  
#### Milestone 4  
- Implemented functionality: Undo, Redo, and Replay.  
- Improved documentation and code quality.  

---

### **Antonio Cinotti Ballarte**  
#### Milestone 1  
- Worked on **UnoGame** class and JUnit testing.  
- Contributed to UML diagrams and sequence diagrams.  
- Completed documentation for **UnoGame**.  
#### Milestone 2  
- Developed MVC Controller and its documentation.  
#### Milestone 3  
- Contributed to Section 1: Uno Flip Integration.  
#### Milestone 4  
- No contributions.  

---

### **Warda Saleh**  
#### Milestone 1  
- Developed **Deck**, **Card**, and enum classes (**Value**, **Side**, and **Color**).  
- Contributed to UML diagrams, sequence diagrams, and documentation.  
- Completed JUnit testing for **Deck** and **Card** classes.  
#### Milestone 2  
- Contributed to MVC View and **UnoView** documentation.  
- Updated the README file.  
#### Milestone 3  
- Worked on Section 2: AI Player Capability.  
- Updated UML diagrams and README file.  
#### Milestone 4  
- Implemented Serialization & Deserialization.  
- Improved documentation and code quality.  

---

### **Arkan Slabi**  
#### Milestone 1  
- Developed **Player** class and its JUnit testing.  
- Contributed to UML diagrams, sequence diagrams, and README.  
#### Milestone 2  
- Worked on MVC View.  
#### Milestone 4  
- No contributions.  

---

## Running Instructions  

- When loading the game, re-enter player information exactly as it was initially saved.  

---

## Milestone Deliverables  

### **Milestone 1 Deliverables**  
- Source code and executable JAR file.  
- README file.  
- UML class diagrams and sequence diagrams.  
- Data structure explanation.  

### **Milestone 2 Deliverables**  
- Source code and executable JAR file.  
- README file.  
- UML class diagrams and sequence diagrams.  
- Data structure explanation.  

### **Milestone 3 Deliverables**  
- Source code and executable JAR file.  
- README file.  
- UML class diagrams and sequence diagrams.  

### **Milestone 4 Deliverables**  
- Source code and executable JAR file.  
- README file.  

---

## Known Issues  

### **Milestone 1**  
- `TestPlayer.java` is outdated.  
- `Wild` method in `UNOGame.java` returns null.  

### **Milestone 2**  
- Buttons do not work.  
- Player information is not saved.  

### **Milestone 3**  
- No issues.  

### **Milestone 4**  
- Images do not display in the JAR file. To view card images, run the Java files and refer to the "Our Game Display" image.  

---

## Next Steps  
None.  

---

## Milestones  

1. **Milestone 1**: Develop a text-based playable version of Uno Flip via console with keyboard input (Completed).  
2. **Milestone 2**: Create a GUI-based iteration of Uno Flip using MVC design (Completed).  
3. **Milestone 3**: Add Uno Flip Integration and AI Player Capability (Completed).  
4. **Milestone 4**: Implement Undo, Redo, Replay, and Save/Load features (Completed).  

---

## Installation  

- **IDE Recommendation**: IntelliJ IDEA 2023.2 or any Java IDE.  
- **JDK Recommendation**: JDK 21 or any compatible version.  

---

## Rules  

- Refer to the official Uno Flip game rules: [Ultra Board Games - Uno Flip Rules](https://www.ultraboardgames.com/uno/flip-game-rules.php)  

