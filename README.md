# Baccarat Game Simulation

The **Baccarat Game Simulation** is a Java program designed to simulate the card game of Baccarat between a Player and a Banker. The program models cards, hands, and a "shoe" (multiple decks), dealing cards and calculating scores according to standard Baccarat rules to determine the winner of each round.

The program accepts command-line arguments to control the game mode:

1. No arguments: Runs the game in non-interactive simulation mode.
2. `-i` or `--interactive`: Runs the game in interactive mode.

A `Shoe` class is used to manage 6 or 8 decks of cards, ensuring realistic shuffling and dealing mechanics.

---

## Input and Interaction

In **Interactive Mode**, the program pauses after each round to accept user input. The user acts by typing:

* **Y** or **y**: to continue playing another round
* **N** or **n** (or any other input): to end the game

Example interaction:

```
Round 1: Player Win!
Continue? (Y/N): Y
```

---

## Output Format

The program prints the game progress to the standard output (console).
At the end of the game, it displays a summary of the session:

* Rounds Played
* Player Wins
* Banker Wins
* Ties

Example output:

```
Rounds Played: 15
Player Wins: 6
Banker Wins: 7
Ties: 2
```

---

## How to Compile and Run

### Compilation

Use the Gradle wrapper to compile and build the project:

```
./gradlew build
```

### Execution

Run the program using the following commands via Gradle:

**Non-Interactive Mode:**

```
./gradlew run
```

**Interactive Mode:**

```
./gradlew interact
```

Alternatively, commands can be run directly if compiled manually, passing the `-i` flag for interaction.

---

## Program Features

* Object-Oriented Design (BaccaratCard, BaccaratHand, Shoe classes)
* Support for variable shoe sizes (6 or 8 decks)
* Implementation of Baccarat scoring and "Natural" win detection
* Interactive command-line interface for user-controlled play
* Automated statistics tracking for all game sessions

---

## Author and License

It is not licensed and is free to use and modify.
