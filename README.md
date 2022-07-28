# tic-tac-toe-with-AI
Tic Tac Toe console game, created learning basic Java context, and its syntax

## Description
This is a simple Tic Tac Toe console game, with 3 different difficulty levels of opponents.
* easy opponent - makes random available moves
* medium opponent - looks one step ahead, won't make a move that instantly looses, will find the winning move
* hard opponent - implements minimax algorithm, which makes it unbeatable

Game allows you to choose who starts, and who plays. You can make two computer opponents play against each other.
## Installation
Clone repository
```
  git clone https://github.com/tomaszkapron/tic-tac-toe-with-AI.git
```

Build and run (If working on Windows use gradlew.bat scripts instead)
```
  gradlew build
  gradlew run
```

## Usage
To get in to play you need to input command:
```
start <player to start> <2nd player>
```
availible players are from the list [easy, medium, hard, user], where the user will require input from your keyboard. If you chose so, you will need to type:
```
<column> <row>
```
Board is numbered from 1 to 3
```
  1  2  3 
 ---------
1|       |
2|       |
3|       |
 ---------
```

## Example output
The greater-than symbol followed by a space (> ) represents the user input. It's not part of the input.
```
Input command: 
> start hard user
---------
|       |
|       |
|       |
---------
Making move level "hard"
---------
| X     |
|       |
|       |
---------
Enter the coordinates: 
> 2 2
---------
| X     |
|   O   |
|       |
---------
Making move level "hard"
---------
| X X   |
|   O   |
|       |
---------
Enter the coordinates: 
1 3
---------
| X X O |
|   O   |
|       |
---------
Making move level "hard"
---------
| X X O |
|   O   |
| X     |
---------
Enter the coordinates: 
2 1
---------
| X X O |
| O O   |
| X     |
---------
Making move level "hard"
---------
| X X O |
| O O X |
| X     |
---------
Enter the coordinates: 
...
```
## What have I learnt
* Basics of Java
* Recursive functions in Java
* Polymorphism 
* Custom Java annotations
* Minimax algorithm
